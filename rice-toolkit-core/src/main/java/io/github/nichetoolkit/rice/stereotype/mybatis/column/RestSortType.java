package io.github.nichetoolkit.rice.stereotype.mybatis.column;


import io.github.nichetoolkit.rice.enums.SortType;
import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Indexed;

import java.lang.annotation.*;

/**
 * <p>RestOrderBy</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Documented
@Indexed
public @interface RestSortType {
    /**
     * 排序方式
     */
    @AliasFor("type")
    SortType value() default SortType.NONE;

    /**
     * 排序方式
     */
    @AliasFor("value")
    SortType type() default SortType.NONE;

    /**
     * 排序的优先级，数值越小优先级越高
     */
    int priority() default 0;
}
