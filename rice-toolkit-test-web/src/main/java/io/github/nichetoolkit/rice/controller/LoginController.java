package io.github.nichetoolkit.rice.controller;

import com.xinfeng.multilink.domain.login.LoginRequest;
import com.xinfeng.multilink.domain.model.User;
import com.xinfeng.multilink.enums.login.LoginType;
import com.xinfeng.oauth.consts.LoginConst;
import com.xinfeng.oauth.service.AccountService;
import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.RestResult;
import io.github.nichetoolkit.rest.helper.OptionalHelper;
import io.github.nichetoolkit.rest.userlog.LogType;
import io.github.nichetoolkit.rest.userlog.stereotype.RestNotelog;
import io.github.nichetoolkit.rest.userlog.stereotype.RestUserlog;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rest.util.JsonUtils;
import io.github.nichetoolkit.rice.RestMap;
import io.github.nichetoolkit.rice.constant.LoginConstants;
import io.github.nichetoolkit.rice.error.login.LoginInfoException;
import io.github.nichetoolkit.rice.error.token.TokenInvalidException;
import io.github.nichetoolkit.rice.stereotype.RestUser;
import io.github.nichetoolkit.rice.stereotype.login.RestLogin;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * <p>AccountController</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Slf4j
@CrossOrigin
@RestController
@RestNotelog("账户")
@RequestMapping("/account")
public class LoginController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/login/password")
    @RestUserlog(logType = LogType.USER_LOGIN)
    @RestLogin(prefix = LoginConstants.ACCESS_TOKEN, type = LoginType.PASSWORD_FILED)
    public ResponseEntity<RestResult<User>> loginWithPassword(RestMap restMap, @RequestBody LoginRequest loginRequest) throws RestException {
        User user = accountService.loginWithPassword(loginRequest);
        OptionalHelper.nullable(user,"账户或密码错误！", LoginInfoException::new);
        return buildLoginResult(restMap, user);
    }

    @PostMapping("/login/token")
    @RestUserlog(logType = LogType.USER_LOGIN)
    @RestLogin(prefix = LoginConstants.ACCESS_TOKEN, type = LoginType.TOKEN_FILED)
    public ResponseEntity<RestResult<User>> loginWithAccessToken(RestMap restMap, @RequestBody LoginRequest loginRequest) throws RestException {
        User user = accountService.loginWithAccessToken(loginRequest);
        OptionalHelper.nullable(user,"the token is invalid！", TokenInvalidException::new);
        return buildLoginResult(restMap, user);
    }

    @GetMapping("/info")
    public ResponseEntity<RestResult<User>> info(@RestUser User user,
                                                 @RequestParam(value = "loadRoles",required = false, defaultValue = "false") Boolean loadRoles) throws RestException {
        User query = accountService.queryWithUserId(user.getId(),loadRoles);
        return RestResult.ok(query);
    }

    @GetMapping("/logout")
    @RestUserlog(logType = LogType.USER_LOGOUT)
    public ResponseEntity<RestResult> logout(@RestUser User user) throws Exception {
        accountService.logoutWithUserId(user.getId());
        return RestResult.ok();
    }

    private ResponseEntity<RestResult<User>> buildLoginResult(RestMap restMap, User user) {
        restMap.put(LoginConst.LOGIN_OAUTH_USER_INFO, JsonUtils.parseJson(user));
        restMap.put(LoginConst.LOGIN_OAUTH_USER_ID, user.getId());
        restMap.put(LoginConst.LOGIN_OAUTH_USER_TYPE, String.valueOf(user.getUserType().getKey()));
        restMap.put(LoginConst.LOGIN_OAUTH_USER_ROLE, JsonUtils.parseJson(user.getRoles()));
        if (GeneralUtils.isNotEmpty(user.getModuleKey())) {
            restMap.put(LoginConst.LOGIN_OAUTH_MODULE_KEY, user.getModuleKey());
        }
        if (GeneralUtils.isNotEmpty(user.getModuleId())) {
            restMap.put(LoginConst.LOGIN_OAUTH_MODULE_ID, user.getModuleId());
        }
        if (GeneralUtils.isNotEmpty(user.getModule())) {
            restMap.put(LoginConst.LOGIN_OAUTH_USER_MODULE, JsonUtils.parseJson(user.getModule()));
        }
        return RestResult.ok(user);
    }

}
