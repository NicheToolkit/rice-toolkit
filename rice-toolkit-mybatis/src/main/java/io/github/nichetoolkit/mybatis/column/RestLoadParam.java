package io.github.nichetoolkit.mybatis.column;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Indexed;

import java.lang.annotation.*;

/**
 * <code>RestLoadParam</code>
 * <p>The rest load param interface.</p>
 * @see  java.lang.annotation.Annotation
 * @see  java.lang.annotation.Retention
 * @see  java.lang.annotation.Target
 * @see  java.lang.annotation.Documented
 * @see  org.springframework.stereotype.Indexed
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
@Indexed
public @interface RestLoadParam {

    /**
     * <code>value</code>
     * <p>The value method.</p>
     * @return  {@link java.lang.Class} <p>The value return object is <code>Class</code> type.</p>
     * @see  java.lang.Class
     */
    Class[] value() default {};

    /**
     * <code>column</code>
     * <p>The column method.</p>
     * @return  {@link java.lang.String} <p>The column return object is <code>String</code> type.</p>
     * @see  java.lang.String
     */
    String column() default "";
}

