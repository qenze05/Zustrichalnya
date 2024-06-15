package com.smakab.datingapp.zustrichalnya.JsonUtils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;

public class ListPropertyStringDeserializer extends StdDeserializer<ListProperty<String>> {

    public ListPropertyStringDeserializer() {
        this(null);
    }

    protected ListPropertyStringDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public ListProperty<String> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        ObservableList<String> list = FXCollections.observableArrayList();

        if (node.isArray()) {
            for (JsonNode element : node) {
                list.add(element.asText());
            }
        }

        return new SimpleListProperty<>(list);
    }
}
