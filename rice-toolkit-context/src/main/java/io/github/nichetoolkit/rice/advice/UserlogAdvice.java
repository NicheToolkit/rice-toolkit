package io.github.nichetoolkit.rice.advice;

import io.github.nichetoolkit.rest.userlog.stereotype.RestUserlog;
import io.github.nichetoolkit.rice.DefaultAdvice;
import io.github.nichetoolkit.rice.constant.AdviceConstants;

/**
 * <code>UserlogAdvice</code>
 * <p>The userlog advice interface.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.DefaultAdvice
 * @since Jdk1.8
 */
public interface UserlogAdvice extends DefaultAdvice<RestUserlog> {

    default int order() {
        return AdviceConstants.USERLOG_ADVICE_ORDER;
    }

}
