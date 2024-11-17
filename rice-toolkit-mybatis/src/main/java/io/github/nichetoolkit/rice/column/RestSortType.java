package io.github.nichetoolkit.rice.column;


import io.github.nichetoolkit.rice.enums.SortType;
import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Indexed;

import java.lang.annotation.*;

/**
 * <code>RestSortType</code>
 * <p>The rest sort type interface.</p>
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
public @interface RestSortType {
    /**
     * <code>value</code>
     * <p>The value method.</p>
     * @return  {@link io.github.nichetoolkit.rice.enums.SortType} <p>The value return object is <code>SortType</code> type.</p>
     * @see  io.github.nichetoolkit.rice.enums.SortType
     * @see  org.springframework.core.annotation.AliasFor
     */
    @AliasFor("type")
    SortType value() default SortType.NONE;

    /**
     * <code>type</code>
     * <p>The type method.</p>
     * @return  {@link io.github.nichetoolkit.rice.enums.SortType} <p>The type return object is <code>SortType</code> type.</p>
     * @see  io.github.nichetoolkit.rice.enums.SortType
     * @see  org.springframework.core.annotation.AliasFor
     */
    @AliasFor("value")
    SortType type() default SortType.NONE;

    /**
     * <code>priority</code>
     * <p>The priority method.</p>
     * @return int <p>The priority return object is <code>int</code> type.</p>
     */
    int priority() default 0;
}
