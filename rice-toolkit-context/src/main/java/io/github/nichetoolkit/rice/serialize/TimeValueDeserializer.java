package io.github.nichetoolkit.rice.serialize;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import io.github.nichetoolkit.rice.time.TimeValue;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class TimeValueDeserializer extends JsonDeserializer<TimeValue> {
    @Override
    public TimeValue deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        return TimeValue.parse(jsonParser.getText());
    }
}
