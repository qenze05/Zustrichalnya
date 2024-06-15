package com.smakab.datingapp.zustrichalnya.JsonUtils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import javafx.util.Pair;

import java.io.IOException;

public class PairIntegerSerializer extends JsonSerializer<Pair<Integer, Integer>> {
    @Override
    public void serialize(Pair<Integer, Integer> pair, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        gen.writeNumberField("first", pair.getKey());
        gen.writeNumberField("second", pair.getValue());
        gen.writeEndObject();
    }
}