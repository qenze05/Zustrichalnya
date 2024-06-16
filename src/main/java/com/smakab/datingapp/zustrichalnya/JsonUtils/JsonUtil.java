package com.smakab.datingapp.zustrichalnya.JsonUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import javafx.beans.property.*;
import javafx.util.Pair;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class JsonUtil {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // Register custom serializers and deserializers for LocalDate
        javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(dateFormatter));
        javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(dateFormatter));

        // Register custom deserializer for ListProperty<String>
        objectMapper.registerModule(javaTimeModule);
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.registerModule(new SimpleListPropertyModule());
        objectMapper.registerModule(new SimpleObjectPropertyModule());
        objectMapper.registerModule(new SimpleObjectPropertyStringModule());

        SimpleModule customModule = new SimpleModule();
        customModule.addSerializer(StringProperty.class, new StringPropertySerializer());
        customModule.addDeserializer(StringProperty.class, new StringPropertyDeserializer());
        objectMapper.registerModule(customModule);

        SimpleModule customModule2 = new SimpleModule();
        customModule2.addSerializer(BooleanProperty.class, new BooleanPropertySerializer());
        customModule2.addDeserializer(BooleanProperty.class, new BooleanPropertyDeserializer());
        objectMapper.registerModule(customModule2);

        objectMapper.registerModule(new SimplePairModule());

        SimpleModule customModule3 = new SimpleModule();
        customModule3.addSerializer(IntegerProperty.class, new IntegerPropertySerializer());
        customModule3.addDeserializer(IntegerProperty.class, new IntegerPropertyDeserializer());
        objectMapper.registerModule(customModule3);

    }

    public static String toJson(Object obj) throws JsonProcessingException {
        return objectMapper.writeValueAsString(obj);
    }

    public static <T> T fromJson(String json, Class<T> clazz) throws JsonProcessingException {
        return objectMapper.readValue(json, clazz);
    }

    public static <T> T fromJsonFile(File file, Class<T> clazz) throws IOException {
        return objectMapper.readValue(file, clazz);
    }

    public static class SimpleListPropertyModule extends SimpleModule {
        public SimpleListPropertyModule() {
            addDeserializer(ListProperty.class, new ListPropertyStringDeserializer());
        }
    }

    public static class SimpleObjectPropertyModule extends SimpleModule {
        public SimpleObjectPropertyModule() {
            addDeserializer(ObjectProperty.class, new ObjectPropertyDeserializer());
        }
    }

    public static class SimpleObjectPropertyStringModule extends SimpleModule {
        public SimpleObjectPropertyStringModule() {
            addDeserializer(ObjectProperty.class, new ObjectPropertyStringDeserializer());
        }
    }
    public static class SimplePairModule extends SimpleModule {
        public SimplePairModule() {
            addDeserializer(Pair.class, new PairIntegerDeserializer());
        }
    }
}
