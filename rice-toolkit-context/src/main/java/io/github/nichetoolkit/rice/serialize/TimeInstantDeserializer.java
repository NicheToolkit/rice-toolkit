package io.github.nichetoolkit.rice.serialize;

import io.github.nichetoolkit.rice.time.TimeInstant;
import tools.jackson.core.JacksonException;
import tools.jackson.core.JsonParser;
import tools.jackson.databind.DeserializationContext;
import tools.jackson.databind.ValueDeserializer;

/**
 * <code>TimeInstantDeserializer</code>
 * <p>The time instant deserializer class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see tools.jackson.databind.ValueDeserializer
 * @since Jdk17
 */
public class TimeInstantDeserializer extends ValueDeserializer<TimeInstant> {
    @Override
    public TimeInstant deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws JacksonException {
        return TimeInstant.parse(jsonParser.getString());
    }
}
