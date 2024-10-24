package io.github.nichetoolkit.rice.constant;


/**
 * <code>AdviceConstants</code>
 * <p>The advice constants interface.</p>
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public interface AdviceConstants {

    /**
     * <code>RESPONSE_ORDER</code>
     * <p>The constant <code>RESPONSE_ORDER</code> field.</p>
     */
    int RESPONSE_ORDER = 1;
    /**
     * <code>LOGIN_ORDER</code>
     * <p>The constant <code>LOGIN_ORDER</code> field.</p>
     */
    int LOGIN_ORDER = 10;

    /**
     * <code>ANNOTATION_ORDER</code>
     * <p>The constant <code>ANNOTATION_ORDER</code> field.</p>
     */
    int ANNOTATION_ORDER = 11;
    /**
     * <code>AUTH_ADVICE_ORDER</code>
     * <p>The constant <code>AUTH_ADVICE_ORDER</code> field.</p>
     */
    int AUTH_ADVICE_ORDER = 11;
    /**
     * <code>USERLOG_ADVICE_ORDER</code>
     * <p>The constant <code>USERLOG_ADVICE_ORDER</code> field.</p>
     */
    int USERLOG_ADVICE_ORDER = Integer.MAX_VALUE;
}
