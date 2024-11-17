package io.github.nichetoolkit.rice.table;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Indexed;

import java.lang.annotation.*;

/**
 * <code>RestIgnores</code>
 * <p>The rest ignores interface.</p>
 * @see  java.lang.annotation.Annotation
 * @see  java.lang.annotation.Retention
 * @see  java.lang.annotation.Target
 * @see  java.lang.annotation.Documented
 * @see  org.springframework.stereotype.Indexed
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Indexed
public @interface RestIgnores {
    /**
     * <code>value</code>
     * <p>The value method.</p>
     * @return  {@link java.lang.String} <p>The value return object is <code>String</code> type.</p>
     * @see  java.lang.String
     * @see  org.springframework.core.annotation.AliasFor
     */
    @AliasFor("fields")
    String[] value() default {};

    /**
     * <code>fields</code>
     * <p>The fields method.</p>
     * @return  {@link java.lang.String} <p>The fields return object is <code>String</code> type.</p>
     * @see  java.lang.String
     * @see  org.springframework.core.annotation.AliasFor
     */
    @AliasFor("value")
    String[] fields() default {};

    /**
     * <code>fieldTypes</code>
     * <p>The field types method.</p>
     * @return  {@link java.lang.Class} <p>The field types return object is <code>Class</code> type.</p>
     * @see  java.lang.Class
     */
    Class<?>[] fieldTypes() default {};

    /**
     * <code>superClasses</code>
     * <p>The super classes method.</p>
     * @return  {@link java.lang.Class} <p>The super classes return object is <code>Class</code> type.</p>
     * @see  java.lang.Class
     */
    Class<?>[] superClasses() default {};
}
