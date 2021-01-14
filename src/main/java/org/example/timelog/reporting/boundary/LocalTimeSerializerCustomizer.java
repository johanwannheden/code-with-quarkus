package org.example.timelog.reporting.boundary;

import io.quarkus.jsonb.JsonbConfigCustomizer;

import javax.inject.Singleton;
import javax.json.bind.JsonbConfig;
import javax.json.bind.serializer.JsonbSerializer;
import javax.json.bind.serializer.SerializationContext;
import javax.json.stream.JsonGenerator;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Singleton
public class LocalTimeSerializerCustomizer implements JsonbConfigCustomizer {

    @Override
    public void customize(JsonbConfig jsonbConfig) {
        jsonbConfig.withSerializers(new LocalTimeSerializer());
    }

}

class LocalTimeSerializer implements JsonbSerializer<LocalTime> {

    public static final DateTimeFormatter HOUR_MINUTE_FORMAT = DateTimeFormatter.ofPattern("HH:mm");

    @Override
    public void serialize(LocalTime obj, JsonGenerator generator, SerializationContext ctx) {
        generator.write(obj.format(HOUR_MINUTE_FORMAT));
    }
}
