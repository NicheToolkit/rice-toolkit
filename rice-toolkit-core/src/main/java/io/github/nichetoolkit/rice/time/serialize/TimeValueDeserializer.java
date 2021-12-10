package io.github.nichetoolkit.rice.time.serialize;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import io.github.nichetoolkit.rice.time.TimeValue;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * <p>TimeValueDeserializer</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Component
public class TimeValueDeserializer extends JsonDeserializer<TimeValue> {
    @Override
    public TimeValue deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        return TimeValue.parse(jsonParser.getText());
    }
}
