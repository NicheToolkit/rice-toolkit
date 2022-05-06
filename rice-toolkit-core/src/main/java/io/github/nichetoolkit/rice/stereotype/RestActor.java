package io.github.nichetoolkit.rice.stereotype;

import io.github.nichetoolkit.rest.RestArithmetic;
import io.github.nichetoolkit.rice.enums.ActorType;
import io.github.nichetoolkit.rice.enums.PermissionType;
import io.github.nichetoolkit.rice.stereotype.ignored.DefaultPermissionKeyValue;
import io.github.nichetoolkit.rice.stereotype.ignored.DefaultActorKeyValue;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * <p>RestActor</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@RestPermission
public @interface RestActor {

    long actor() default 0;

    ActorType[] actors() default {};

    Class<? extends RestArithmetic> actorType() default DefaultActorKeyValue.class;

    @AliasFor(
            annotation = RestPermission.class,
            attribute = "permission"
    )
    long permission() default 0L;

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
