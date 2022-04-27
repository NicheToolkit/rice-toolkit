package io.github.nichetoolkit.rice.stereotype;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * <p>RestPending</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@RestLogin
public @interface RestPending {

    @AliasFor(
            annotation = io.github.nichetoolkit.rice.stereotype.RestLogin.class,
            attribute = "type"
    )
    String type() default "";

    @AliasFor(
            annotation = io.github.nichetoolkit.rice.stereotype.RestLogin.class,
            attribute = "update"
    )
    boolean update() default false;
}
