package com.smakab.datingapp.zustrichalnya.Models.Search;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.smakab.datingapp.zustrichalnya.JsonUtils.JsonUtil;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.TreeSet;
import java.util.UUID;

public class GeneralInfo {

    public UUID uuid;
    public StringProperty name = new SimpleStringProperty("Назва");
    public StringProperty purpose = new SimpleStringProperty("");
    public StringProperty description = new SimpleStringProperty("Опис");
    public ObjectProperty<String> relationshipType = new SimpleObjectProperty<>("На один день");
    public ListProperty<String> relationshipTypeAll = new SimpleListProperty<>(FXCollections.observableArrayList("На один день", "Дружба", "Короткочасні романтичні стосунки", "Партнерство", "Сімʼяʼ"));
    public TreeSet<String> greenFlags = new TreeSet<>();
    public TreeSet<String> redFlags = new TreeSet<>();

    public GeneralInfo(){

    }
    public GeneralInfo(UUID uuid){
        this.uuid = uuid;
    }

    public TreeSet<String> getGreenFlags() {
        return greenFlags;
    }

    public TreeSet<String> getRedFlags() {
        return redFlags;
    }

    public void renameFlag(String oldValue, String newValue, TreeSet<String> set) {
        removeFlag(oldValue, set);
        set.add(newValue);
    }

    public void removeFlag(String value, TreeSet<String> set) {
        set.remove(value);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public String getPurpose() {
        return purpose.get();
    }

    public StringProperty purposeProperty() {
        return purpose;
    }

    public String getDescription() {
        return description.get();
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public String getRelationshipType() {
        return relationshipType.get();
    }

    public ObjectProperty<String> relationshipTypeProperty() {
        return relationshipType;
    }

    public ObservableList<String> getRelationshipTypeAll() {
        return relationshipTypeAll.get();
    }

    public ListProperty<String> relationshipTypeAllProperty() {
        return relationshipTypeAll;
    }

    public String toJson() throws JsonProcessingException {
        return JsonUtil.toJson(this);
    }

    public void writeInfoToFile(String filename) throws IOException {
        Path path = Paths.get(filename).getParent();
        if (path != null) {
            Files.createDirectories(path);
        }
        // Write the JSON string to the specified file
        try (FileWriter writer = new FileWriter(filename, false)) {
            writer.write(this.toJson());
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

    }
}
