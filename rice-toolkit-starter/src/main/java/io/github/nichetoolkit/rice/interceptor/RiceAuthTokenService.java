package io.github.nichetoolkit.rice.interceptor;

import io.github.nichetoolkit.rice.RestMap;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>RiceAuthTokenService</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public interface RiceAuthTokenService<T>  {

    String generateToken(RestMap restMap, Object login, RiceLoginResult loginResult);

    T checkToken(String accessToken);

    T userInfo();

    T userInfo(HttpServletRequest request);
}
