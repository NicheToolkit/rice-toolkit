package io.github.nichetoolkit.rice.stereotype;

import io.github.nichetoolkit.rest.userlog.stereotype.RestNotelog;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * <p>RiceNotelog</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@RestNotelog
public @interface RiceNotelog {
    @AliasFor(
            annotation = io.github.nichetoolkit.rest.userlog.stereotype.RestNotelog.class,
            attribute = "value"
    )
    String value() default "";

    @AliasFor(
            annotation = io.github.nichetoolkit.rest.userlog.stereotype.RestNotelog.class,
            attribute = "notelog"
    )
    String notelog() default "";

    @AliasFor(
            annotation = io.github.nichetoolkit.rest.userlog.stereotype.RestNotelog.class,
            attribute = "logKey"
    )
    int logKey() default 0;
}
