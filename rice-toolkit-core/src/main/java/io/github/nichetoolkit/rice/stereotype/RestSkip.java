package io.github.nichetoolkit.rice.stereotype;

import java.lang.annotation.*;

/**
 * <p>RestSkip 接口验证跳过注解</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public  @interface RestSkip {
}
