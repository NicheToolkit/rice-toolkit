package io.github.nichetoolkit.rice.stereotype.mybatis.table;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Indexed;

import java.lang.annotation.*;

/**
 * <p>RestUniques</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Indexed
public @interface RestUniques {

    @AliasFor("uniques")
    String[] value() default {};

    @AliasFor("value")
    String[] uniques() default {};


    String[] ignores() default {};

}
