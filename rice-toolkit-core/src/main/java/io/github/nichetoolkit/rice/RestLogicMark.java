package io.github.nichetoolkit.rice;

import io.github.nichetoolkit.rest.RestException;

/**
 * <code>RestLogicMark</code>
 * <p>The rest logic mark interface.</p>
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public interface RestLogicMark {

    /**
     * <code>getLogicMark</code>
     * <p>The get logic mark getter method.</p>
     * @return  {@link java.lang.Object} <p>The get logic mark return object is <code>Object</code> type.</p>
     * @see  java.lang.Object
     * @see  io.github.nichetoolkit.rest.RestException
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     */
    Object getLogicMark() throws RestException;

    /**
     * <code>getLogicUnmark</code>
     * <p>The get logic unmark getter method.</p>
     * @return  {@link java.lang.Object} <p>The get logic unmark return object is <code>Object</code> type.</p>
     * @see  java.lang.Object
     * @see  io.github.nichetoolkit.rest.RestException
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     */
    Object getLogicUnmark() throws RestException;
}
