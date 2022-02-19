package io.github.nichetoolkit.rice.time.serialize;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rice.time.TimeInstant;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * <p>TimeInstantSerializer</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Component
public class TimeInstantSerializer extends JsonSerializer<TimeInstant> {
    @Override
    public void serialize(TimeInstant timeInstant, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (GeneralUtils.isNotEmpty(timeInstant)) {
            jsonGenerator.writeString(timeInstant.format());
        }
    }
}
