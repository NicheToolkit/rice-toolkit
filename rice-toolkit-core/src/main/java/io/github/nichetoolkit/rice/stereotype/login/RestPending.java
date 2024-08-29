package io.github.nichetoolkit.rice.stereotype.login;

import org.springframework.stereotype.Indexed;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Indexed
public @interface RestPending {

    String type() default "";

    boolean update() default false;
}
