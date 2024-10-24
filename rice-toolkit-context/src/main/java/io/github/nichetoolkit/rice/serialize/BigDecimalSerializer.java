package io.github.nichetoolkit.rice.serialize;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import io.github.nichetoolkit.rice.configure.RiceSerializeProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Objects;

/**
 * <code>BigDecimalSerializer</code>
 * <p>The big decimal serializer class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see com.fasterxml.jackson.databind.JsonSerializer
 * @see com.fasterxml.jackson.databind.ser.ContextualSerializer
 * @see org.springframework.boot.jackson.JsonComponent
 * @since Jdk1.8
 */
@JsonComponent
public class BigDecimalSerializer extends JsonSerializer<BigDecimal> implements ContextualSerializer {

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
     * @see org.springframework.beans.factory.annotation.Autowired
     */
    @Autowired
    public BigDecimalSerializer(RiceSerializeProperties serializeProperties) {
        this.format = serializeProperties.getBigDecimalFormat();
        this.serializeProperties = serializeProperties;
    }

    @Override
    public void serialize(BigDecimal bigDecimal, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(new DecimalFormat(format).format(bigDecimal));
    }

    @Override
    public JsonSerializer<?> createContextual(SerializerProvider serializerProvider, BeanProperty beanProperty) throws JsonMappingException {
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
            return serializerProvider.findValueSerializer(beanProperty.getType(),beanProperty);
        }
        return serializerProvider.findNullValueSerializer(null);
    }
}