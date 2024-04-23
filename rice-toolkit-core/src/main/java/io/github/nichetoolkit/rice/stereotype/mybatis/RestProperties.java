package io.github.nichetoolkit.rice.stereotype.mybatis;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Indexed;

import java.lang.annotation.*;

/**
 * <p>RestProperties</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.FIELD,ElementType.METHOD})
@Documented
@Indexed
public @interface RestProperties {
    /**
     * 配置的 RestProperty 集合
     */
    @AliasFor("properties")
    RestProperty[] value() default {};

    /**
     * 配置的 RestProperty 集合
     */
    RestProperty[] properties() default {};
}
