package io.github.nichetoolkit.rice.table;

import org.springframework.stereotype.Indexed;

import java.lang.annotation.*;

/**
 * <code>RestUniqueKeys</code>
 * <p>The rest unique keys interface.</p>
 * @see  java.lang.annotation.Annotation
 * @see  java.lang.annotation.Retention
 * @see  java.lang.annotation.Target
 * @see  java.lang.annotation.Documented
 * @see  org.springframework.stereotype.Indexed
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Indexed
public @interface RestUniqueKeys {
    /**
     * <code>value</code>
     * <p>The value method.</p>
     * @return  {@link java.lang.String} <p>The value return object is <code>String</code> type.</p>
     * @see  java.lang.String
     */
    String[] value() default {};
}
