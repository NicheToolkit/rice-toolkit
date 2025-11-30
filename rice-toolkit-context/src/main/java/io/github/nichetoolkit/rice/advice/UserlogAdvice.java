package io.github.nichetoolkit.rice.advice;

import io.github.nichetoolkit.rest.userlog.stereotype.RestUserlog;
import io.github.nichetoolkit.rice.RestAfterLoginAdvice;
import io.github.nichetoolkit.rice.constant.AdviceConstants;

/**
 * <code>UserlogAdvice</code>
 * <p>The userlog advice interface.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.RestAfterLoginAdvice
 * @since Jdk17
 */
public interface UserlogAdvice extends RestAfterLoginAdvice<RestUserlog> {

    default int order() {
        return AdviceConstants.USERLOG_ADVICE_ORDER;
    }

}
