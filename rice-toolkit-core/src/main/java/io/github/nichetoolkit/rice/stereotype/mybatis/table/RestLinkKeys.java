package io.github.nichetoolkit.rice.stereotype.mybatis.table;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Indexed;

import java.lang.annotation.*;

/**
 * <p>RestLinkKeys</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Indexed
public @interface RestLinkKeys {

    /**
     * 联合主键的字段名称
     */
    @AliasFor("linkKeys")
    String[] value() default {};

    /**
     * 联合主键的字段名称
     */
    @AliasFor("value")
    String[] linkKeys() default {};


    String[] ignores() default {};

}
