package io.github.nichetoolkit.rice.serialize;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rice.time.TimeInterval;

import java.io.IOException;

/**
 * <code>TimeIntervalSerializer</code>
 * <p>The time interval serializer class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see com.fasterxml.jackson.databind.JsonSerializer
 * @since Jdk1.8
 */
public class TimeIntervalSerializer extends JsonSerializer<TimeInterval> {
    @Override
    public void serialize(TimeInterval timeInterval, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (GeneralUtils.isNotEmpty(timeInterval)) {
            jsonGenerator.writeString(timeInterval.format());
        }
    }
}
