package io.github.nichetoolkit.mybatis.column;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Indexed;

import java.lang.annotation.*;

/**
 * <code>RestLoadEntity</code>
 * <p>The rest load entity interface.</p>
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
public @interface RestLoadEntity {
    /**
     * <code>value</code>
     * <p>The value method.</p>
     * @return {@link java.lang.String} <p>The value return object is <code>String</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.core.annotation.AliasFor
     */
    @AliasFor("key")
    String value() default "";

    /**
     * <code>key</code>
     * <p>The key method.</p>
     * @return {@link java.lang.String} <p>The key return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    String key() default "";

    /**
     * <code>table</code>
     * <p>The table method.</p>
     * @return {@link java.lang.String} <p>The table return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    String table() default "";

    /**
     * <code>recursive</code>
     * <p>The recursive method.</p>
     * @return boolean <p>The recursive return object is <code>boolean</code> type.</p>
     */
    boolean recursive() default false;

}

