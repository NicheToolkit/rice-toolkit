package io.github.nichetoolkit.rice.stereotype;

import io.github.nichetoolkit.rice.mapper.SuperMapper;
import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Service;

import java.lang.annotation.*;

/**
 * <code>RestService</code>
 * <p>The rest service interface.</p>
 * @see  java.lang.annotation.Annotation
 * @see  java.lang.annotation.Target
 * @see  java.lang.annotation.Retention
 * @see  java.lang.annotation.Documented
 * @see  org.springframework.stereotype.Service
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Service
public @interface RestService {
    /**
     * <code>name</code>
     * <p>The name method.</p>
     * @return  {@link java.lang.String} <p>The name return object is <code>String</code> type.</p>
     * @see  java.lang.String
     * @see  org.springframework.core.annotation.AliasFor
     */
    @AliasFor(
            annotation = org.springframework.stereotype.Service.class,
            attribute = "value"
    )
    String name() default "";

    /**
     * <code>mapperType</code>
     * <p>The mapper type method.</p>
     * @return  {@link java.lang.Class} <p>The mapper type return object is <code>Class</code> type.</p>
     * @see  java.lang.Class
     */
    Class<? extends SuperMapper> mapperType() default SuperMapper.class;
}
