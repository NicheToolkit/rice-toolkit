package io.github.nichetoolkit.rice.stereotype.mybatis.table;

import io.github.nichetoolkit.rice.enums.StyleType;
import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Indexed;

import java.lang.annotation.*;

/**
 * <p>RestStyle</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Indexed
public @interface RestStyle {
    /**
     * 表名，默认空时使用对象名
     */
    @AliasFor("name")
    String value() default "";

    /**
     * 名称规则样式 默认小写加下划线 支持自定义
     * 默认使用 全局配置 LOWER_UNDERSCORE
     */
    @AliasFor("value")
    String name() default "";

    /**
     * 名称规则样式 默认小写加下划线
     * 默认使用 全局配置 LOWER_UNDERSCORE
     */
    StyleType type() default StyleType.LOWER_UNDERLINE;

    /**
     * catalog 名称，配置后，会在表名前面加上 catalog名称
     * 默认使用 全局配置
     */
    String catalog() default "";

    /**
     * schema 名称，配置后，会在表名前面加上 schema 名称
     * 默认使用 全局配置
     */
    String schema() default "";


}
