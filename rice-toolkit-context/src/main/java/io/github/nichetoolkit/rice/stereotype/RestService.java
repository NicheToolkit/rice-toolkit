package io.github.nichetoolkit.rice.stereotype;

import io.github.nichetoolkit.rice.mapper.SuperMapper;
import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Service;

import java.lang.annotation.*;

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

    Class<? extends SuperMapper> mapper() default SuperMapper.class;
}
