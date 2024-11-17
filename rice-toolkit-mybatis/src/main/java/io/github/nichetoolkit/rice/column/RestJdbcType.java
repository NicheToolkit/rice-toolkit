package io.github.nichetoolkit.rice.column;


import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;
import org.apache.ibatis.type.UnknownTypeHandler;
import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Indexed;

import java.lang.annotation.*;

/**
 * <code>RestJdbcType</code>
 * <p>The rest jdbc type interface.</p>
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
public @interface RestJdbcType {
    /**
     * <code>value</code>
     * <p>The value method.</p>
     * @return  {@link org.apache.ibatis.type.JdbcType} <p>The value return object is <code>JdbcType</code> type.</p>
     * @see  org.apache.ibatis.type.JdbcType
     * @see  org.springframework.core.annotation.AliasFor
     */
    @AliasFor("jdbcType")
    JdbcType value() default JdbcType.UNDEFINED;

    /**
     * <code>jdbcType</code>
     * <p>The jdbc type method.</p>
     * @return  {@link org.apache.ibatis.type.JdbcType} <p>The jdbc type return object is <code>JdbcType</code> type.</p>
     * @see  org.apache.ibatis.type.JdbcType
     * @see  org.springframework.core.annotation.AliasFor
     */
    @AliasFor("value")
    JdbcType jdbcType() default JdbcType.UNDEFINED;

    /**
     * <code>typeHandler</code>
     * <p>The type handler method.</p>
     * @return  {@link java.lang.Class} <p>The type handler return object is <code>Class</code> type.</p>
     * @see  java.lang.Class
     */
    Class<? extends TypeHandler<?>> typeHandler() default UnknownTypeHandler.class;

    /**
     * <code>numericScale</code>
     * <p>The numeric scale method.</p>
     * @return  {@link java.lang.String} <p>The numeric scale return object is <code>String</code> type.</p>
     * @see  java.lang.String
     */
    String numericScale() default "";
}
