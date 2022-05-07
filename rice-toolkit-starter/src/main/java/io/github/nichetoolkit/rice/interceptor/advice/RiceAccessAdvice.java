package io.github.nichetoolkit.rice.interceptor.advice;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.RestResult;
import io.github.nichetoolkit.rest.interceptor.RestRequestWrapper;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rice.RestMap;
import io.github.nichetoolkit.rice.constant.LoginConstants;
import io.github.nichetoolkit.rice.error.token.TokenNoPermissionException;
import io.github.nichetoolkit.rice.helper.LoginTokenHelper;
import io.github.nichetoolkit.rice.stereotype.RestAccess;
import io.github.nichetoolkit.rice.stereotype.RestUser;
import org.springframework.core.MethodParameter;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>RiceAccessAdvice </p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public interface RiceAccessAdvice {

    default boolean supports(RestAccess restAccess,HandlerMethod handlerMethod) {
        return GeneralUtils.isNotEmpty(restAccess);
    }

    default void checkAccess(RestRequestWrapper request, HttpServletResponse response, HandlerMethod handlerMethod,RestAccess restAccess) throws RestException {
    }
}
