package io.github.nichetoolkit.rice.stereotype;

import io.github.nichetoolkit.rest.RestArithmetic;
import io.github.nichetoolkit.rest.RestValue;
import io.github.nichetoolkit.rice.enums.PermissionType;
import io.github.nichetoolkit.rice.stereotype.ignored.DefaultModuleKeyValue;
import io.github.nichetoolkit.rice.stereotype.ignored.DefaultPermissionKeyValue;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * <p>RestModule</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@RestPermission
public @interface RestModule {

    @AliasFor("name")
    String key() default "";

    @AliasFor("key")
    String name() default "";

    Class<? extends RestValue<String,Long>> moduleType() default DefaultModuleKeyValue.class;

    @AliasFor(
            annotation = RestPermission.class,
            attribute = "value"
    )
    long value() default 0L;

    @AliasFor(
            annotation = RestPermission.class,
            attribute = "permissions"
    )
    PermissionType[] permissions() default {};

    @AliasFor(
            annotation = RestPermission.class,
            attribute = "permissionType"
    )
    Class<? extends RestArithmetic> permissionType() default DefaultPermissionKeyValue.class;


}
