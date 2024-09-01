package io.github.nichetoolkit.rice;

import io.github.nichetoolkit.rest.RestException;

import javax.servlet.http.HttpServletRequest;

public interface RestTokenResolver<U extends RestUserInfo<?>, R extends RestLoginResult<R>> {

    String resolveToken(RestMap restMap, Object login, R loginResult) throws RestException;

    U resolveUserInfo() throws RestException;

    U resolveUserInfo(String token) throws RestException;

    U resolveUserInfo(HttpServletRequest request) throws RestException;
}
