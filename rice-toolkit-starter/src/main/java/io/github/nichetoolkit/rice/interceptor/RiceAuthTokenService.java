package io.github.nichetoolkit.rice.interceptor;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rice.RestMap;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>RiceAuthTokenService</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public interface RiceAuthTokenService<T, R extends RiceLoginResult> {

    String generateToken(RestMap restMap, Object login, R loginResult) throws RestException;

    T checkToken(String accessToken) throws RestException;

    T userInfo() throws RestException;

    T userInfo(HttpServletRequest request) throws RestException;
}
