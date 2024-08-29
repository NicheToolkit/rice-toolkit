package io.github.nichetoolkit.rice.time.serialize;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import io.github.nichetoolkit.rice.time.TimeInstant;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class TimeInstantDeserializer extends JsonDeserializer<TimeInstant> {
    @Override
    public TimeInstant deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        return TimeInstant.parse(jsonParser.getText());
    }
}
