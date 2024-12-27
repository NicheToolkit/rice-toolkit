package io.github.nichetoolkit.rice;

import java.io.Serializable;

/**
 * <code>RestLogic</code>
 * <p>The rest logic interface.</p>
 * @see  java.io.Serializable
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public interface RestLogic extends Serializable {

    /**
     * <code>getLogic</code>
     * <p>The get logic getter method.</p>
     * @return  {@link java.lang.Object} <p>The get logic return object is <code>Object</code> type.</p>
     * @see  java.lang.Object
     */
    Object getLogic();

    /**
     * <code>setLogic</code>
     * <p>The set logic setter method.</p>
     * @param logic {@link java.lang.Object} <p>The logic parameter is <code>Object</code> type.</p>
     * @see  java.lang.Object
     */
    void setLogic(Object logic);

}
