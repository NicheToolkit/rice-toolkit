package io.github.nichetoolkit.rice.advice;

import io.github.nichetoolkit.rice.RestBeforeLoginAdvice;
import io.github.nichetoolkit.rice.constant.AdviceConstants;
import io.github.nichetoolkit.rice.stereotype.RestOpen;

/**
 * <code>OpenAdvice</code>
 * <p>The open advice interface.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.RestBeforeLoginAdvice
 * @since Jdk1.8
 */
public interface OpenAdvice extends RestBeforeLoginAdvice<RestOpen> {

    default int order() {
        return AdviceConstants.OPEN_ADVICE_ORDER;
    }
}
