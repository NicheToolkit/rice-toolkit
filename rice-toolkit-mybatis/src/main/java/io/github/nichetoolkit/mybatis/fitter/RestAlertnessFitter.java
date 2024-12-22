package io.github.nichetoolkit.mybatis.fitter;

import io.github.nichetoolkit.mybatis.table.RestAlertness;
import io.github.nichetoolkit.rest.error.lack.InterfaceLackError;

/**
 * <code>RestAlertnessFitter</code>
 * <p>The rest alertness fitter interface.</p>
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public interface RestAlertnessFitter {

    /**
     * <code>supports</code>
     * <p>The supports method.</p>
     * @param alertnessType {@link java.lang.Class} <p>The alertness type parameter is <code>Class</code> type.</p>
     * @see  java.lang.Class
     * @return boolean <p>The supports return object is <code>boolean</code> type.</p>
     */
    default boolean supports(Class<?> alertnessType) {
        return alertnessType.isAnnotationPresent(RestAlertness.class);
    }

    /**
     * <code>sqlOfAlertness</code>
     * <p>The sql of alertness method.</p>
     * @return  {@link java.lang.String} <p>The sql of alertness return object is <code>String</code> type.</p>
     * @see  java.lang.String
     */
    default String sqlOfAlertness() {
        throw new InterfaceLackError("the method has not implemented, 'sqlOfAlertness()' ");
    }
}
