package io.github.nichetoolkit.rice.stereotype.mybatis;

import io.github.nichetoolkit.rice.enums.StyleType;
import io.github.nichetoolkit.rice.stereotype.mybatis.table.*;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * <p>RestTable</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@RestEntity
@RestStyle
@RestResultMap
@RestProperties
@RestExcludes
@RestUnionKeys
@RestLinkKeys
@RestUniqueKeys
@RestAlertKeys
public @interface RestTable {
    /**
     * 表名，默认空时使用对象名
     */
    @AliasFor(annotation = RestEntity.class, attribute = "name")
    String value() default "";

    /**
     * 备注，仅用于在注解上展示，不用于任何其他处理
     */
    @AliasFor(annotation = RestEntity.class, attribute = "remark")
    String remark() default "";

    /**
     * 备注，仅用于在注解上展示，不用于任何其他处理
     */
    @AliasFor(annotation = RestEntity.class, attribute = "entity")
    Class<?> entity() default Object.class;

    /**
     * 联合主键的字段名称
     */
    @AliasFor(annotation = RestUnionKeys.class, attribute = "unionKeys")
    String[] unionKeys() default {};

    /**
     * 是否将主键注解添加到联合主键
     */
    @AliasFor(annotation = RestUnionKeys.class, attribute = "unionIdentity")
    boolean unionIdentity() default false;

    /**
     * 联合主键的字段名称
     */
    @AliasFor(annotation = RestAlertKeys.class, attribute = "alertKeys")
    String[] alertKeys() default {};

    /**
     * 联合主键的字段名称
     */
    @AliasFor(annotation = RestLinkKeys.class, attribute = "linkKeys")
    String[] linkKeys() default {};

    /**
     * 联合主键的字段名称
     */
    @AliasFor(annotation = RestUniqueKeys.class, attribute = "uniqueKeys")
    String[] uniqueKeys() default {};

    /**
     * catalog 名称，配置后，会在表名前面加上 catalog名称
     * 默认使用 全局配置
     */
    @AliasFor(annotation = RestStyle.class, attribute = "catalog")
    String catalog() default "";

    /**
     * schema 名称，配置后，会在表名前面加上 schema 名称
     * 默认使用 全局配置
     */
    @AliasFor(annotation = RestStyle.class, attribute = "schema")
    String schema() default "";

    /**
     * 名称规则样式 默认小写加下划线 支持自定义
     * 默认使用 全局配置 LOWER_UNDERSCORE
     */
    @AliasFor(annotation = RestStyle.class, attribute = "name")
    String styleName() default "";

    /**
     * 名称规则样式 默认小写加下划线
     * 默认使用 全局配置 LOWER_UNDERSCORE
     */
    @AliasFor(annotation = RestStyle.class, attribute = "type")
    StyleType styleType() default StyleType.LOWER_UNDERLINE;

    /**
     * 使用指定的 <resultMap>
     */
    @AliasFor(annotation = RestResultMap.class, attribute = "name")
    String resultMap() default "";

    /**
     * 自动根据字段生成 <resultMap>
     */
    @AliasFor(annotation = RestResultMap.class, attribute = "autoResultMap")
    boolean autoResultMap() default true;

    /**
     * 属性配置
     */
    @AliasFor(annotation = RestProperties.class, attribute = "properties")
    RestProperty[] properties() default {};

    /**
     * 排除指定字段名的字段
     */
    @AliasFor(annotation = RestExcludes.class, attribute = "fields")
    String[] excludeFields() default {};

    /**
     * 排除指定类型的字段
     */
    @AliasFor(annotation = RestExcludes.class, attribute = "fieldTypes")
    Class<?>[] excludeFieldTypes() default {};

    /**
     * 排除指定父类的所有字段
     */
    @AliasFor(annotation = RestExcludes.class, attribute = "superClasses")
    Class<?>[] excludeSuperClasses() default {};
}
