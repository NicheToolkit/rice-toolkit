package io.github.nichetoolkit.rice.serialize;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import io.github.nichetoolkit.rice.time.TimeValue;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class TimeValueSerializer extends JsonSerializer<TimeValue> {
    @Override
    public void serialize(TimeValue timeValue, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(timeValue.format());
    }
}
