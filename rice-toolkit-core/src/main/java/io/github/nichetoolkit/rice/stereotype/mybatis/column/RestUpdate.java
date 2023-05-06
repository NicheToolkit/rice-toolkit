package io.github.nichetoolkit.rice.stereotype.mybatis.column;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Indexed;

import java.lang.annotation.*;

/**
 * <p>RestUpdate</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Documented
@Indexed
public @interface RestUpdate {
    @AliasFor("update")
    boolean value() default true;

    @AliasFor("value")
    boolean update() default true;

}

