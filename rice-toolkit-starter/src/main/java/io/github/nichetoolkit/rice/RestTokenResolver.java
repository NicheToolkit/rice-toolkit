package io.github.nichetoolkit.rice;

import io.github.nichetoolkit.rest.RestException;

import javax.servlet.http.HttpServletRequest;

/**
 * <code>RestTokenResolver</code>
 * <p>The type rest token resolver interface.</p>
 * @param <U> {@link io.github.nichetoolkit.rice.RestUserInfo} <p>the generic parameter is <code>RestUserInfo</code> type.</p>
 * @param <R> {@link io.github.nichetoolkit.rice.RestLoginResult} <p>the generic parameter is <code>RestLoginResult</code> type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.RestUserInfo
 * @see io.github.nichetoolkit.rice.RestLoginResult
 * @since Jdk1.8
 */
public interface RestTokenResolver<U extends RestUserInfo<?>, R extends RestLoginResult<R>> {

    /**
     * <code>resolveToken</code>
     * <p>the token method.</p>
     * @param restMap     {@link io.github.nichetoolkit.rice.TokenContext} <p>the rest map parameter is <code>TokenContext</code> type.</p>
     * @param login       {@link java.lang.Object} <p>the login parameter is <code>Object</code> type.</p>
     * @param loginResult R <p>the login result parameter is <code>R</code> type.</p>
     * @return {@link java.lang.String} <p>the token return object is <code>String</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rice.TokenContext
     * @see java.lang.Object
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestException
     */
    String resolveToken(TokenContext restMap, Object login, R loginResult) throws RestException;

    /**
     * <code>resolveUserInfo</code>
     * <p>the user info method.</p>
     * @return U <p>the user info return object is <code>U</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     */
    U resolveUserInfo() throws RestException;

    /**
     * <code>resolveUserInfo</code>
     * <p>the user info method.</p>
     * @param token {@link java.lang.String} <p>the token parameter is <code>String</code> type.</p>
     * @return U <p>the user info return object is <code>U</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestException
     */
    U resolveUserInfo(String token) throws RestException;

    /**
     * <code>resolveUserInfo</code>
     * <p>the user info method.</p>
     * @param request {@link javax.servlet.http.HttpServletRequest} <p>the request parameter is <code>HttpServletRequest</code> type.</p>
     * @return U <p>the user info return object is <code>U</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see javax.servlet.http.HttpServletRequest
     * @see io.github.nichetoolkit.rest.RestException
     */
    U resolveUserInfo(HttpServletRequest request) throws RestException;
}
