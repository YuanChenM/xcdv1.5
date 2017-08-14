package com.hoperun.plug.spring.annotation;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Json Date Serializer.
 */
@Component
public class JsonDateSerializer extends JsonSerializer<Date> {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd", Locale.SIMPLIFIED_CHINESE);

    @Override
    public void serialize(Date date, JsonGenerator gen, SerializerProvider provider)
        throws IOException, JsonProcessingException {
        if (date != null) {
            gen.writeString(DATE_FORMAT.format(date));
        } else {
            gen.writeString("");
        }
    }

}
