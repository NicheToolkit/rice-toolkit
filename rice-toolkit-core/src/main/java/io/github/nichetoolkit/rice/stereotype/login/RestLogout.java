package io.github.nichetoolkit.rice.stereotype.login;

import org.springframework.stereotype.Indexed;

import java.lang.annotation.*;

/**
 * <p>RestLogout</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Indexed
public @interface RestLogout {
}
