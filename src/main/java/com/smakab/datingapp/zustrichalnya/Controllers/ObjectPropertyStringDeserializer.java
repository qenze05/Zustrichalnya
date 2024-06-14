package com.smakab.datingapp.zustrichalnya.Controllers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.io.IOException;

public class ObjectPropertyStringDeserializer extends JsonDeserializer<ObjectProperty<String>> {

    @Override
    public ObjectProperty<String> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String value = p.getValueAsString();
        return new SimpleObjectProperty<>(value);
    }
}