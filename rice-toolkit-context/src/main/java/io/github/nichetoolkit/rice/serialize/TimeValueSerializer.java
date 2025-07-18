package io.github.nichetoolkit.rice.serialize;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import io.github.nichetoolkit.rice.time.TimeValue;

import java.io.IOException;

/**
 * <code>TimeValueSerializer</code>
 * <p>The time value serializer class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see com.fasterxml.jackson.databind.JsonSerializer
 * @since Jdk1.8
 */
public class TimeValueSerializer extends JsonSerializer<TimeValue> {
    @Override
    public void serialize(TimeValue timeValue, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(timeValue.format());
    }
}
