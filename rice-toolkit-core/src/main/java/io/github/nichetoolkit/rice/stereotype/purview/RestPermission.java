package io.github.nichetoolkit.rice.stereotype.purview;

import io.github.nichetoolkit.rest.RestArithmetic;
import io.github.nichetoolkit.rice.enums.PermissionType;
import io.github.nichetoolkit.rice.stereotype.ignored.DefaultPermissionKeyValue;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * <p>RestPermission</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RestPermission {

    @AliasFor("value")
    long permission() default 0L;

    @AliasFor("permission")
    long value() default 0L;

    PermissionType[] permissions() default {};

    Class<? extends RestArithmetic> permissionType() default DefaultPermissionKeyValue.class;

}
