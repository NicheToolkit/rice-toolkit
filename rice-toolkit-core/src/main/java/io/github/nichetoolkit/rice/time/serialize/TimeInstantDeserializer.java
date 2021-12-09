package io.github.nichetoolkit.rice.time.serialize;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import io.github.nichetoolkit.rice.time.TimeInstant;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * <p>TimeInstantDeserializer</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Component
public class TimeInstantDeserializer extends JsonDeserializer<TimeInstant> {
    @Override
    public TimeInstant deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        return TimeInstant.parse(jsonParser.getText());
    }
}
