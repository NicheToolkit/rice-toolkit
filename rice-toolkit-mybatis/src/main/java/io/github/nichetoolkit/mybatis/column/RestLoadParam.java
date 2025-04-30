package io.github.nichetoolkit.mybatis.column;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Indexed;

import java.lang.annotation.*;

/**
 * <code>RestLoadParam</code>
 * <p>The rest load param interface.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see java.lang.annotation.Annotation
 * @see java.lang.annotation.Retention
 * @see java.lang.annotation.Target
 * @see java.lang.annotation.Documented
 * @see org.springframework.stereotype.Indexed
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
     * @return {@link java.lang.String} <p>The value return object is <code>String</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.core.annotation.AliasFor
     */
    @AliasFor("param")
    String value() default "";

    /**
     * <code>param</code>
     * <p>The param method.</p>
     * @return {@link java.lang.String} <p>The param return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    String param() default "";

    /**
     * <code>types</code>
     * <p>The types method.</p>
     * @return {@link java.lang.Class} <p>The types return object is <code>Class</code> type.</p>
     * @see java.lang.Class
     */
    Class<?>[] types() default {};

    /**
     * <code>keys</code>
     * <p>The keys method.</p>
     * @return {@link java.lang.String} <p>The keys return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    String[] keys() default {};


}

