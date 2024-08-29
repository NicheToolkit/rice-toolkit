package io.github.nichetoolkit.rice.stereotype.purview;

import io.github.nichetoolkit.rice.stereotype.value.RestRoleValue;
import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Indexed;

import java.lang.annotation.*;

@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Indexed
public @interface RestRole {

    @AliasFor("value")
    long role() default 0L;

    @AliasFor("role")
    long value() default 0L;

    long[] roles() default {};

    RestRoleValue[] values() default {};

}
