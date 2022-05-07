package io.github.nichetoolkit.rice.stereotype.purview;

import io.github.nichetoolkit.rest.RestArithmetic;
import io.github.nichetoolkit.rest.RestValue;
import io.github.nichetoolkit.rice.enums.ActorType;
import io.github.nichetoolkit.rice.enums.PermissionType;
import io.github.nichetoolkit.rice.stereotype.ignored.DefaultActorKeyValue;
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
@RestActor
public @interface RestModule {

    String module() default "";

    Class<? extends RestValue<String,Long>> moduleType() default DefaultModuleKeyValue.class;

    @AliasFor(
            annotation = RestActor.class,
            attribute = "actor"
    )
    long actor() default 0;

    @AliasFor(
            annotation = RestActor.class,
            attribute = "actors"
    )
    ActorType[] actors() default {};

    @AliasFor(
            annotation = RestActor.class,
            attribute = "actorType"
    )
    Class<? extends RestArithmetic> actorType() default DefaultActorKeyValue.class;

    @AliasFor(
            annotation = RestActor.class,
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
