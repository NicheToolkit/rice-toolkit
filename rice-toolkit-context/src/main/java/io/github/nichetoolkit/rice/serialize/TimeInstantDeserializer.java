package io.github.nichetoolkit.rice.serialize;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import io.github.nichetoolkit.rice.time.TimeInstant;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * <code>TimeInstantDeserializer</code>
 * <p>The time instant deserializer class.</p>
 * @see  com.fasterxml.jackson.databind.JsonDeserializer
 * @see  org.springframework.stereotype.Component
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
@Component
public class TimeInstantDeserializer extends JsonDeserializer<TimeInstant> {
    @Override
    public TimeInstant deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        return TimeInstant.parse(jsonParser.getText());
    }
}
