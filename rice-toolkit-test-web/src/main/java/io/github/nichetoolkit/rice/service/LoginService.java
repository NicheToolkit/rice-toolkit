package io.github.nichetoolkit.rice.service;


import com.xinfeng.multilink.domain.login.LoginRequest;
import com.xinfeng.multilink.domain.model.User;
import io.github.nichetoolkit.rest.RestException;

import java.util.Collection;

/**
 * <p>LoginService</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */

public interface LoginService {

    /**
     * 通过accessToken验证登录
     * @return {@link User} 用户信息
     * @throws RestException RestException
     */
    User loginWithAccessToken(LoginRequest loginRequest) throws RestException;

    /**
     * 通过密码登录
     * @param loginRequest 密码
     * @return {@link User} 用户信息
     * @throws RestException RestException
     */
    User loginWithPassword(LoginRequest loginRequest) throws RestException;

    /**
     * 注销用户信息
     * @param userId 账户Id
     * @throws RestException RestException
     */
    void logoutWithUserId(String userId) throws RestException;

    /**
     * 注销用户信息
     * @param userIds 账户Id集合
     * @throws RestException RestException
     */
    void logoutWithUserIds(Collection<String> userIds) throws RestException;

    /**
     * 查询注册用户信息
     * @param userId 账户Id
     * @return {@link User} 登录账户信息
     * @throws RestException RestException
     */
    User queryWithUserId(String userId, Boolean isLoadRoles) throws RestException;

    /**
     * 更新账户信息
     * @param user 账户信息
     * @return User 账户信息
     * @throws RestException RestException
     */
    User updateWithUser(User user) throws RestException;

}
