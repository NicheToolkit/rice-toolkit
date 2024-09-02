package io.github.nichetoolkit.rice.serialize;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.deser.std.NumberDeserializers;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <code>BigDecimalFormat</code>
 * <p>The type big decimal format interface.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see java.lang.annotation.Annotation
 * @see java.lang.annotation.Retention
 * @see java.lang.annotation.Target
 * @see com.fasterxml.jackson.annotation.JacksonAnnotationsInside
 * @see com.fasterxml.jackson.databind.annotation.JsonSerialize
 * @see com.fasterxml.jackson.databind.annotation.JsonDeserialize
 * @since Jdk1.8
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@JacksonAnnotationsInside
@JsonSerialize(using = BigDecimalSerializer.class)
@JsonDeserialize(using = NumberDeserializers.BigDecimalDeserializer.class)
public @interface BigDecimalFormat {
    /**
     * <code>value</code>
     * <p>the method.</p>
     * @return {@link java.lang.String} <p>the return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    String value() default "0.00";
}
