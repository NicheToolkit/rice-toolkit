package io.github.nichetoolkit.rice.stereotype;

import io.github.nichetoolkit.rest.userlog.LogType;
import io.github.nichetoolkit.rest.userlog.stereotype.RestUserlog;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>RiceUserlog</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@RestUserlog
public @interface RiceUserlog {
    @AliasFor(
            annotation = io.github.nichetoolkit.rest.userlog.stereotype.RestUserlog.class,
            attribute = "value"
    )
    String value() default "";

    @AliasFor(
            annotation = io.github.nichetoolkit.rest.userlog.stereotype.RestNotelog.class,
            attribute = "notelog"
    )
    String notelog() default "";

    @AliasFor(
            annotation = io.github.nichetoolkit.rest.userlog.stereotype.RestUserlog.class,
            attribute = "logKey"
    )
    String userlog() default "";

    @AliasFor(
            annotation = io.github.nichetoolkit.rest.userlog.stereotype.RestNotelog.class,
            attribute = "logKey"
    )
    int logKey() default 0;

    @AliasFor(
            annotation = io.github.nichetoolkit.rest.userlog.stereotype.RestUserlog.class,
            attribute = "logValue"
    )
    String logValue() default "";

    @AliasFor(
            annotation = io.github.nichetoolkit.rest.userlog.stereotype.RestUserlog.class,
            attribute = "logType"
    )
    LogType logType() default LogType.NONE;


    String userId() default "";


    String username() default "";
}
