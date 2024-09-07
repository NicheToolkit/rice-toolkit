package io.github.nichetoolkit.rice.service;

import io.fusionauth.jwt.InvalidJWTException;
import io.fusionauth.jwt.InvalidJWTSignatureException;
import io.fusionauth.jwt.domain.JWT;
import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.helper.OptionalHelper;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rest.util.JsonUtils;
import io.github.nichetoolkit.rest.worker.jwt.JwtWorker;
import io.github.nichetoolkit.rice.RestLoginResult;
import io.github.nichetoolkit.rice.RestMap;
import io.github.nichetoolkit.rice.RestTokenResolver;
import io.github.nichetoolkit.rice.simple.LoginResult;
import io.github.nichetoolkit.rice.simple.UserModel;
import io.github.nichetoolkit.rice.stereotype.login.RestLogin;
import io.github.nichetoolkit.rice.stereotype.login.RestPending;
import io.micrometer.core.instrument.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Slf4j
@Component
public class TokenService implements RestTokenResolver<UserModel, LoginResult> {

    @Autowired
    protected StringRedisTemplate redisTemplate;

    @Override
    public String token(RestMap restMap, Object login, LoginResult loginResult) throws RestException {
        if (!(login instanceof RestLogin) && !(login instanceof RestPending)) {
            return null;
        }
        if ((login instanceof RestLogin)) {
            if (((RestLogin) login).update()) {
                redisTemplate.delete(LoginConst.LOGIN_OAUTH_ACCESS_TOKEN);
                loginResult.setAccessToken(null);
            }
        }
        if ((login instanceof RestPending)) {
            if (((RestPending) login).update()) {
                redisTemplate.delete(LoginConst.LOGIN_OAUTH_ACCESS_TOKEN);
                loginResult.setAccessToken(null);
            }
        }
        if (StringUtils.isNotEmpty(loginResult.getAccessToken())) {
            return loginResult.getAccessToken();
        }
        String userId = String.valueOf(restMap.get(LoginConst.LOGIN_OAUTH_USER_ID));
        return JwtWorker.token(userId, restMap);
    }

    @Override
    public User check(String accessToken) throws RestException {
        /** accessToken 非空校验 */
        OptionalHelper.nullable(accessToken, "访问令牌校验无效，请重新登录！", TokenInvalidException::new);
        /** 获取缓存信息 */
        JWT jwt = null;
        try {
            jwt = JwtWorker.parse(accessToken);
        } catch (InvalidJWTSignatureException | InvalidJWTException ignored) {
        }
        OptionalHelper.nullable(jwt, "访问令牌校验无效，请重新登录！", TokenInvalidException::new);
        assert jwt != null;
        Map<String, Object> context = jwt.getRawClaims();
        String userId = String.valueOf(context.get(LoginConst.LOGIN_OAUTH_USER_ID));
        String username = jwt.subject;
        OptionalHelper.falseable(GeneralUtils.isNotEmpty(userId) && GeneralUtils.isNotEmpty(username) && username.equals(userId),"访问令牌校验无效，请重新登录！", TokenInvalidException::new);
        String userJson = redisTemplate.opsForValue().get(LoginConst.LOGIN_OAUTH_ACCESS_TOKEN + userId);
        User user = JsonUtils.parseBean(userJson,User.class);
        OptionalHelper.falseable(GeneralUtils.isNotEmpty(userJson) && GeneralUtils.isNotEmpty(user),"访问令牌校验无效，请重新登录！", TokenInvalidException::new);
        context.put(LoginConst.LOGIN_OAUTH_USER_INFO, userJson);
        user.setContext(context);
        String moduleId = String.valueOf(context.get(LoginConst.LOGIN_OAUTH_MODULE_ID));
        if (GeneralUtils.isNotEmpty(moduleId)) {
            user.setModuleId(moduleId);
        }
        String moduleKey = String.valueOf(context.get(LoginConst.LOGIN_OAUTH_MODULE_KEY));
        if (GeneralUtils.isNotEmpty(moduleKey)) {
            user.setModuleId(moduleKey);
        }
        String moduleJson = String.valueOf(context.get(LoginConst.LOGIN_OAUTH_USER_MODULE));
        if (GeneralUtils.isNotEmpty(moduleJson)) {
            Module module = JsonUtils.parseBean(moduleJson, Module.class);
            user.setModule(module);
        }
        return user;
    }


    @Override
    public User userInfo() throws RestException {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if(GeneralUtils.isNotEmpty(servletRequestAttributes)) {
            return userInfo(RestRequestHelper.getRestRequestWrapper(servletRequestAttributes.getRequest()));
        }
        return null;
    }

    @Override
    public User userInfo(HttpServletRequest request) throws RestException {
        String userJson = String.valueOf(request.getAttribute(LoginConst.LOGIN_OAUTH_USER_INFO));
        User localUser = JsonUtils.parseBean(userJson,User.class);
        if (GeneralUtils.isEmpty(localUser)) {
            String token = InterceptorHelper.getRequestToken(request);
            localUser = check(token);
            request.setAttribute(LoginConst.LOGIN_OAUTH_USER_INFO, JsonUtils.parseJson(localUser));
        }
        MultilinkBuildHelper.buildRoles(localUser);
        return localUser;
    }

    @Override
    public String resolveToken(RestMap restMap, Object login, LoginResult loginResult) throws RestException {
        if (!(login instanceof RestLogin) && !(login instanceof RestPending)) {
            return null;
        }
        if ((login instanceof RestLogin)) {
            if (((RestLogin) login).update()) {
                redisTemplate.delete(UserModel.LOGIN_TOKEN);
                loginResult.setAccessToken(null);
            }
        }
        if ((login instanceof RestPending)) {
            if (((RestPending) login).update()) {
                redisTemplate.delete(UserModel.LOGIN_TOKEN);
                loginResult.setAccessToken(null);
            }
        }
        if (StringUtils.isNotEmpty(loginResult.getAccessToken())) {
            return loginResult.getAccessToken();
        }
        String userId = String.valueOf(restMap.get(UserModel.LOGIN_USER_ID));
        return JwtWorker.token(userId, restMap);
    }

    @Override
    public UserModel resolveUserInfo() throws RestException {
        return null;
    }

    @Override
    public UserModel resolveUserInfo(String token) throws RestException {
        return null;
    }

    @Override
    public UserModel resolveUserInfo(HttpServletRequest request) throws RestException {
        String userJson = String.valueOf(request.getAttribute(UserModel.LOGIN_USER_INFO));
        UserModel localUser = JsonUtils.parseBean(userJson,UserModel.class);
        if (GeneralUtils.isEmpty(localUser)) {
            String token = InterceptorHelper.getRequestToken(request);
            localUser = check(token);
            request.setAttribute(LoginConst.LOGIN_OAUTH_USER_INFO, JsonUtils.parseJson(localUser));
        }
        MultilinkBuildHelper.buildRoles(localUser);
        return localUser;
    }
}
