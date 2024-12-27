package io.github.nichetoolkit.rice.stereotype;

import org.springframework.stereotype.Indexed;

import java.lang.annotation.*;

/**
 * <code>RestUser</code>
 * <p>The rest user interface.</p>
 * @see  java.lang.annotation.Annotation
 * @see  java.lang.annotation.Target
 * @see  java.lang.annotation.Retention
 * @see  java.lang.annotation.Documented
 * @see  org.springframework.stereotype.Indexed
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Indexed
public @interface RestUser {
}
