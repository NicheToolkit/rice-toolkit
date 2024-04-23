package io.github.nichetoolkit.rice.stereotype.mybatis.table;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Indexed;

import java.lang.annotation.*;

/**
 * <p>RestUnionKeys</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Indexed
public @interface RestUnionKeys {

    /**
     * 联合主键的字段名称
     */
    @AliasFor("unionKeys")
    String[] value() default {};

    /**
     * 联合主键的字段名称
     */
    @AliasFor("value")
    String[] unionKeys() default {};


    String[] ignores() default {};

    /**
     * 是否将主键注解添加到联合主键
     */
    boolean unionIdentity() default true;

}
