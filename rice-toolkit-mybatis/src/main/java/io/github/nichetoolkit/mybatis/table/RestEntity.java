package io.github.nichetoolkit.mybatis.table;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Indexed;

import java.lang.annotation.*;

/**
 * <code>RestEntity</code>
 * <p>The rest entity interface.</p>
 * @see  java.lang.annotation.Annotation
 * @see  java.lang.annotation.Retention
 * @see  java.lang.annotation.Target
 * @see  java.lang.annotation.Documented
 * @see  org.springframework.stereotype.Indexed
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.METHOD,ElementType.ANNOTATION_TYPE})
@Documented
@Indexed
public @interface RestEntity {
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
     * <code>comment</code>
     * <p>The comment method.</p>
     * @return  {@link java.lang.String} <p>The comment return object is <code>String</code> type.</p>
     * @see  java.lang.String
     */
    String comment() default "";

    /**
     * <code>alias</code>
     * <p>The alias method.</p>
     * @return  {@link java.lang.String} <p>The alias return object is <code>String</code> type.</p>
     * @see  java.lang.String
     */
    String alias() default "";

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
