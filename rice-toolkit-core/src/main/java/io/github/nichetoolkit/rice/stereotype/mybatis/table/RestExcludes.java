package io.github.nichetoolkit.rice.stereotype.mybatis.table;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Indexed;

import java.lang.annotation.*;

/**
 * <p>RestExclude</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Indexed
public @interface RestExcludes {

    /**
     * 排除指定字段名的字段
     */
    @AliasFor("fields")
    String[] value() default {};

    /**
     * 排除指定字段名的字段
     */
    @AliasFor("value")
    String[] fields() default {};


    String[] ignores() default {};

    /**
     * 排除指定类型的字段
     */
    Class<?>[] fieldTypes() default {};

    /**
     * 排除指定父类的所有字段
     */
    Class<?>[] superClasses() default {};
}
