package io.github.nichetoolkit.rice.stereotype.purview;

import io.github.nichetoolkit.rice.stereotype.value.RestModuleValue;
import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Indexed;

import java.lang.annotation.*;

@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Indexed
public @interface RestModule {

    @AliasFor("value")
    String module() default "";

    @AliasFor("module")
    String value() default "";

    String[] modules() default {};

    RestModuleValue[] values() default {};

}
