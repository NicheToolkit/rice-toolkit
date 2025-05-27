package io.github.nichetoolkit.mybatis.column;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Indexed;

import java.lang.annotation.*;

/**
 * <code>RestLoadEntity</code>
 * <p>The rest load entity interface.</p>
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
public @interface RestLoadEntity {
    /**
     * <code>value</code>
     * <p>The value method.</p>
     * @return int <p>The value return object is <code>int</code> type.</p>
     * @see  org.springframework.core.annotation.AliasFor
     */
    @AliasFor("index")
    int value() default 0;

    /**
     * <code>index</code>
     * <p>The index method.</p>
     * @return int <p>The index return object is <code>int</code> type.</p>
     */
    int index() default 0;

    /**
     * <code>key</code>
     * <p>The key method.</p>
     * @return  {@link java.lang.String} <p>The key return object is <code>String</code> type.</p>
     * @see  java.lang.String
     */
    String key() default "";

    /**
     * <code>table</code>
     * <p>The table method.</p>
     * @return  {@link java.lang.String} <p>The table return object is <code>String</code> type.</p>
     * @see  java.lang.String
     */
    String table() default "";

}

