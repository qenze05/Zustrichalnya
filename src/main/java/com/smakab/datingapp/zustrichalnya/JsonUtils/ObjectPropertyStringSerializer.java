package com.smakab.datingapp.zustrichalnya.JsonUtils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import javafx.beans.property.ObjectProperty;

import java.io.IOException;

public class ObjectPropertyStringSerializer extends JsonSerializer<ObjectProperty<String>> {

    @Override
    public void serialize(ObjectProperty<String> value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        if (value != null && value.get() != null) {
            gen.writeString(value.get());
        } else {
            gen.writeNull();
        }
    }
}
