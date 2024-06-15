package com.smakab.datingapp.zustrichalnya.JsonUtils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ObjectPropertyDeserializer extends JsonDeserializer<ObjectProperty<LocalDate>> {

    @Override
    public ObjectProperty<LocalDate> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String dateStr = p.getValueAsString();
        LocalDate date = LocalDate.parse(dateStr, DateTimeFormatter.ISO_DATE);
        return new SimpleObjectProperty<>(date);
    }
}
