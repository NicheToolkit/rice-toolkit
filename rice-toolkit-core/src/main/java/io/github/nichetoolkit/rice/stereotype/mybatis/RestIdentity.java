package io.github.nichetoolkit.rice.stereotype.mybatis;

import org.springframework.stereotype.Indexed;

import java.lang.annotation.*;

/**
 * <p>RestIdentity</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Documented
@Indexed
public @interface RestIdentity {
}
