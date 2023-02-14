package io.github.nichetoolkit.rice.stereotype.purview;

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
public @interface RestModule {

    @AliasFor("name")
    String module() default "";

    @AliasFor("module")
    String name() default "";

    String[] modules() default {};

}
