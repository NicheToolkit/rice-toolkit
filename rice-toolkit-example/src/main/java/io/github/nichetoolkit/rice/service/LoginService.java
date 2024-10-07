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
     * <code>userService</code>
     * {@link io.github.nichetoolkit.rice.service.UserService} <p>The <code>userService</code> field.</p>
     * @see io.github.nichetoolkit.rice.service.UserService
     */
    private final UserService userService;
    /**
     * <code>tokenService</code>
     * {@link io.github.nichetoolkit.rice.service.TokenService} <p>The <code>tokenService</code> field.</p>
     * @see io.github.nichetoolkit.rice.service.TokenService
     */
    private final TokenService tokenService;

    /**
     * <code>LoginService</code>
     * <p>Instantiates a new login service.</p>
     * @param userService  {@link io.github.nichetoolkit.rice.service.UserService} <p>The user service parameter is <code>UserService</code> type.</p>
     * @param tokenService {@link io.github.nichetoolkit.rice.service.TokenService} <p>The token service parameter is <code>TokenService</code> type.</p>
     * @see io.github.nichetoolkit.rice.service.UserService
     * @see io.github.nichetoolkit.rice.service.TokenService
     * @see org.springframework.beans.factory.annotation.Autowired
     */
    @Autowired
    public LoginService(UserService userService, TokenService tokenService) {
        this.userService = userService;
        this.tokenService = tokenService;
    }


    /**
     * <code>loginWithToken</code>
     * <p>The with token method.</p>
     * @param loginRequest {@link io.github.nichetoolkit.rice.simple.LoginRequest} <p>The login request parameter is <code>LoginRequest</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.simple.UserModel} <p>The with token return object is <code>UserModel</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rice.simple.LoginRequest
     * @see io.github.nichetoolkit.rice.simple.UserModel
     * @see io.github.nichetoolkit.rest.RestException
     */
    public UserModel loginWithToken(LoginRequest loginRequest) throws RestException {
        String token = loginRequest.getToken();
        UserModel localUser = tokenService.resolveUserInfo(token);
        String userId = localUser.getId();
        OptionalUtils.ofFalse(GeneralUtils.isNotEmpty(userId), TokenInvalidException::new);
        return localUser;
    }

    /**
     * <code>loginWithPassword</code>
     * <p>The with password method.</p>
     * @param loginRequest {@link io.github.nichetoolkit.rice.simple.LoginRequest} <p>The login request parameter is <code>LoginRequest</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.simple.UserModel} <p>The with password return object is <code>UserModel</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rice.simple.LoginRequest
     * @see io.github.nichetoolkit.rice.simple.UserModel
     * @see io.github.nichetoolkit.rest.RestException
     */
    public UserModel loginWithPassword(LoginRequest loginRequest) throws RestException {
        String account = loginRequest.getAccount();
        String password = loginRequest.getPassword();
        OptionalUtils.ofFalse(GeneralUtils.isNotEmpty(account) && GeneralUtils.isNotEmpty(password), LoginInfoException::new);
        List<UserModel> modelList = userService.queryByName(account);
        OptionalUtils.ofFalse(GeneralUtils.isNotEmpty(modelList), LoginInfoException::new);
        Optional<UserModel> firstOptional = modelList.stream().findFirst();
        UserModel localUser = firstOptional.orElseThrow(LoginInfoException::new);
        String localPassword = localUser.getPassword();
        if (GeneralUtils.isNotEmpty(localPassword)) {
            String encryptPassword = ShaWorker.encrypts(password);
            OptionalUtils.ofFalse(encryptPassword.equals(localPassword), LoginPasswordException::new);
        }
        return localUser;
    }

}
