package io.github.nichetoolkit.rice.stereotype;

import java.lang.annotation.*;

/**
 * <p>RestAuth</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RestAuth {
    String name() default "auth";
}
