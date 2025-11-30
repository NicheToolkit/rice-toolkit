package io.github.nichetoolkit.mybatis.column;


import org.springframework.stereotype.Indexed;

import java.lang.annotation.*;

/**
 * <code>RestOrdered</code>
 * <p>The rest ordered interface.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see java.lang.annotation.Annotation
 * @see java.lang.annotation.Retention
 * @see java.lang.annotation.Target
 * @see java.lang.annotation.Documented
 * @see org.springframework.stereotype.Indexed
 * @since Jdk17
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Documented
@Indexed
public @interface RestOrdered {
    /**
     * <code>value</code>
     * <p>The value method.</p>
     * @return int <p>The value return object is <code>int</code> type.</p>
     */
    int value() default 0;
}
