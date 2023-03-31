package io.github.nichetoolkit.rice.stereotype.login;

import org.springframework.stereotype.Indexed;

import java.lang.annotation.*;

/**
 * <p>RestPending</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Indexed
public @interface RestPending {

    String type() default "";

    boolean update() default false;
}
