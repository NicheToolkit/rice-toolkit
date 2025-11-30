package io.github.nichetoolkit.rice.serialize;

import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rice.time.TimeInterval;
import tools.jackson.core.JacksonException;
import tools.jackson.core.JsonGenerator;
import tools.jackson.databind.SerializationContext;
import tools.jackson.databind.ValueSerializer;

/**
 * <code>TimeIntervalSerializer</code>
 * <p>The time interval serializer class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see tools.jackson.databind.ValueSerializer
 * @since Jdk17
 */
public class TimeIntervalSerializer extends ValueSerializer<TimeInterval> {
    @Override
    public void serialize(TimeInterval timeInterval, JsonGenerator jsonGenerator, SerializationContext serializerProvider) throws JacksonException {
        if (GeneralUtils.isNotEmpty(timeInterval)) {
            jsonGenerator.writeString(timeInterval.format());
        }
    }
}
