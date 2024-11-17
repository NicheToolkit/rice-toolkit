package io.github.nichetoolkit.rice.table;

import org.springframework.stereotype.Indexed;

import java.lang.annotation.*;

/**
 * <code>RestProperty</code>
 * <p>The rest property interface.</p>
 * @see  java.lang.annotation.Annotation
 * @see  java.lang.annotation.Retention
 * @see  java.lang.annotation.Target
 * @see  java.lang.annotation.Documented
 * @see  org.springframework.stereotype.Indexed
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.ANNOTATION_TYPE})
@Documented
@Indexed
public @interface RestProperty {

    /**
     * <code>name</code>
     * <p>The name method.</p>
     * @return  {@link java.lang.String} <p>The name return object is <code>String</code> type.</p>
     * @see  java.lang.String
     */
    String name() default "";

    /**
     * <code>value</code>
     * <p>The value method.</p>
     * @return  {@link java.lang.String} <p>The value return object is <code>String</code> type.</p>
     * @see  java.lang.String
     */
    String value() default "";

}
