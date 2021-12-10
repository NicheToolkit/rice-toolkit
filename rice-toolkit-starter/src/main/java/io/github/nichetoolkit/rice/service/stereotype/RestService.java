package io.github.nichetoolkit.rice.service.stereotype;

import io.github.nichetoolkit.rice.mapper.IdMapper;
import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Service;

import java.lang.annotation.*;

/**
 * <p>RestService</p>
 * @author Cyan (snow22314 @ outlook.com)
 * @version v1.0.0
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Service
public @interface RestService {
    @AliasFor(
            annotation = org.springframework.stereotype.Service.class,
            attribute = "value"
    )
    String name() default "";

    Class<? extends IdMapper> mapper() default IdMapper.class;
}
