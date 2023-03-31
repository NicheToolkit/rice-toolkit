package io.github.nichetoolkit.rice.stereotype.purview;

import io.github.nichetoolkit.rice.stereotype.value.RestPurviewValue;
import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Indexed;

import java.lang.annotation.*;

/**
 * <p>RestPurview</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Indexed
public @interface RestPurview {

    @AliasFor("value")
    long purview() default 0L;

    @AliasFor("purview")
    long value() default 0L;

    long[] purviews() default {};

    RestPurviewValue[] values() default {};

}
