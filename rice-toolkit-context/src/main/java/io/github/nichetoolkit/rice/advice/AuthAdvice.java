package io.github.nichetoolkit.rice.advice;

import io.github.nichetoolkit.rice.RestAfterLoginAdvice;
import io.github.nichetoolkit.rice.constant.AdviceConstants;
import io.github.nichetoolkit.rice.stereotype.RestAuth;

/**
 * <code>AuthAdvice</code>
 * <p>The auth advice interface.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.RestAfterLoginAdvice
 * @since Jdk17
 */
public interface AuthAdvice extends RestAfterLoginAdvice<RestAuth> {

    default int order() {
        return AdviceConstants.AUTH_ADVICE_ORDER;
    }
}
