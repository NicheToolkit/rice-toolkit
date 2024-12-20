package io.github.nichetoolkit.mybatis.table;

import io.github.nichetoolkit.mybatis.enums.StyleType;
import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Indexed;

import java.lang.annotation.*;

/**
 * <code>RestTableStyle</code>
 * <p>The rest table style interface.</p>
 * @see  java.lang.annotation.Annotation
 * @see  java.lang.annotation.Retention
 * @see  java.lang.annotation.Target
 * @see  java.lang.annotation.Documented
 * @see  org.springframework.stereotype.Indexed
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Indexed
public @interface RestTableStyle {
    /**
     * <code>value</code>
     * <p>The value method.</p>
     * @return  {@link java.lang.String} <p>The value return object is <code>String</code> type.</p>
     * @see  java.lang.String
     * @see  org.springframework.core.annotation.AliasFor
     */
    @AliasFor("name")
    String value() default "";

    /**
     * <code>name</code>
     * <p>The name method.</p>
     * @return  {@link java.lang.String} <p>The name return object is <code>String</code> type.</p>
     * @see  java.lang.String
     * @see  org.springframework.core.annotation.AliasFor
     */
    @AliasFor("value")
    String name() default "";

    /**
     * <code>catalog</code>
     * <p>The catalog method.</p>
     * @return  {@link java.lang.String} <p>The catalog return object is <code>String</code> type.</p>
     * @see  java.lang.String
     */
    String catalog() default "";

    /**
     * <code>schema</code>
     * <p>The schema method.</p>
     * @return  {@link java.lang.String} <p>The schema return object is <code>String</code> type.</p>
     * @see  java.lang.String
     */
    String schema() default "";

    /**
     * <code>type</code>
     * <p>The type method.</p>
     * @return  {@link io.github.nichetoolkit.mybatis.enums.StyleType} <p>The type return object is <code>StyleType</code> type.</p>
     * @see  io.github.nichetoolkit.mybatis.enums.StyleType
     */
    StyleType type() default StyleType.LOWER_UNDERLINE;

}
