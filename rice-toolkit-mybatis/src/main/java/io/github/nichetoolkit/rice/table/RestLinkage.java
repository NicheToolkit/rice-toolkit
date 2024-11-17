package io.github.nichetoolkit.rice.table;

import org.springframework.stereotype.Indexed;

import java.lang.annotation.*;

/**
 * <code>RestLinkage</code>
 * <p>The rest linkage interface.</p>
 * @see  java.lang.annotation.Annotation
 * @see  java.lang.annotation.Retention
 * @see  java.lang.annotation.Target
 * @see  java.lang.annotation.Documented
 * @see  org.springframework.stereotype.Indexed
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Indexed
public @interface RestLinkage {
}
