package io.github.nichetoolkit.mybatis.column;


import org.springframework.stereotype.Indexed;

import java.lang.annotation.*;

/**
 * <code>RestOrder</code>
 * <p>The rest order interface.</p>
 * @see  java.lang.annotation.Annotation
 * @see  java.lang.annotation.Retention
 * @see  java.lang.annotation.Target
 * @see  java.lang.annotation.Documented
 * @see  org.springframework.stereotype.Indexed
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Documented
@Indexed
public @interface RestOrder {
    /**
     * <code>value</code>
     * <p>The value method.</p>
     * @return int <p>The value return object is <code>int</code> type.</p>
     */
    int value() default 0;
}
