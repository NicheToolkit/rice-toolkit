package io.github.nichetoolkit.rice.service;


import com.xinfeng.multilink.domain.login.LoginRequest;
import com.xinfeng.multilink.domain.model.Role;
import com.xinfeng.multilink.domain.model.User;
import com.xinfeng.multilink.enums.UserType;
import com.xinfeng.multilink.helper.MultilinkBuildHelper;
import com.xinfeng.multilink.model.ExternalUser;
import com.xinfeng.multilink.service.*;
import com.xinfeng.oauth.consts.LoginConst;
import com.xinfeng.oauth.login.LoginService;
import com.xinfeng.oauth.service.AccountService;
import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.helper.OptionalHelper;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rest.worker.sha.ShaWorker;
import io.github.nichetoolkit.rice.error.login.LoginAccountException;
import io.github.nichetoolkit.rice.error.login.LoginInfoException;
import io.github.nichetoolkit.rice.error.login.LoginPasswordException;
import io.github.nichetoolkit.rice.error.token.TokenInvalidException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>AccountServiceImpl</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Slf4j
@Service
public class LoginServiceImpl implements AccountService {

    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private UserService userService;
    @Autowired
    private ExternalService externalService;
    @Autowired
    private LoginService loginService;


    @Override
    public User loginWithAccessToken(LoginRequest loginRequest) throws RestException {
        String accessToken = loginRequest.getAccessToken();
        User localUser = loginService.check(accessToken);
        String userId = localUser.getId();
        OptionalHelper.falseable(GeneralUtils.isNotEmpty(userId), "访问令牌校验无效，请重新登录！", TokenInvalidException::new);
        if (loginRequest.isLoadRoles()) {
            MultilinkBuildHelper.buildRoles(localUser);
        } else {
            localUser.setRoles((Collection<Role>) null);
        }
        /** 更新用户登录时间状态 */
        userService.refreshLoginTime(userId);
        return localUser;
    }

    @Override
    public User loginWithPassword(LoginRequest loginRequest) throws RestException {
        String account = loginRequest.getAccount();
        String password = loginRequest.getPassword();
        OptionalHelper.falseable(GeneralUtils.isNotEmpty(account) && GeneralUtils.isNotEmpty(password), "账户或密码错误！", LoginInfoException::new);
        /** 首先验证数据库 */
        User localUser = userService.queryByAccount(account,true,true);
        if (GeneralUtils.isEmpty(localUser)) {
            /** 账户不存在 首次登录 或者 未知用户 */
            String token = externalService.externalLogin(account, password);
            OptionalHelper.nullable(token, "账户或密码错误！", LoginAccountException::new);
            /** 这种情况只存在 数据同时间差时 即 数据中台数据 已经新增 但本地数据暂未同步 */
            /** 通过账户（目前为工号 'employee_code'）数据中台查询 用户数据 */
            Map<String, String> paramsMap = Collections.singletonMap("employee_code", account);
            List<ExternalUser> externalUsers = externalService.externalUser(paramsMap);
            /** 假如 统一认证通过认证 但是查询不到用户数据 说明数据中台数据同步不及时 或者 统一认证接口错误 */
            OptionalHelper.nullable(externalUsers, "账户异常，请联系管理员！", LoginAccountException::new);
            /** 账户首次登录 保存用户信息 */
            Optional<ExternalUser> userOptional = externalUsers.stream().findAny();
            assert userOptional.isPresent();
            ExternalUser externalUser = userOptional.get();
            localUser = externalUser.toModel();
            localUser.setToken(token);
            String encryptPassword = ShaWorker.encrypts(password);
            localUser.setPassword(encryptPassword);
            localUser.setFirstLoginTime(new Date());
            userService.create(localUser);
        } else {
            String localPassword = localUser.password();
            if (GeneralUtils.isNotEmpty(localPassword)) {
                String encryptPassword = ShaWorker.encrypts(password);
                OptionalHelper.falseable(encryptPassword.equals(localPassword), "账户或密码错误！", LoginPasswordException::new);
            }
            if (UserType.USER == localUser.getUserType())  {
                String token = externalService.externalLogin(account, password);
                OptionalHelper.nullable(token, "账户或密码错误！", LoginAccountException::new);
                localUser.setToken(token);
                userService.refreshToken(localUser.getId(),token);
                if (GeneralUtils.isEmpty(localPassword)) {
                    String encryptPassword = ShaWorker.encrypts(password);
                    userService.refreshPassword(localUser.getId(),encryptPassword);
                }
            }
        }
        String userId = localUser.getId();
        if (loginRequest.isLoadRoles()) {
            MultilinkBuildHelper.buildRoles(localUser);
        } else {
            localUser.setRoles((Collection<Role>) null);
        }
        /** 更新用户登录时间状态 */
        userService.refreshLoginTime(userId);
        return localUser;
    }

    @Override
    public void logoutWithUserId(String userId) throws RestException {
        Object accessToken = redisTemplate.opsForValue().get(LoginConst.LOGIN_OAUTH_ACCESS_TOKEN + userId);
        if (GeneralUtils.isNotEmpty(accessToken)) {
            redisTemplate.delete(LoginConst.LOGIN_OAUTH_ACCESS_TOKEN + userId);
        }
    }

    @Override
    public void logoutWithUserIds(Collection<String> userIds) throws RestException {
        if (GeneralUtils.isEmpty(userIds)) {
            return;
        }
        List<String> keyList = userIds.stream().map(userId -> LoginConst.LOGIN_OAUTH_ACCESS_TOKEN + userId).collect(Collectors.toList());
        redisTemplate.delete(keyList);
    }

    @Override
    public User queryWithUserId(String userId, Boolean isLoadRoles) throws RestException {
        User user = userService.queryById(userId, true, true);
        if (isLoadRoles) {
            MultilinkBuildHelper.buildRoles(user);
        } else {
            user.setRoles((Collection<Role>) null);
        }
        return user;
    }

    @Override
    public User updateWithUser(User user) throws RestException {
        return userService.update(user);
    }


}
