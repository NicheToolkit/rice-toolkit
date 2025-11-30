package io.github.nichetoolkit.rice.serialize;

import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rice.time.TimeInstant;
import tools.jackson.core.JacksonException;
import tools.jackson.core.JsonGenerator;
import tools.jackson.databind.SerializationContext;
import tools.jackson.databind.ValueSerializer;

/**
 * <code>TimeInstantSerializer</code>
 * <p>The time instant serializer class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see tools.jackson.databind.ValueSerializer
 * @since Jdk17
 */
public class TimeInstantSerializer extends ValueSerializer<TimeInstant> {
    @Override
    public void serialize(TimeInstant timeInstant, JsonGenerator jsonGenerator, SerializationContext serializerProvider) throws JacksonException {
        if (GeneralUtils.isNotEmpty(timeInstant)) {
            jsonGenerator.writeString(timeInstant.format());
        }
    }
}
