package io.github.nichetoolkit.rice.stereotype.value;

import org.springframework.stereotype.Indexed;

import java.lang.annotation.*;

/**
 * <code>RestPurviewValue</code>
 * <p>The type rest purview value interface.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see java.lang.annotation.Annotation
 * @see java.lang.annotation.Target
 * @see java.lang.annotation.Retention
 * @see java.lang.annotation.Documented
 * @see org.springframework.stereotype.Indexed
 * @since Jdk1.8
 */
@Target({ElementType.ANNOTATION_TYPE, ElementType.METHOD, ElementType.CONSTRUCTOR, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Indexed
public @interface RestPurviewValue {

    /**
     * <code>purview</code>
     * <p>the method.</p>
     * @return long <p>the return object is <code>long</code> type.</p>
     */
    long purview() default 0L;

}
