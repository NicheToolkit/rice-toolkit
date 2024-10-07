package io.github.nichetoolkit.rice.constant;


/**
 * <code>LoginConstants</code>
 * <p>The type login constants interface.</p>
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public interface LoginConstants {
    /**
     * <code>SKIP_API_PACKAGE</code>
     * {@link java.lang.String} <p>The constant <code>SKIP_API_PACKAGE</code> field.</p>
     * @see java.lang.String
     */
    String SKIP_API_PACKAGE = "SKIP_API";
    /**
     * <code>SKIP_API_REQUEST_FORWARD_FLAG</code>
     * {@link java.lang.String} <p>The constant <code>SKIP_API_REQUEST_FORWARD_FLAG</code> field.</p>
     * @see java.lang.String
     */
    String SKIP_API_REQUEST_FORWARD_FLAG = "SKIP_API_REQUEST_FORWARD_FLAG";

    /**
     * <code>ACCESS_TOKEN_HEADER</code>
     * {@link java.lang.String} <p>The constant <code>ACCESS_TOKEN_HEADER</code> field.</p>
     * @see java.lang.String
     */
    String ACCESS_TOKEN_HEADER = "Access-Token";
    /**
     * <code>ACCESS_AUTH_HEADER</code>
     * {@link java.lang.String} <p>The constant <code>ACCESS_AUTH_HEADER</code> field.</p>
     * @see java.lang.String
     */
    String ACCESS_AUTH_HEADER = "Access-Auth";

    /**
     * <code>TOKEN_PREFIXES</code>
     * {@link java.lang.String} <p>The constant <code>TOKEN_PREFIXES</code> field.</p>
     * @see java.lang.String
     */
    String[] TOKEN_PREFIXES = {"Bearer"};
    /**
     * <code>HEADER_TOKENS</code>
     * {@link java.lang.String} <p>The constant <code>HEADER_TOKENS</code> field.</p>
     * @see java.lang.String
     */
    String[] HEADER_TOKENS = {"Authorization"};

}
