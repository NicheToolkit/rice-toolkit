package io.github.nichetoolkit.mybatis;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * <code>RestMapper</code>
 * <p>The rest mapper interface.</p>
 * @see  java.lang.annotation.Annotation
 * @see  java.lang.annotation.Documented
 * @see  java.lang.annotation.Inherited
 * @see  java.lang.annotation.Retention
 * @see  java.lang.annotation.Target
 * @see  org.springframework.stereotype.Component
 * @see  org.apache.ibatis.annotations.Mapper
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Component
@Mapper
public @interface RestMapper {
    /**
     * <code>value</code>
     * <p>The value method.</p>
     * @return  {@link java.lang.String} <p>The value return object is <code>String</code> type.</p>
     * @see  java.lang.String
     * @see  org.springframework.core.annotation.AliasFor
     */
    @AliasFor(
            annotation = org.springframework.stereotype.Component.class
    )
    String value() default "";

    /**
     * <code>entityType</code>
     * <p>The entity type method.</p>
     * @return  {@link java.lang.Class} <p>The entity type return object is <code>Class</code> type.</p>
     * @see  java.lang.Class
     */
    Class<?> entityType() default Object.class;

    /**
     * <code>identityType</code>
     * <p>The identity type method.</p>
     * @return  {@link java.lang.Class} <p>The identity type return object is <code>Class</code> type.</p>
     * @see  java.lang.Class
     */
    Class<?> identityType() default Object.class;

    /**
     * <code>linkageType</code>
     * <p>The linkage type method.</p>
     * @return  {@link java.lang.Class} <p>The linkage type return object is <code>Class</code> type.</p>
     * @see  java.lang.Class
     */
    Class<?> linkageType() default Object.class;

    /**
     * <code>alertnessType</code>
     * <p>The alertness type method.</p>
     * @return  {@link java.lang.Class} <p>The alertness type return object is <code>Class</code> type.</p>
     * @see  java.lang.Class
     */
    Class<?> alertnessType() default Object.class;
}
