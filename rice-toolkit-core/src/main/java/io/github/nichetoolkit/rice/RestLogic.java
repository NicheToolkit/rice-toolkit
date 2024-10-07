package io.github.nichetoolkit.rice;

import java.io.Serializable;

/**
 * <code>RestLogic</code>
 * <p>The type rest logic interface.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see java.io.Serializable
 * @since Jdk1.8
 */
public interface RestLogic extends Serializable {

    /**
     * <code>getLogic</code>
     * <p>The logic getter method.</p>
     * @return {@link java.lang.String} <p>The logic return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    String getLogic();

    /**
     * <code>setLogic</code>
     * <p>The logic setter method.</p>
     * @param logic {@link java.lang.String} <p>The logic parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    void setLogic(String logic);

}
