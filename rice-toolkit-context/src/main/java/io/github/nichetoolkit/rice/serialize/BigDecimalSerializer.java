package io.github.nichetoolkit.rice.serialize;

import tools.jackson.core.JacksonException;
import tools.jackson.core.JsonGenerator;
import tools.jackson.databind.BeanProperty;
import tools.jackson.databind.SerializationContext;
import tools.jackson.databind.ValueSerializer;
import io.github.nichetoolkit.rice.configure.RiceSerializeProperties;
import org.springframework.boot.jackson.JacksonComponent;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Objects;

/**
 * <code>BigDecimalSerializer</code>
 * <p>The big decimal serializer class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see tools.jackson.databind.ValueSerializer
 * @see org.springframework.boot.jackson.JacksonComponent
 * @since Jdk17
 */
@JacksonComponent
public class BigDecimalSerializer extends ValueSerializer<BigDecimal> {

    /**
     * <code>format</code>
     * {@link java.lang.String} <p>The <code>format</code> field.</p>
     * @see java.lang.String
     */
    private String format;
    /**
     * <code>serializeProperties</code>
     * {@link io.github.nichetoolkit.rice.configure.RiceSerializeProperties} <p>The <code>serializeProperties</code> field.</p>
     * @see io.github.nichetoolkit.rice.configure.RiceSerializeProperties
     */
    private final RiceSerializeProperties serializeProperties;

    /**
     * <code>BigDecimalSerializer</code>
     * <p>Instantiates a new big decimal serializer.</p>
     * @param serializeProperties {@link io.github.nichetoolkit.rice.configure.RiceSerializeProperties} <p>The serialize properties parameter is <code>RiceSerializeProperties</code> type.</p>
     * @see io.github.nichetoolkit.rice.configure.RiceSerializeProperties
     */
    public BigDecimalSerializer(RiceSerializeProperties serializeProperties) {
        this.format = serializeProperties.getBigDecimalFormat();
        this.serializeProperties = serializeProperties;
    }

    @Override
    public void serialize(BigDecimal bigDecimal, JsonGenerator jsonGenerator, SerializationContext serializerProvider) throws JacksonException {
        jsonGenerator.writeString(new DecimalFormat(format).format(bigDecimal));
    }

    @Override
    public ValueSerializer<?> createContextual(SerializationContext serializerProvider, BeanProperty beanProperty) {
        if(beanProperty !=null ){
            if(Objects.equals(beanProperty.getType().getRawClass(),BigDecimal.class)){
                BigDecimalFormat bigDecimalFormat = beanProperty.getAnnotation((BigDecimalFormat.class));
                if(bigDecimalFormat == null){
                    bigDecimalFormat = beanProperty.getContextAnnotation(BigDecimalFormat.class);
                }
                BigDecimalSerializer bigDecimalSerializer = new BigDecimalSerializer(this.serializeProperties);
                if(bigDecimalFormat != null){
                    bigDecimalSerializer.format = bigDecimalFormat.value();
                }
                return bigDecimalSerializer;
            }
            return serializerProvider.findValueSerializer(beanProperty.getType());
        }
        return serializerProvider.findNullValueSerializer(null);
    }
}