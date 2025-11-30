package io.github.nichetoolkit.rice.advice;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.RestHttpRequest;
import io.github.nichetoolkit.rice.RestBeforeLogoutAdvice;
import io.github.nichetoolkit.rice.constant.AdviceConstants;
import io.github.nichetoolkit.rice.stereotype.RestLogout;
import org.springframework.web.method.HandlerMethod;

import jakarta.servlet.http.HttpServletResponse;

/**
 * <code>LogoutAdvice</code>
 * <p>The logout advice interface.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.RestBeforeLogoutAdvice
 * @since Jdk17
 */
public interface LogoutAdvice extends RestBeforeLogoutAdvice<RestLogout> {

    default int order() {
        return AdviceConstants.LOGOUT_ORDER;
    }

    @Override
    default void doAnnotationHandle(RestHttpRequest request, HttpServletResponse response, HandlerMethod handlerMethod, RestLogout annotation) throws RestException {
        doLogoutHandle(request);
    }

    /**
     * <code>doLogoutHandle</code>
     * <p>The do logout handle method.</p>
     * @param request {@link io.github.nichetoolkit.rest.RestHttpRequest} <p>The request parameter is <code>RestHttpRequest</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestHttpRequest
     * @see io.github.nichetoolkit.rest.RestException
     */
    default void doLogoutHandle(RestHttpRequest request) throws RestException {
    }
}
