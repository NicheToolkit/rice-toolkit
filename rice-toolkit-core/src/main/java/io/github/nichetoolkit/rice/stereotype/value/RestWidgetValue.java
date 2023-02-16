package io.github.nichetoolkit.rice.stereotype.value;

import java.lang.annotation.*;

/**
 * <p>RestModuleValue</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Target({ElementType.ANNOTATION_TYPE, ElementType.METHOD, ElementType.CONSTRUCTOR, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RestWidgetValue {

    String widget() default "";

}
