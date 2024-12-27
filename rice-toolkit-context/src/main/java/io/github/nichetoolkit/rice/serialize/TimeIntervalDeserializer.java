package io.github.nichetoolkit.rice.serialize;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import io.github.nichetoolkit.rice.time.TimeInterval;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * <code>TimeIntervalDeserializer</code>
 * <p>The time interval deserializer class.</p>
 * @see  com.fasterxml.jackson.databind.JsonDeserializer
 * @see  org.springframework.stereotype.Component
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
@Component
public class TimeIntervalDeserializer extends JsonDeserializer<TimeInterval> {
    @Override
    public TimeInterval deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        return TimeInterval.parse(jsonParser.getText());
    }
}
