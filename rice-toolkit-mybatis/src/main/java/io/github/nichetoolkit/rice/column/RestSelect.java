package io.github.nichetoolkit.rice.column;

import org.springframework.stereotype.Indexed;

import java.lang.annotation.*;

/**
 * <code>RestSelect</code>
 * <p>The rest select interface.</p>
 * @see  java.lang.annotation.Annotation
 * @see  java.lang.annotation.Retention
 * @see  java.lang.annotation.Target
 * @see  java.lang.annotation.Documented
 * @see  org.springframework.stereotype.Indexed
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD,ElementType.ANNOTATION_TYPE})
@Documented
@Indexed
public @interface RestSelect {
    /**
     * <code>value</code>
     * <p>The value method.</p>
     * @return boolean <p>The value return object is <code>boolean</code> type.</p>
     */
    boolean value() default true;
}

