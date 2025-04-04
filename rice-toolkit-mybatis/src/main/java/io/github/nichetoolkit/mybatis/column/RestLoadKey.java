package io.github.nichetoolkit.mybatis.column;

import io.github.nichetoolkit.mybatis.consts.EntityConstants;
import org.springframework.stereotype.Indexed;

import java.lang.annotation.*;

/**
 * <code>RestLoadKey</code>
 * <p>The rest load key interface.</p>
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
public @interface RestLoadKey {
    /**
     * <code>value</code>
     * <p>The value method.</p>
     * @return  {@link java.lang.Class} <p>The value return object is <code>Class</code> type.</p>
     * @see  java.lang.Class
     */
    Class<?> value();

    /**
     * <code>column</code>
     * <p>The column method.</p>
     * @return  {@link java.lang.String} <p>The column return object is <code>String</code> type.</p>
     * @see  java.lang.String
     */
    String column() default "";
}

