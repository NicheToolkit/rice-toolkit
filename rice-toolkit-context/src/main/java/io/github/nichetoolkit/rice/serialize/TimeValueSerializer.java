package io.github.nichetoolkit.rice.serialize;

import tools.jackson.core.JacksonException;
import tools.jackson.core.JsonGenerator;
import io.github.nichetoolkit.rice.time.TimeValue;
import tools.jackson.databind.SerializationContext;
import tools.jackson.databind.ValueSerializer;

/**
 * <code>TimeValueSerializer</code>
 * <p>The time value serializer class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see tools.jackson.databind.ValueSerializer
 * @since Jdk17
 */
public class TimeValueSerializer extends ValueSerializer<TimeValue> {
    @Override
    public void serialize(TimeValue timeValue, JsonGenerator jsonGenerator, SerializationContext serializerProvider) throws JacksonException {
        jsonGenerator.writeString(timeValue.format());
    }
}
