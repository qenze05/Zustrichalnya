package com.smakab.datingapp.zustrichalnya.Controllers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

import java.io.IOException;

public class BooleanPropertyDeserializer extends JsonDeserializer<BooleanProperty> {

    @Override
    public BooleanProperty deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        return new SimpleBooleanProperty(p.getBooleanValue());
    }
}
