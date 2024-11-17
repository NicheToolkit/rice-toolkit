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
 * <p>The login controller class.</p>
 * @see  lombok.extern.slf4j.Slf4j
 * @see  org.springframework.web.bind.annotation.CrossOrigin
 * @see  org.springframework.web.bind.annotation.RestController
 * @see  io.github.nichetoolkit.rest.userlog.stereotype.RestNotelog
 * @see  org.springframework.web.bind.annotation.RequestMapping
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
@Slf4j
@CrossOrigin
@RestController
@RestNotelog(loggingKey = "login", notelog = "login controller log")
@RequestMapping("/rest")
public class LoginController {

    /**
     * <code>loginService</code>
     * {@link io.github.nichetoolkit.rice.service.LoginService} <p>The <code>loginService</code> field.</p>
     * @see  io.github.nichetoolkit.rice.service.LoginService
     */
    private final LoginService loginService;

    /**
     * <code>LoginController</code>
     * <p>Instantiates a new login controller.</p>
     * @param loginService {@link io.github.nichetoolkit.rice.service.LoginService} <p>The login service parameter is <code>LoginService</code> type.</p>
     * @see  io.github.nichetoolkit.rice.service.LoginService
     * @see  org.springframework.beans.factory.annotation.Autowired
     */
    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    /**
     * <code>loginWithPassword</code>
     * <p>The login with password method.</p>
     * @param context {@link io.github.nichetoolkit.rice.TokenContext} <p>The context parameter is <code>TokenContext</code> type.</p>
     * @param loginRequest {@link io.github.nichetoolkit.rice.simple.LoginRequest} <p>The login request parameter is <code>LoginRequest</code> type.</p>
     * @see  io.github.nichetoolkit.rice.TokenContext
     * @see  io.github.nichetoolkit.rice.simple.LoginRequest
     * @see  org.springframework.web.bind.annotation.RequestBody
     * @see  io.github.nichetoolkit.rest.RestResult
     * @see  io.github.nichetoolkit.rice.stereotype.RestLogin
     * @see  org.springframework.web.bind.annotation.PostMapping
     * @see  io.github.nichetoolkit.rest.userlog.stereotype.RestUserlog
     * @see  io.github.nichetoolkit.rest.RestException
     * @return  {@link io.github.nichetoolkit.rest.RestResult} <p>The login with password return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     */
    @RestLogin
    @PostMapping("/login/password")
    @RestUserlog(loggingType = LoggingType.USER_LOGIN, userlog = "password login")
    public RestResult<UserModel> loginWithPassword(TokenContext context, @RequestBody LoginRequest loginRequest) throws RestException {
        UserModel user = loginService.loginWithPassword(loginRequest);
        return buildLoginResult(context, user);
    }

    /**
     * <code>loginWithAccessToken</code>
     * <p>The login with access token method.</p>
     * @param context {@link io.github.nichetoolkit.rice.TokenContext} <p>The context parameter is <code>TokenContext</code> type.</p>
     * @param loginRequest {@link io.github.nichetoolkit.rice.simple.LoginRequest} <p>The login request parameter is <code>LoginRequest</code> type.</p>
     * @see  io.github.nichetoolkit.rice.TokenContext
     * @see  io.github.nichetoolkit.rice.simple.LoginRequest
     * @see  org.springframework.web.bind.annotation.RequestBody
     * @see  io.github.nichetoolkit.rest.RestResult
     * @see  io.github.nichetoolkit.rice.stereotype.RestLogin
     * @see  org.springframework.web.bind.annotation.PostMapping
     * @see  io.github.nichetoolkit.rest.userlog.stereotype.RestUserlog
     * @see  io.github.nichetoolkit.rest.RestException
     * @return  {@link io.github.nichetoolkit.rest.RestResult} <p>The login with access token return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     */
    @RestLogin
    @PostMapping("/login/token")
    @RestUserlog(loggingType = LoggingType.USER_LOGIN, userlog = "token login")
    public RestResult<UserModel> loginWithAccessToken(TokenContext context, @RequestBody LoginRequest loginRequest) throws RestException {
        UserModel user = loginService.loginWithToken(loginRequest);
        return buildLoginResult(context, user);
    }

    /**
     * <code>logout</code>
     * <p>The logout method.</p>
     * @return  {@link io.github.nichetoolkit.rest.RestResult} <p>The logout return object is <code>RestResult</code> type.</p>
     * @see  io.github.nichetoolkit.rest.RestResult
     * @see  io.github.nichetoolkit.rice.stereotype.RestLogout
     * @see  org.springframework.web.bind.annotation.GetMapping
     * @see  io.github.nichetoolkit.rest.userlog.stereotype.RestUserlog
     * @see  java.lang.Exception
     * @throws Exception {@link java.lang.Exception} <p>The exception is <code>Exception</code> type.</p>
     */
    @RestLogout
    @GetMapping("/logout")
    @RestUserlog(loggingType = LoggingType.USER_LOGOUT, userlog = "user logout")
    public RestResult<?> logout() throws Exception {
        return RestResult.success();
    }

    /**
     * <code>info</code>
     * <p>The info method.</p>
     * @param user {@link io.github.nichetoolkit.rice.simple.UserModel} <p>The user parameter is <code>UserModel</code> type.</p>
     * @see  io.github.nichetoolkit.rice.simple.UserModel
     * @see  io.github.nichetoolkit.rice.stereotype.RestUser
     * @see  io.github.nichetoolkit.rest.RestResult
     * @see  org.springframework.web.bind.annotation.GetMapping
     * @see  io.github.nichetoolkit.rest.userlog.stereotype.RestUserlog
     * @see  io.github.nichetoolkit.rest.RestException
     * @return  {@link io.github.nichetoolkit.rest.RestResult} <p>The info return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     */
    @GetMapping("/info")
    @RestUserlog(loggingType = LoggingType.USER, userlog = "user info")
    public RestResult<UserModel> info(@RestUser UserModel user) throws RestException {
        return RestResult.success(user);
    }

    /**
     * <code>buildLoginResult</code>
     * <p>The build login result method.</p>
     * @param context {@link io.github.nichetoolkit.rice.TokenContext} <p>The context parameter is <code>TokenContext</code> type.</p>
     * @param user {@link io.github.nichetoolkit.rice.simple.UserModel} <p>The user parameter is <code>UserModel</code> type.</p>
     * @see  io.github.nichetoolkit.rice.TokenContext
     * @see  io.github.nichetoolkit.rice.simple.UserModel
     * @see  io.github.nichetoolkit.rest.RestResult
     * @return  {@link io.github.nichetoolkit.rest.RestResult} <p>The build login result return object is <code>RestResult</code> type.</p>
     */
    private RestResult<UserModel> buildLoginResult(TokenContext context, UserModel user) {
        context.put(UserModel.LOGIN_USER_INFO, JsonUtils.parseJson(user));
        context.put(UserModel.LOGIN_USER_ID, user.getId());
        return RestResult.success(user);
    }

}
