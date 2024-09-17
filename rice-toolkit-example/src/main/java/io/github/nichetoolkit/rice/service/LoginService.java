package io.github.nichetoolkit.rice.service;


import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rest.util.OptionalUtils;
import io.github.nichetoolkit.rest.worker.sha.ShaWorker;
import io.github.nichetoolkit.rice.error.LoginInfoException;
import io.github.nichetoolkit.rice.error.LoginPasswordException;
import io.github.nichetoolkit.rice.error.TokenInvalidException;
import io.github.nichetoolkit.rice.simple.LoginRequest;
import io.github.nichetoolkit.rice.simple.UserModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * <code>LoginService</code>
 * <p>The type login service class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see lombok.extern.slf4j.Slf4j
 * @see org.springframework.stereotype.Service
 * @since Jdk1.8
 */
@Slf4j
@Service
public class LoginService {

    /**
     * <code>redisTemplate</code>
     * {@link org.springframework.data.redis.core.StringRedisTemplate} <p>the <code>redisTemplate</code> field.</p>
     * @see org.springframework.data.redis.core.StringRedisTemplate
     */
    private final StringRedisTemplate redisTemplate;
    /**
     * <code>userService</code>
     * {@link io.github.nichetoolkit.rice.service.UserService} <p>the <code>userService</code> field.</p>
     * @see io.github.nichetoolkit.rice.service.UserService
     */
    private final UserService userService;
    /**
     * <code>tokenService</code>
     * {@link io.github.nichetoolkit.rice.service.TokenService} <p>the <code>tokenService</code> field.</p>
     * @see io.github.nichetoolkit.rice.service.TokenService
     */
    private final TokenService tokenService;

    /**
     * <code>LoginService</code>
     * Instantiates a new login service.
     * @param redisTemplate {@link org.springframework.data.redis.core.StringRedisTemplate} <p>the redis template parameter is <code>StringRedisTemplate</code> type.</p>
     * @param userService   {@link io.github.nichetoolkit.rice.service.UserService} <p>the user service parameter is <code>UserService</code> type.</p>
     * @param tokenService  {@link io.github.nichetoolkit.rice.service.TokenService} <p>the token service parameter is <code>TokenService</code> type.</p>
     * @see org.springframework.data.redis.core.StringRedisTemplate
     * @see io.github.nichetoolkit.rice.service.UserService
     * @see io.github.nichetoolkit.rice.service.TokenService
     * @see org.springframework.beans.factory.annotation.Autowired
     */
    @Autowired
    public LoginService(StringRedisTemplate redisTemplate, UserService userService, TokenService tokenService) {
        this.redisTemplate = redisTemplate;
        this.userService = userService;
        this.tokenService = tokenService;
    }


    /**
     * <code>loginWithToken</code>
     * <p>the with token method.</p>
     * @param loginRequest {@link io.github.nichetoolkit.rice.simple.LoginRequest} <p>the login request parameter is <code>LoginRequest</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.simple.UserModel} <p>the with token return object is <code>UserModel</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rice.simple.LoginRequest
     * @see io.github.nichetoolkit.rice.simple.UserModel
     * @see io.github.nichetoolkit.rest.RestException
     */
    public UserModel loginWithToken(LoginRequest loginRequest) throws RestException {
        String token = loginRequest.getToken();
        UserModel localUser = tokenService.resolveUserInfo(token);
        String userId = localUser.getId();
        OptionalUtils.falseable(GeneralUtils.isNotEmpty(userId), TokenInvalidException::new);
        return localUser;
    }

    /**
     * <code>loginWithPassword</code>
     * <p>the with password method.</p>
     * @param loginRequest {@link io.github.nichetoolkit.rice.simple.LoginRequest} <p>the login request parameter is <code>LoginRequest</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.simple.UserModel} <p>the with password return object is <code>UserModel</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rice.simple.LoginRequest
     * @see io.github.nichetoolkit.rice.simple.UserModel
     * @see io.github.nichetoolkit.rest.RestException
     */
    public UserModel loginWithPassword(LoginRequest loginRequest) throws RestException {
        String account = loginRequest.getAccount();
        String password = loginRequest.getPassword();
        OptionalUtils.falseable(GeneralUtils.isNotEmpty(account) && GeneralUtils.isNotEmpty(password), LoginInfoException::new);
        List<UserModel> modelList = userService.queryByName(account);
        OptionalUtils.falseable(GeneralUtils.isNotEmpty(modelList), LoginInfoException::new);
        Optional<UserModel> firstOptional = modelList.stream().findFirst();
        UserModel localUser = firstOptional.orElseThrow(LoginInfoException::new);
        String localPassword = localUser.getPassword();
        if (GeneralUtils.isNotEmpty(localPassword)) {
            String encryptPassword = ShaWorker.encrypts(password);
            OptionalUtils.falseable(encryptPassword.equals(localPassword), LoginPasswordException::new);
        }
        return localUser;
    }


    /**
     * <code>logoutWithUserId</code>
     * <p>the with user id method.</p>
     * @param userId {@link java.lang.String} <p>the user id parameter is <code>String</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestException
     */
    public void logoutWithUserId(String userId) throws RestException {
        Object accessToken = redisTemplate.opsForValue().get(UserModel.LOGIN_TOKEN + userId);
        if (GeneralUtils.isNotEmpty(accessToken)) {
            redisTemplate.delete(UserModel.LOGIN_TOKEN + userId);
        }
    }

}
