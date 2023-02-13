package io.github.nichetoolkit.rice.stereotype.purview;

import io.github.nichetoolkit.rice.enums.PermitType;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * <p>RestPermit</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RestPermit {

    @AliasFor("value")
    long permit() default 0L;

    @AliasFor("permit")
    long value() default 0L;

    long[] permits() default {};

    PermitType[] types() default {};

}
