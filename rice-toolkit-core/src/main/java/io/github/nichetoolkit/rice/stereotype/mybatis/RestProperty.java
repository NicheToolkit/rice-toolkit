package io.github.nichetoolkit.rice.stereotype.mybatis;

import org.springframework.stereotype.Indexed;

import java.lang.annotation.*;

/**
 * <p>RestProperty</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.ANNOTATION_TYPE})
@Documented
@Indexed
public @interface RestProperty {
    /**
     * 属性名
     */
    String name();

    /**
     * 属性值
     */
    String value();


}
