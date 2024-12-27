package io.github.nichetoolkit.rice;

import io.github.nichetoolkit.rest.RestException;

import javax.servlet.http.HttpServletRequest;

/**
 * <code>RestTokenResolver</code>
 * <p>The rest token resolver interface.</p>
 * @param <U>  {@link io.github.nichetoolkit.rice.RestUserInfo} <p>The generic parameter is <code>RestUserInfo</code> type.</p>
 * @param <R>  {@link io.github.nichetoolkit.rice.RestLoginResult} <p>The generic parameter is <code>RestLoginResult</code> type.</p>
 * @see  io.github.nichetoolkit.rice.RestUserInfo
 * @see  io.github.nichetoolkit.rice.RestLoginResult
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public interface RestTokenResolver<U extends RestUserInfo<?>, R extends RestLoginResult<R>> {

    /**
     * <code>resolveToken</code>
     * <p>The resolve token method.</p>
     * @param context {@link io.github.nichetoolkit.rice.TokenContext} <p>The context parameter is <code>TokenContext</code> type.</p>
     * @param login {@link java.lang.Object} <p>The login parameter is <code>Object</code> type.</p>
     * @param loginResult R <p>The login result parameter is <code>R</code> type.</p>
     * @see  io.github.nichetoolkit.rice.TokenContext
     * @see  java.lang.Object
     * @see  java.lang.String
     * @see  io.github.nichetoolkit.rest.RestException
     * @return  {@link java.lang.String} <p>The resolve token return object is <code>String</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     */
    String resolveToken(TokenContext context, Object login, R loginResult) throws RestException;

    /**
     * <code>resolveUserInfo</code>
     * <p>The resolve user info method.</p>
     * @return U <p>The resolve user info return object is <code>U</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see  io.github.nichetoolkit.rest.RestException
     */
    U resolveUserInfo() throws RestException;

    /**
     * <code>resolveUserInfo</code>
     * <p>The resolve user info method.</p>
     * @param token {@link java.lang.String} <p>The token parameter is <code>String</code> type.</p>
     * @see  java.lang.String
     * @see  io.github.nichetoolkit.rest.RestException
     * @return U <p>The resolve user info return object is <code>U</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     */
    U resolveUserInfo(String token) throws RestException;

    /**
     * <code>resolveUserInfo</code>
     * <p>The resolve user info method.</p>
     * @param request {@link javax.servlet.http.HttpServletRequest} <p>The request parameter is <code>HttpServletRequest</code> type.</p>
     * @see  javax.servlet.http.HttpServletRequest
     * @see  io.github.nichetoolkit.rest.RestException
     * @return U <p>The resolve user info return object is <code>U</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     */
    U resolveUserInfo(HttpServletRequest request) throws RestException;
}
