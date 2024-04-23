package io.github.nichetoolkit.rice.stereotype.value;

import org.springframework.stereotype.Indexed;

import java.lang.annotation.*;

/**
 * <p>RestPurviewValue</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Target({ElementType.ANNOTATION_TYPE, ElementType.METHOD, ElementType.CONSTRUCTOR, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Indexed
public @interface RestPurviewValue {

    long purview() default 0L;

}
