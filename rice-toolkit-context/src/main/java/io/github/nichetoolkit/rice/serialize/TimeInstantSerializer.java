package io.github.nichetoolkit.rice.serialize;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rice.time.TimeInstant;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * <code>TimeInstantSerializer</code>
 * <p>The time instant serializer class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see com.fasterxml.jackson.databind.JsonSerializer
 * @see org.springframework.stereotype.Component
 * @since Jdk1.8
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
