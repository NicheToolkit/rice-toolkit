package io.github.nichetoolkit.rice.stereotype;

import io.github.nichetoolkit.rest.RestArithmetic;

import java.lang.annotation.*;

/**
 * <p>RestPurview</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@RestLogin
public @interface RestPurview {

    String name() default "";

    long value() default 0L;

    Class<? extends RestArithmetic> type() default RestArithmetic.class;

}
