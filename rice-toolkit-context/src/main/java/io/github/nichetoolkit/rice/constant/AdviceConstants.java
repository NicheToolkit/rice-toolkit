package io.github.nichetoolkit.rice.constant;


/**
 * <code>AdviceConstants</code>
 * <p>The type advice constants interface.</p>
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public interface AdviceConstants {

    /**
     * <code>RESPONSE_ORDER</code>
     * {@link java.lang.Integer} <p>the constant <code>RESPONSE_ORDER</code> field.</p>
     * @see java.lang.Integer
     */
    int RESPONSE_ORDER = 1;
    /**
     * <code>LOGIN_ORDER</code>
     * {@link java.lang.Integer} <p>the constant <code>LOGIN_ORDER</code> field.</p>
     * @see java.lang.Integer
     */
    int LOGIN_ORDER = 10;

    /**
     * <code>ANNOTATION_ORDER</code>
     * {@link java.lang.Integer} <p>the constant <code>ANNOTATION_ORDER</code> field.</p>
     * @see java.lang.Integer
     */
    int ANNOTATION_ORDER = 11;
    /**
     * <code>AUTH_ADVICE_ORDER</code>
     * {@link java.lang.Integer} <p>the constant <code>AUTH_ADVICE_ORDER</code> field.</p>
     * @see java.lang.Integer
     */
    int AUTH_ADVICE_ORDER = 11;
    /**
     * <code>USERLOG_ADVICE_ORDER</code>
     * {@link java.lang.Integer} <p>the constant <code>USERLOG_ADVICE_ORDER</code> field.</p>
     * @see java.lang.Integer
     */
    int USERLOG_ADVICE_ORDER = Integer.MAX_VALUE;
}
