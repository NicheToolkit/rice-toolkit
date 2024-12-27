package io.github.nichetoolkit.rice.advice;

import io.github.nichetoolkit.rice.DefaultAdvice;
import io.github.nichetoolkit.rice.constant.AdviceConstants;
import io.github.nichetoolkit.rice.stereotype.RestAuth;

/**
 * <code>AuthAdvice</code>
 * <p>The auth advice interface.</p>
 * @see  io.github.nichetoolkit.rice.DefaultAdvice
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public interface AuthAdvice extends DefaultAdvice<RestAuth> {

    default int order() {
        return AdviceConstants.AUTH_ADVICE_ORDER;
    }
}
