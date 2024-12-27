package io.github.nichetoolkit.rice.stereotype;

import org.springframework.stereotype.Indexed;

import java.lang.annotation.*;

/**
 * <code>RestPended</code>
 * <p>The rest pended interface.</p>
 * @see  java.lang.annotation.Annotation
 * @see  java.lang.annotation.Target
 * @see  java.lang.annotation.Retention
 * @see  java.lang.annotation.Documented
 * @see  org.springframework.stereotype.Indexed
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Indexed
public @interface RestPended {

    /**
     * <code>type</code>
     * <p>The type method.</p>
     * @return  {@link java.lang.String} <p>The type return object is <code>String</code> type.</p>
     * @see  java.lang.String
     */
    String type() default "";

    /**
     * <code>update</code>
     * <p>The update method.</p>
     * @return boolean <p>The update return object is <code>boolean</code> type.</p>
     */
    boolean update() default false;
}
