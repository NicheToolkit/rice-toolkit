package io.github.nichetoolkit.rice.login;


import io.github.nichetoolkit.rest.*;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rest.util.JsonUtils;
import io.github.nichetoolkit.rest.util.OptionalUtils;
import io.github.nichetoolkit.rice.TokenContext;
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
 * <p>The login handler class.</p>
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
     * {@link io.github.nichetoolkit.rice.configure.RiceLoginProperties} <p>The <code>loginProperties</code> field.</p>
     * @see io.github.nichetoolkit.rice.configure.RiceLoginProperties
     */
    private final RiceLoginProperties loginProperties;

    /**
     * <code>redisTemplate</code>
     * {@link org.springframework.data.redis.core.StringRedisTemplate} <p>The <code>redisTemplate</code> field.</p>
     * @see org.springframework.data.redis.core.StringRedisTemplate
     */
    private final StringRedisTemplate redisTemplate;

    /**
     * <code>tokenService</code>
     * {@link io.github.nichetoolkit.rice.service.TokenService} <p>The <code>tokenService</code> field.</p>
     * @see io.github.nichetoolkit.rice.service.TokenService
     */
    private final TokenService tokenService;

    /**
     * <code>LoginHandler</code>
     * <p>Instantiates a new login handler.</p>
     * @param loginProperties {@link io.github.nichetoolkit.rice.configure.RiceLoginProperties} <p>The login properties parameter is <code>RiceLoginProperties</code> type.</p>
     * @param redisTemplate   {@link org.springframework.data.redis.core.StringRedisTemplate} <p>The redis template parameter is <code>StringRedisTemplate</code> type.</p>
     * @param tokenService    {@link io.github.nichetoolkit.rice.service.TokenService} <p>The token service parameter is <code>TokenService</code> type.</p>
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
    public Object doLoginHandle(RestHttpRequest httpRequest, Object body, MethodParameter returnType, TokenContext context) throws RestException {
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
            String userId = String.valueOf(context.get(UserModel.LOGIN_USER_ID));
            loginResult.setUserId(userId);

            String userJson = String.valueOf(context.get(UserModel.LOGIN_USER_INFO));
            context.remove(UserModel.LOGIN_USER_INFO);
            UserModel user = JsonUtils.parseBean(userJson, UserModel.class);

            loginResult.setUser(user);
            token = tokenService.resolveToken(context, restLogin, loginResult);
            redisTemplate.opsForValue().set(UserModel.LOGIN_TOKEN + userId, userJson, loginProperties.getTokenExpiration(), loginProperties.getTokenTimeUnit());
            loginResult.setToken(token);
        }
        return body;
    }

    @Override
    public void doLogoutHandle(RestHttpRequest request, Object body, MethodParameter returnType, TokenContext context) throws RestException {
        UserModel userModel = tokenService.resolveUserInfo(request);
        RestOptional.ofNullable(userModel).ifEmptyPresent(user ->{
            String userId = user.getId();
            Object accessToken = redisTemplate.opsForValue().get(UserModel.LOGIN_TOKEN + userId);
            if (GeneralUtils.isNotEmpty(accessToken)) {
                redisTemplate.delete(UserModel.LOGIN_TOKEN + userId);
            }
        });
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
        OptionalUtils.ofNull(user, log, TokenPermissionException::new);
        return true;
    }

}
