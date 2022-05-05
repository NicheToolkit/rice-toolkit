package io.github.nichetoolkit.rice.stereotype;

import io.github.nichetoolkit.rest.RestArithmetic;
import io.github.nichetoolkit.rest.RestValue;
import io.github.nichetoolkit.rice.enums.PurviewType;
import io.github.nichetoolkit.rice.stereotype.ignored.DefaultModuleKeyValue;
import io.github.nichetoolkit.rice.stereotype.ignored.DefaultPurviewKeyValue;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * <p>RestModule</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@RestPurview
public @interface RestModule {

    @AliasFor("name")
    String key() default "";

    @AliasFor("key")
    String name() default "";

    Class<? extends RestValue<String,Long>> moduleType() default DefaultModuleKeyValue.class;

    @AliasFor(
            annotation = io.github.nichetoolkit.rice.stereotype.RestPurview.class,
            attribute = "value"
    )
    long value() default 0L;

    @AliasFor(
            annotation = io.github.nichetoolkit.rice.stereotype.RestPurview.class,
            attribute = "purviews"
    )
    PurviewType[] purviews() default {};

    @AliasFor(
            annotation = io.github.nichetoolkit.rice.stereotype.RestPurview.class,
            attribute = "purviewType"
    )
    Class<? extends RestArithmetic> purviewType() default DefaultPurviewKeyValue.class;


}
