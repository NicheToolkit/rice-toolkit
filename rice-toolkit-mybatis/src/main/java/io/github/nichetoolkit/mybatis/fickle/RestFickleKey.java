package io.github.nichetoolkit.mybatis.fickle;

import org.springframework.stereotype.Indexed;

import java.lang.annotation.*;

/**
 * <code>RestFickleKey</code>
 * <p>The rest fickle key interface.</p>
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
public @interface RestFickleKey {
}

