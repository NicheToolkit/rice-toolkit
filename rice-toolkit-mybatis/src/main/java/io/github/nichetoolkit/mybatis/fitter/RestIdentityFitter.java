package io.github.nichetoolkit.mybatis.fitter;

import io.github.nichetoolkit.mybatis.table.RestIdentity;
import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.error.lack.InterfaceLackError;
import io.github.nichetoolkit.rest.error.natives.UnsupportedErrorException;

/**
 * <code>RestIdentityFitter</code>
 * <p>The rest identity fitter interface.</p>
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public interface RestIdentityFitter {

    /**
     * <code>supports</code>
     * <p>The supports method.</p>
     * @param identityType {@link java.lang.Class} <p>The identity type parameter is <code>Class</code> type.</p>
     * @see  java.lang.Class
     * @return boolean <p>The supports return object is <code>boolean</code> type.</p>
     */
    default boolean supports(Class<?> identityType) {
        return identityType.isAnnotationPresent(RestIdentity.class);
    }

    /**
     * <code>sqlOfIdentity</code>
     * <p>The sql of identity method.</p>
     * @return  {@link java.lang.String} <p>The sql of identity return object is <code>String</code> type.</p>
     * @see  java.lang.String
     */
    default String sqlOfIdentity() {
        throw new InterfaceLackError("the method has not implemented, 'sqlOfIdentity()' ");
    }
}
