package io.github.nichetoolkit.rice.stereotype;

import org.springframework.stereotype.Indexed;

import java.lang.annotation.*;

/**
 * <p>RestUser 接口认证用户注解</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Indexed
public @interface RestUser {
}
