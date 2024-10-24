package io.github.nichetoolkit.rice.service;

import io.fusionauth.jwt.InvalidJWTException;
import io.fusionauth.jwt.InvalidJWTSignatureException;
import io.fusionauth.jwt.domain.JWT;
import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.RestHttpRequest;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rest.util.JsonUtils;
import io.github.nichetoolkit.rest.util.OptionalUtils;
import io.github.nichetoolkit.rest.worker.jwt.JwtWorker;
import io.github.nichetoolkit.rice.TokenContext;
import io.github.nichetoolkit.rice.RestTokenResolver;
import io.github.nichetoolkit.rice.error.TokenInvalidException;
import io.github.nichetoolkit.rice.helper.HttpRequestHelper;
import io.github.nichetoolkit.rice.simple.LoginResult;
import io.github.nichetoolkit.rice.simple.UserModel;
import io.github.nichetoolkit.rice.stereotype.RestLogin;
import io.github.nichetoolkit.rice.stereotype.RestPended;
import io.micrometer.core.instrument.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * <code>TokenService</code>
 * <p>The token service class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.RestTokenResolver
 * @see lombok.extern.slf4j.Slf4j
 * @see org.springframework.stereotype.Service
 * @since Jdk1.8
 */
@Slf4j
@Service
public class TokenService implements RestTokenResolver<UserModel, LoginResult> {

    /**
     * <code>redisTemplate</code>
     * {@link org.springframework.data.redis.core.StringRedisTemplate} <p>The <code>redisTemplate</code> field.</p>
     * @see org.springframework.data.redis.core.StringRedisTemplate
     */
    protected final StringRedisTemplate redisTemplate;

    /**
     * <code>TokenService</code>
     * <p>Instantiates a new token service.</p>
     * @param redisTemplate {@link org.springframework.data.redis.core.StringRedisTemplate} <p>The redis template parameter is <code>StringRedisTemplate</code> type.</p>
     * @see org.springframework.data.redis.core.StringRedisTemplate
     * @see org.springframework.beans.factory.annotation.Autowired
     */
    @Autowired
    public TokenService(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public String resolveToken(TokenContext restMap, Object login, LoginResult loginResult) throws RestException {
        if (!(login instanceof RestLogin) && !(login instanceof RestPended)) {
            return null;
        }
        if ((login instanceof RestLogin)) {
            if (((RestLogin) login).update()) {
                redisTemplate.delete(UserModel.LOGIN_TOKEN);
                loginResult.setToken(null);
            }
        }
        if ((login instanceof RestPended)) {
            if (((RestPended) login).update()) {
                redisTemplate.delete(UserModel.LOGIN_TOKEN);
                loginResult.setToken(null);
            }
        }
        if (StringUtils.isNotEmpty(loginResult.getToken())) {
            return loginResult.getToken();
        }
        String userId = String.valueOf(restMap.get(UserModel.LOGIN_USER_ID));
        return JwtWorker.token(userId, restMap);
    }

    @Override
    public UserModel resolveUserInfo(String token) throws RestException {
        OptionalUtils.ofEmpty(token, log,TokenInvalidException::new);
        JWT jwt;
        try {
            jwt = JwtWorker.parse(token);
        } catch (InvalidJWTSignatureException | InvalidJWTException ignored) {
            throw new TokenInvalidException();
        }
        Map<String, Object> context = jwt.getRawClaims();
        String userId = String.valueOf(context.get(UserModel.LOGIN_USER_ID));
        String username = jwt.subject;
        OptionalUtils.ofFalse(GeneralUtils.isNotEmpty(userId) && GeneralUtils.isNotEmpty(username) && username.equals(userId),log,TokenInvalidException::new);
        String userJson = redisTemplate.opsForValue().get(UserModel.LOGIN_TOKEN + userId);
        UserModel localUser = JsonUtils.parseBean(userJson,UserModel.class);
        OptionalUtils.ofFalse(GeneralUtils.isNotEmpty(userJson) && GeneralUtils.isNotEmpty(localUser),log,TokenInvalidException::new);
        context.put(UserModel.LOGIN_USER_INFO, userJson);
        return localUser;
    }


    @Override
    public UserModel resolveUserInfo() throws RestException {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if(GeneralUtils.isNotEmpty(servletRequestAttributes)) {
            return resolveUserInfo(RestHttpRequest.getHttpRequest(servletRequestAttributes.getRequest()));
        }
        return null;
    }

    @Override
    public UserModel resolveUserInfo(HttpServletRequest request) throws RestException {
        String userJson = String.valueOf(request.getAttribute(UserModel.LOGIN_USER_INFO));
        UserModel localUser = JsonUtils.parseBean(userJson,UserModel.class);
        if (GeneralUtils.isEmpty(localUser)) {
            String token = HttpRequestHelper.resolveToken(request);
            localUser = resolveUserInfo(token);
            request.setAttribute(UserModel.LOGIN_USER_INFO, JsonUtils.parseJson(localUser));
        }
        return localUser;
    }
}
