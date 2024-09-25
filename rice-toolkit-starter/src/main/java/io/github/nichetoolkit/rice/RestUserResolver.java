package io.github.nichetoolkit.rice;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.RestHttpRequest;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rice.stereotype.RestUser;
import org.springframework.core.MethodParameter;
import org.springframework.lang.NonNull;

/**
 * <code>RestUserResolver</code>
 * <p>The type rest user resolver interface.</p>
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public interface RestUserResolver {

    /**
     * <code>supports</code>
     * <p>the method.</p>
     * @param parameter {@link org.springframework.core.MethodParameter} <p>the parameter parameter is <code>MethodParameter</code> type.</p>
     * @return boolean <p>the return object is <code>boolean</code> type.</p>
     * @see org.springframework.core.MethodParameter
     */
    default boolean supports(MethodParameter parameter) {
        RestUser restUser = parameter.getParameterAnnotation(RestUser.class);
        return GeneralUtils.isNotEmpty(restUser);
    }

    /**
     * <code>resolveUser</code>
     * <p>the user method.</p>
     * @param parameter   {@link org.springframework.core.MethodParameter} <p>the parameter parameter is <code>MethodParameter</code> type.</p>
     * @param httpRequest {@link io.github.nichetoolkit.rest.RestHttpRequest} <p>the http request parameter is <code>RestHttpRequest</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.RestUserInfo} <p>the user return object is <code>RestUserInfo</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.core.MethodParameter
     * @see io.github.nichetoolkit.rest.RestHttpRequest
     * @see io.github.nichetoolkit.rice.RestUserInfo
     * @see org.springframework.lang.NonNull
     * @see io.github.nichetoolkit.rest.RestException
     */
    @NonNull
    RestUserInfo<?> resolveUser(MethodParameter parameter, RestHttpRequest httpRequest) throws RestException;
}
