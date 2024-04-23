package io.github.nichetoolkit.rice.stereotype.mybatis.table;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Indexed;

import java.lang.annotation.*;

/**
 * <p>RestEntity</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.METHOD,ElementType.ANNOTATION_TYPE})
@Documented
@Indexed
public @interface RestEntity {
    /**
     * 表名，默认空时使用对象名
     */
    @AliasFor("name")
    String value() default "";

    /**
     * 表名，默认空时使用对象名
     */
    @AliasFor("value")
    String name() default "";

    /**
     * 备注
     */
    String remark() default "";

    /**
     * 实体类型
     */
    Class<?> entity() default Object.class;

}
