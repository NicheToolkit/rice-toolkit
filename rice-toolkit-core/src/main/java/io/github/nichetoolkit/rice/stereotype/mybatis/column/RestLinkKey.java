package io.github.nichetoolkit.rice.stereotype.mybatis.column;

import org.springframework.stereotype.Indexed;

import java.lang.annotation.*;

/**
 * <p>RestLinkKey</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Documented
@Indexed
public @interface RestLinkKey {
    /**
     * 标记字段是否 用于联合主键字段
     */
    boolean value() default true;

}

