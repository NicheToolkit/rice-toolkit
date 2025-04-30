package io.github.nichetoolkit.mybatis.column;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Indexed;

import java.lang.annotation.*;

/**
 * <code>RestLoadTable</code>
 * <p>The rest load table interface.</p>
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
public @interface RestLoadTable {
    /**
     * <code>value</code>
     * <p>The value method.</p>
     * @return {@link java.lang.String} <p>The value return object is <code>String</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.core.annotation.AliasFor
     */
    @AliasFor("format")
    String value() default "%s_%s";

    /**
     * <code>format</code>
     * <p>The format method.</p>
     * @return {@link java.lang.String} <p>The format return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    String format() default "%s_%s";

}

