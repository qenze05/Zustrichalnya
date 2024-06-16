package com.smakab.datingapp.zustrichalnya.JsonUtils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;

import java.io.IOException;

public class IntegerPropertySerializer extends JsonSerializer<IntegerProperty> {

    @Override
    public void serialize(IntegerProperty integerProperty, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeNumber(integerProperty.get());
    }
}
