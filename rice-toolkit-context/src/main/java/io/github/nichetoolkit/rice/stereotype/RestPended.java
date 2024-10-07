package io.github.nichetoolkit.rice.stereotype;

import org.springframework.stereotype.Indexed;

import java.lang.annotation.*;

/**
 * <code>RestPended</code>
 * <p>The type rest pended interface.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see java.lang.annotation.Annotation
 * @see java.lang.annotation.Target
 * @see java.lang.annotation.Retention
 * @see java.lang.annotation.Documented
 * @see org.springframework.stereotype.Indexed
 * @since Jdk1.8
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Indexed
public @interface RestPended {

    /**
     * <code>type</code>
     * <p>The method.</p>
     * @return {@link java.lang.String} <p>The return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    String type() default "";

    /**
     * <code>update</code>
     * <p>The method.</p>
     * @return boolean <p>The return object is <code>boolean</code> type.</p>
     */
    boolean update() default false;
}
