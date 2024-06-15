package com.smakab.datingapp.zustrichalnya.JsonUtils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import javafx.beans.property.ObjectProperty;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ObjectPropertySerializer extends JsonSerializer<ObjectProperty<LocalDate>> {

    @Override
    public void serialize(ObjectProperty<LocalDate> value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        LocalDate date = value.get();
        gen.writeString(date.toString());
    }
}
