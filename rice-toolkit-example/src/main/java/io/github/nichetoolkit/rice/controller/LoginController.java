package io.github.nichetoolkit.rice.controller;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.RestResult;
import io.github.nichetoolkit.rest.userlog.LoggingType;
import io.github.nichetoolkit.rest.userlog.stereotype.RestNotelog;
import io.github.nichetoolkit.rest.userlog.stereotype.RestUserlog;
import io.github.nichetoolkit.rest.util.JsonUtils;
import io.github.nichetoolkit.rice.TokenContext;
import io.github.nichetoolkit.rice.service.LoginService;
import io.github.nichetoolkit.rice.simple.LoginRequest;
import io.github.nichetoolkit.rice.simple.UserModel;
import io.github.nichetoolkit.rice.stereotype.RestLogin;
import io.github.nichetoolkit.rice.stereotype.RestLogout;
import io.github.nichetoolkit.rice.stereotype.RestUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <code>LoginController</code>
 * <p>The type login controller class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see lombok.extern.slf4j.Slf4j
 * @see org.springframework.web.bind.annotation.CrossOrigin
 * @see org.springframework.web.bind.annotation.RestController
 * @see io.github.nichetoolkit.rest.userlog.stereotype.RestNotelog
 * @see org.springframework.web.bind.annotation.RequestMapping
 * @since Jdk1.8
 */
@Slf4j
@CrossOrigin
@RestController
@RestNotelog(loggingKey = "login", notelog = "login controller log")
@RequestMapping("/rest")
public class LoginController {

    private final LoginService loginService;

    /**
     * <code>LoginController</code>
     * Instantiates a new login controller.
     * @param loginService {@link io.github.nichetoolkit.rice.service.LoginService} <p>the login service parameter is <code>LoginService</code> type.</p>
     * @see io.github.nichetoolkit.rice.service.LoginService
     * @see org.springframework.beans.factory.annotation.Autowired
     */
    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    /**
     * <code>loginWithPassword</code>
     * <p>the with password method.</p>
     * @param restMap      {@link io.github.nichetoolkit.rice.TokenContext} <p>the rest map parameter is <code>TokenContext</code> type.</p>
     * @param loginRequest {@link io.github.nichetoolkit.rice.simple.LoginRequest} <p>the login request parameter is <code>LoginRequest</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>the with password return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rice.TokenContext
     * @see io.github.nichetoolkit.rice.simple.LoginRequest
     * @see org.springframework.web.bind.annotation.RequestBody
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rice.stereotype.RestLogin
     * @see org.springframework.web.bind.annotation.PostMapping
     * @see io.github.nichetoolkit.rest.userlog.stereotype.RestUserlog
     * @see io.github.nichetoolkit.rest.RestException
     */
    @RestLogin
    @PostMapping("/login/password")
    @RestUserlog(loggingType = LoggingType.USER_LOGIN, userlog = "password login")
    public RestResult<UserModel> loginWithPassword(TokenContext restMap, @RequestBody LoginRequest loginRequest) throws RestException {
        UserModel user = loginService.loginWithPassword(loginRequest);
        return buildLoginResult(restMap, user);
    }

    /**
     * <code>loginWithAccessToken</code>
     * <p>the with access token method.</p>
     * @param restMap      {@link io.github.nichetoolkit.rice.TokenContext} <p>the rest map parameter is <code>TokenContext</code> type.</p>
     * @param loginRequest {@link io.github.nichetoolkit.rice.simple.LoginRequest} <p>the login request parameter is <code>LoginRequest</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>the with access token return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rice.TokenContext
     * @see io.github.nichetoolkit.rice.simple.LoginRequest
     * @see org.springframework.web.bind.annotation.RequestBody
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rice.stereotype.RestLogin
     * @see org.springframework.web.bind.annotation.PostMapping
     * @see io.github.nichetoolkit.rest.userlog.stereotype.RestUserlog
     * @see io.github.nichetoolkit.rest.RestException
     */
    @RestLogin
    @PostMapping("/login/token")
    @RestUserlog(loggingType = LoggingType.USER_LOGIN, userlog = "token login")
    public RestResult<UserModel> loginWithAccessToken(TokenContext restMap, @RequestBody LoginRequest loginRequest) throws RestException {
        UserModel user = loginService.loginWithToken(loginRequest);
        return buildLoginResult(restMap, user);
    }

    /**
     * <code>logout</code>
     * <p>the method.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>the return object is <code>RestResult</code> type.</p>
     * @throws Exception {@link java.lang.Exception} <p>the exception is <code>Exception</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rice.stereotype.RestLogout
     * @see org.springframework.web.bind.annotation.GetMapping
     * @see io.github.nichetoolkit.rest.userlog.stereotype.RestUserlog
     * @see java.lang.Exception
     */
    @RestLogout
    @GetMapping("/logout")
    @RestUserlog(loggingType = LoggingType.USER_LOGOUT, userlog = "user logout")
    public RestResult<?> logout() throws Exception {
        return RestResult.success();
    }

    /**
     * <code>info</code>
     * <p>the method.</p>
     * @param user {@link io.github.nichetoolkit.rice.simple.UserModel} <p>the user parameter is <code>UserModel</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>the return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rice.simple.UserModel
     * @see io.github.nichetoolkit.rice.stereotype.RestUser
     * @see io.github.nichetoolkit.rest.RestResult
     * @see org.springframework.web.bind.annotation.GetMapping
     * @see io.github.nichetoolkit.rest.userlog.stereotype.RestUserlog
     * @see io.github.nichetoolkit.rest.RestException
     */
    @GetMapping("/info")
    @RestUserlog(loggingType = LoggingType.USER, userlog = "user info")
    public RestResult<UserModel> info(@RestUser UserModel user) throws RestException {
        return RestResult.success(user);
    }

    private RestResult<UserModel> buildLoginResult(TokenContext restMap, UserModel user) {
        restMap.put(UserModel.LOGIN_USER_INFO, JsonUtils.parseJson(user));
        restMap.put(UserModel.LOGIN_USER_ID, user.getId());
        return RestResult.success(user);
    }

}
