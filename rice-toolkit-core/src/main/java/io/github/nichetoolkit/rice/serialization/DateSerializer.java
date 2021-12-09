package io.github.nichetoolkit.rice.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import io.github.nichetoolkit.rest.util.common.DateUtils;

import java.io.IOException;
import java.util.Date;

/**
 * <p>DataSerializer</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class DateSerializer<D> extends JsonSerializer<D> {

    @Override
    public void serialize(D date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (date instanceof Long) {
            Long datetime = (Long) date;
            String formatTime = DateUtils.formatTime(datetime);
            jsonGenerator.writeString(formatTime);
        } else if (date instanceof Date) {
            Date datetime = (Date) date;
            String formatTime = DateUtils.formatTime(datetime);
            jsonGenerator.writeString(formatTime);
        } else if (date instanceof String) {
            String datetime = (String) date;
            jsonGenerator.writeString(datetime);
        } else {
            jsonGenerator.writeObject(date);
        }
    }
}
