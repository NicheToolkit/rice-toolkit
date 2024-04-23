package io.github.nichetoolkit.rice.stereotype.mybatis.table;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Indexed;

import java.lang.annotation.*;

/**
 * <p>RestResultMap</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Indexed
public @interface RestResultMap {
    /**
     * 指定返回集合名称
     */
    @AliasFor("name")
    String value() default "";

    /**
     * 指定返回集合名称
     */
    @AliasFor("value")
    String name() default "";

    /**
     * 自动根据字段生成返回集合
     */
    boolean autoResultMap() default true;

}
