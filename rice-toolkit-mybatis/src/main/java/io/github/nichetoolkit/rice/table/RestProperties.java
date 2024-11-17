package io.github.nichetoolkit.rice.table;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Indexed;

import java.lang.annotation.*;

/**
 * <code>RestProperties</code>
 * <p>The rest properties interface.</p>
 * @see  java.lang.annotation.Annotation
 * @see  java.lang.annotation.Retention
 * @see  java.lang.annotation.Target
 * @see  java.lang.annotation.Documented
 * @see  org.springframework.stereotype.Indexed
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.FIELD,ElementType.METHOD})
@Documented
@Indexed
public @interface RestProperties {
    /**
     * <code>value</code>
     * <p>The value method.</p>
     * @return  {@link io.github.nichetoolkit.rice.table.RestProperty} <p>The value return object is <code>RestProperty</code> type.</p>
     * @see  io.github.nichetoolkit.rice.table.RestProperty
     * @see  org.springframework.core.annotation.AliasFor
     */
    @AliasFor("properties")
    RestProperty[] value() default {};

    /**
     * <code>properties</code>
     * <p>The properties method.</p>
     * @return  {@link io.github.nichetoolkit.rice.table.RestProperty} <p>The properties return object is <code>RestProperty</code> type.</p>
     * @see  io.github.nichetoolkit.rice.table.RestProperty
     * @see  org.springframework.core.annotation.AliasFor
     */
    @AliasFor("value")
    RestProperty[] properties() default {};
}
