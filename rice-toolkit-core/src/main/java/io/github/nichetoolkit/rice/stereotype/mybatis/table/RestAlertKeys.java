package io.github.nichetoolkit.rice.stereotype.mybatis.table;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Indexed;

import java.lang.annotation.*;

/**
 * <p>RestAlertKeys</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Indexed
public @interface RestAlertKeys {

    @AliasFor("alertKeys")
    String[] value() default {};

    @AliasFor("value")
    String[] alertKeys() default {};


    String[] ignores() default {};

}
