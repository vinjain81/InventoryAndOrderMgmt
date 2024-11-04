package com.bgarage.autopartsmgmt.common.exception.serde;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class CustomLocalTimeDeserializer extends StdDeserializer<LocalTime> {

    protected CustomLocalTimeDeserializer() {
        super(LocalTime.class);
    }

    @Override
    public LocalTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {

        JsonNode node = (JsonNode) jsonParser.getCodec().readTree(jsonParser);

        try {
            String date = node.asText();
            LocalTime localTime = Objects.isNull(date) ? null : LocalTime.parse(date,  DateTimeFormatter.ofPattern("H:mm:ss"));
            return localTime;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
