package io.github.nichetoolkit.rice.stereotype.purview;

import io.github.nichetoolkit.rice.enums.ActorType;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * <p>RestActor</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RestActor {

    @AliasFor("value")
    long actor() default 0L;

    @AliasFor("actor")
    long value() default 0L;

    ActorType[] actors() default {};

}
