package io.github.nichetoolkit.mybatis.table;

import org.springframework.stereotype.Indexed;

import java.lang.annotation.*;

/**
 * <code>RestIdentity</code>
 * <p>The rest identity interface.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see java.lang.annotation.Annotation
 * @see java.lang.annotation.Retention
 * @see java.lang.annotation.Target
 * @see java.lang.annotation.Documented
 * @see org.springframework.stereotype.Indexed
 * @since Jdk17
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Indexed
public @interface RestIdentity {
}
