package io.github.nichetoolkit.rice.stereotype;

import java.lang.annotation.*;

/**
 * <p>RestAccess 接口访问验证注解</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@RestLogin
public @interface RestAccess {
    String name() default "REST-ACCESS";
}
