package io.github.nichetoolkit.rice.stereotype.purview;

import io.github.nichetoolkit.rest.stereotype.StereoValue;
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
@RestPermission
public @interface RestRole {

    /**
     * <code>key</code>
     * <p>the method.</p>
     * @return {@link java.lang.String} <p>the return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    @AliasFor(
            annotation = RestPermission.class,
            attribute = "key"
    )
    String key() default "";

    /**
     * <code>keys</code>
     * <p>the method.</p>
     * @return {@link java.lang.String} <p>the return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    @AliasFor(
            annotation = RestPermission.class,
            attribute = "keys"
    )
    String[] keys() default {};

    /**
     * <code>value</code>
     * <p>the method.</p>
     * @return long <p>the return object is <code>long</code> type.</p>
     */
    @AliasFor(
            annotation = RestPermission.class,
            attribute = "value"
    )
    long value() default 0L;

    /**
     * <code>values</code>
     * <p>the method.</p>
     * @return long <p>the return object is <code>long</code> type.</p>
     */
    @AliasFor(
            annotation = RestPermission.class,
            attribute = "values"
    )
    long[] values() default {};

    /**
     * <code>role</code>
     * <p>the method.</p>
     * @return {@link io.github.nichetoolkit.rest.stereotype.StereoValue} <p>the return object is <code>StereoValue</code> type.</p>
     * @see io.github.nichetoolkit.rest.stereotype.StereoValue
     */
    @AliasFor(
            annotation = RestPermission.class,
            attribute = "permission"
    )
    StereoValue role() default @StereoValue;

    /**
     * <code>roles</code>
     * <p>the method.</p>
     * @return {@link io.github.nichetoolkit.rest.stereotype.StereoValue} <p>the return object is <code>StereoValue</code> type.</p>
     * @see io.github.nichetoolkit.rest.stereotype.StereoValue
     */
    @AliasFor(
            annotation = RestPermission.class,
            attribute = "permissions"
    )
    StereoValue[] roles() default {};
}
