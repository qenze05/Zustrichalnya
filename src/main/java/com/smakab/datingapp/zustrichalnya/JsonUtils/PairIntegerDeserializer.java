package com.smakab.datingapp.zustrichalnya.JsonUtils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import javafx.util.Pair;

import java.io.IOException;

public class PairIntegerDeserializer extends JsonDeserializer<Pair<Integer, Integer>> {
    @Override
    public Pair<Integer, Integer> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        JsonNode node = p.getCodec().readTree(p);
        int first = node.get("first").asInt();
        int second = node.get("second").asInt();
        return new Pair<>(first, second);
    }
}
