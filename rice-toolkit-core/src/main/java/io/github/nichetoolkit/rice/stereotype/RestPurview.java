package io.github.nichetoolkit.rice.stereotype;

import io.github.nichetoolkit.rest.RestArithmetic;
import io.github.nichetoolkit.rest.RestValue;
import io.github.nichetoolkit.rice.enums.PurviewType;
import io.github.nichetoolkit.rice.stereotype.ignored.DefaultModuleKeyValue;
import io.github.nichetoolkit.rice.stereotype.ignored.DefaultPurviewKeyValue;

import java.lang.annotation.*;

/**
 * <p>RestPurview</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@RestLogin
public @interface RestPurview {

    long value() default 0L;

    PurviewType[] purviews() default {};

    Class<? extends RestArithmetic> purviewType() default DefaultPurviewKeyValue.class;

}
