package io.github.nichetoolkit.rice.stereotype.mybatis.column;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Indexed;

import java.lang.annotation.*;

/**
 * <p>RestExclude</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Documented
@Indexed
public @interface RestExclude {
    @AliasFor("exclude")
    boolean value() default true;

    @AliasFor("value")
    boolean exclude() default true;
}

