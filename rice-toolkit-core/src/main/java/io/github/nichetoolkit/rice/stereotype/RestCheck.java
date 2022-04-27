package io.github.nichetoolkit.rice.stereotype;

import java.lang.annotation.*;

/**
 * <p>RestCheck 校验注解</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RestCheck {
    String[] prefix() default {};
}
