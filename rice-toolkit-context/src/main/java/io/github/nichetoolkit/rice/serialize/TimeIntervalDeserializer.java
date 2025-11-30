package io.github.nichetoolkit.rice.serialize;

import io.github.nichetoolkit.rice.time.TimeInterval;
import tools.jackson.core.JacksonException;
import tools.jackson.core.JsonParser;
import tools.jackson.databind.DeserializationContext;
import tools.jackson.databind.ValueDeserializer;

/**
 * <code>TimeIntervalDeserializer</code>
 * <p>The time interval deserializer class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see tools.jackson.databind.ValueDeserializer
 * @since Jdk17
 */
public class TimeIntervalDeserializer extends ValueDeserializer<TimeInterval> {
    @Override
    public TimeInterval deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws JacksonException {
        return TimeInterval.parse(jsonParser.getString());
    }
}
