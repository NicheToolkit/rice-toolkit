package io.github.nichetoolkit.rice.stereotype.mybatis.column;

import org.springframework.stereotype.Indexed;

import java.lang.annotation.*;

/**
 * <p>RestUnionKey</p>
 * 联合主键注解 单个配置不生效
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD,ElementType.ANNOTATION_TYPE})
@Documented
@Indexed
public @interface RestUnionKey {
    /**
     * 标记字段是否 用于联合主键字段
     */
    boolean value() default true;

    /**
     * 用于联合主键的顺序
     */
    int index() default 0;

}
