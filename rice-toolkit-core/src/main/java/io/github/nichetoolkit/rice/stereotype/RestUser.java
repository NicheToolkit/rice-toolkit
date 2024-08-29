package io.github.nichetoolkit.rice.stereotype;

import org.springframework.stereotype.Indexed;

import java.lang.annotation.*;

@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Indexed
public @interface RestUser {
}
