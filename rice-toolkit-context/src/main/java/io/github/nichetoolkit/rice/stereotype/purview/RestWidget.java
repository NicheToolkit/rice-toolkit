package io.github.nichetoolkit.rice.stereotype.purview;

import io.github.nichetoolkit.rice.stereotype.value.RestWidgetValue;
import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Indexed;

import java.lang.annotation.*;

@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Indexed
public @interface RestWidget {

    @AliasFor("value")
    String widget() default "";

    @AliasFor("widget")
    String value() default "";

    String[] widgets() default {};

    RestWidgetValue[] values() default {};

}
