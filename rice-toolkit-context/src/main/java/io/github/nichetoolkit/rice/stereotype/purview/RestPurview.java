package io.github.nichetoolkit.rice.stereotype.purview;

import io.github.nichetoolkit.rice.stereotype.value.RestPurviewValue;
import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Indexed;

import java.lang.annotation.*;

/**
 * <code>RestPurview</code>
 * <p>The type rest purview interface.</p>
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
public @interface RestPurview {

    /**
     * <code>purview</code>
     * <p>the method.</p>
     * @return long <p>the return object is <code>long</code> type.</p>
     * @see org.springframework.core.annotation.AliasFor
     */
    @AliasFor("value")
    long purview() default 0L;

    /**
     * <code>value</code>
     * <p>the method.</p>
     * @return long <p>the return object is <code>long</code> type.</p>
     * @see org.springframework.core.annotation.AliasFor
     */
    @AliasFor("purview")
    long value() default 0L;

    /**
     * <code>purviews</code>
     * <p>the method.</p>
     * @return long <p>the return object is <code>long</code> type.</p>
     */
    long[] purviews() default {};

    /**
     * <code>values</code>
     * <p>the method.</p>
     * @return {@link io.github.nichetoolkit.rice.stereotype.value.RestPurviewValue} <p>the return object is <code>RestPurviewValue</code> type.</p>
     * @see io.github.nichetoolkit.rice.stereotype.value.RestPurviewValue
     */
    RestPurviewValue[] values() default {};

}
