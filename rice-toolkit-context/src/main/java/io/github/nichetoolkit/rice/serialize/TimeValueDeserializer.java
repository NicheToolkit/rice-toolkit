package io.github.nichetoolkit.rice.serialize;

import tools.jackson.core.JacksonException;
import tools.jackson.core.JsonParser;
import tools.jackson.databind.DeserializationContext;
import io.github.nichetoolkit.rice.time.TimeValue;
import tools.jackson.databind.ValueDeserializer;

/**
 * <code>TimeValueDeserializer</code>
 * <p>The time value deserializer class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see tools.jackson.databind.ValueDeserializer
 * @since Jdk17
 */
public class TimeValueDeserializer extends ValueDeserializer<TimeValue> {
    @Override
    public TimeValue deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws JacksonException {
        return TimeValue.parse(jsonParser.getString());
    }
}
