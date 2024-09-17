package io.github.nichetoolkit.rice.login;


import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.RestHttpRequest;
import io.github.nichetoolkit.rest.RestResult;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rest.util.JsonUtils;
import io.github.nichetoolkit.rest.util.OptionalUtils;
import io.github.nichetoolkit.rice.RestMap;
import io.github.nichetoolkit.rice.advice.LoginAdvice;
import io.github.nichetoolkit.rice.configure.RiceLoginProperties;
import io.github.nichetoolkit.rice.service.TokenService;
import io.github.nichetoolkit.rice.simple.LoginResult;
import io.github.nichetoolkit.rice.simple.UserModel;
import io.github.nichetoolkit.rice.stereotype.RestLogin;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import io.github.nichetoolkit.rice.error.TokenPermissionException;

import javax.servlet.http.HttpServletResponse;


/**
 * <code>LoginHandler</code>
 * <p>The type login handler class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.advice.LoginAdvice
 * @see lombok.extern.slf4j.Slf4j
 * @see org.springframework.stereotype.Component
 * @since Jdk1.8
 */
@Slf4j
@Component
public class LoginHandler implements LoginAdvice {

    /**
     * <code>loginProperties</code>
     * {@link io.github.nichetoolkit.rice.configure.RiceLoginProperties} <p>the <code>loginProperties</code> field.</p>
     * @see io.github.nichetoolkit.rice.configure.RiceLoginProperties
     */
    private final RiceLoginProperties loginProperties;

    /**
     * <code>redisTemplate</code>
     * {@link org.springframework.data.redis.core.StringRedisTemplate} <p>the <code>redisTemplate</code> field.</p>
     * @see org.springframework.data.redis.core.StringRedisTemplate
     */
    private final StringRedisTemplate redisTemplate;

    /**
     * <code>tokenService</code>
     * {@link io.github.nichetoolkit.rice.service.TokenService} <p>the <code>tokenService</code> field.</p>
     * @see io.github.nichetoolkit.rice.service.TokenService
     */
    private final TokenService tokenService;

    /**
     * <code>LoginHandler</code>
     * Instantiates a new login handler.
     * @param loginProperties {@link io.github.nichetoolkit.rice.configure.RiceLoginProperties} <p>the login properties parameter is <code>RiceLoginProperties</code> type.</p>
     * @param redisTemplate   {@link org.springframework.data.redis.core.StringRedisTemplate} <p>the redis template parameter is <code>StringRedisTemplate</code> type.</p>
     * @param tokenService    {@link io.github.nichetoolkit.rice.service.TokenService} <p>the token service parameter is <code>TokenService</code> type.</p>
     * @see io.github.nichetoolkit.rice.configure.RiceLoginProperties
     * @see org.springframework.data.redis.core.StringRedisTemplate
     * @see io.github.nichetoolkit.rice.service.TokenService
     * @see org.springframework.beans.factory.annotation.Autowired
     */
    @Autowired
    public LoginHandler(RiceLoginProperties loginProperties, StringRedisTemplate redisTemplate, TokenService tokenService) {
        this.loginProperties = loginProperties;
        this.redisTemplate = redisTemplate;
        this.tokenService = tokenService;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Object doLoginHandle(RestHttpRequest httpRequest, Object body, MethodParameter returnType, RestMap restMap) throws RestException {
        if (doResponseHandle(body)) {
            return null;
        }
        RestResult<Object> restResult = (RestResult<Object>) body;
        restResult.setMessage(RestErrorStatus.SUCCESS.getMessage());
        Object data = restResult.getData();
        if (GeneralUtils.isEmpty(data) || !(data instanceof LoginResult)) {
            data = new LoginResult();
            restResult.setData(data);
        }
        LoginResult loginResult = (LoginResult) data;
        RestLogin restLogin = returnType.getMethodAnnotation(RestLogin.class);
        String token;
        if (GeneralUtils.isNotEmpty(restLogin)) {
            String userId = String.valueOf(restMap.get(UserModel.LOGIN_USER_ID));
            loginResult.setUserId(userId);

            String userJson = String.valueOf(restMap.get(UserModel.LOGIN_USER_INFO));
            restMap.remove(UserModel.LOGIN_USER_INFO);
            UserModel user = JsonUtils.parseBean(userJson, UserModel.class);

            loginResult.setUser(user);
            token = tokenService.resolveToken(restMap, restLogin, loginResult);
            redisTemplate.opsForValue().set(UserModel.LOGIN_TOKEN + userId, userJson, loginProperties.getTokenExpiration(), loginProperties.getTokenTimeUnit());
            loginResult.setToken(token);
        }
        return body;
    }

    @Override
    public boolean preHandle(RestHttpRequest httpRequest, HttpServletResponse response, HandlerMethod handlerMethod) throws RestException {
        if (isSkipApi(httpRequest, handlerMethod)) {
            return true;
        }
        if (!doHeaderHandle(httpRequest, loginProperties.getTokenHeaders())) {
            return false;
        }
        UserModel user = tokenService.resolveUserInfo(httpRequest);
        OptionalUtils.nullable(user, TokenPermissionException::new);
        return true;
    }

}
