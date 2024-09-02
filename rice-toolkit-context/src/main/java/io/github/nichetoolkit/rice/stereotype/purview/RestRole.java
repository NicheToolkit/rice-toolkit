package io.github.nichetoolkit.rice.stereotype.purview;

import io.github.nichetoolkit.rice.stereotype.value.RestRoleValue;
import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Indexed;

import java.lang.annotation.*;

/**
 * <code>RestRole</code>
 * <p>The type rest role interface.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see java.lang.annotation.Annotation
 * @see java.lang.annotation.Target
 * @see java.lang.annotation.Retention
 * @see java.lang.annotation.Documented
 * @see org.springframework.stereotype.Indexed
 * @since Jdk1.8
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Indexed
public @interface RestRole {

    /**
     * <code>role</code>
     * <p>the method.</p>
     * @return long <p>the return object is <code>long</code> type.</p>
     * @see org.springframework.core.annotation.AliasFor
     */
    @AliasFor("value")
    long role() default 0L;

    /**
     * <code>value</code>
     * <p>the method.</p>
     * @return long <p>the return object is <code>long</code> type.</p>
     * @see org.springframework.core.annotation.AliasFor
     */
    @AliasFor("role")
    long value() default 0L;

    /**
     * <code>roles</code>
     * <p>the method.</p>
     * @return long <p>the return object is <code>long</code> type.</p>
     */
    long[] roles() default {};

    /**
     * <code>values</code>
     * <p>the method.</p>
     * @return {@link io.github.nichetoolkit.rice.stereotype.value.RestRoleValue} <p>the return object is <code>RestRoleValue</code> type.</p>
     * @see io.github.nichetoolkit.rice.stereotype.value.RestRoleValue
     */
    RestRoleValue[] values() default {};

}
