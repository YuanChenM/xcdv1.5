/**
 * JsonDateTimeSerializer.java
 * 
 * @screen core
 * @author ma_b
 */
package com.hoperun.plug.spring.annotation;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Locale;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * Json Date Serializer.
 */
@Component
public class JsonDateTimeSerializer extends JsonSerializer<Timestamp> {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
        Locale.SIMPLIFIED_CHINESE);

    @Override
    public void serialize(Timestamp date, JsonGenerator gen, SerializerProvider provider)
        throws IOException, JsonProcessingException {
        if (date != null) {
            gen.writeString(DATE_FORMAT.format(date));
        } else {
            gen.writeString("");
        }
    }

}
