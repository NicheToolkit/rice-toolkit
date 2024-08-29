package io.github.nichetoolkit.rice.stereotype.value;

import org.springframework.stereotype.Indexed;

import java.lang.annotation.*;

@Target({ElementType.ANNOTATION_TYPE, ElementType.METHOD, ElementType.CONSTRUCTOR, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Indexed
public @interface RestWidgetValue {

    String widget() default "";

}
