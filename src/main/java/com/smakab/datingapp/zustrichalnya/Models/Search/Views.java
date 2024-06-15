package com.smakab.datingapp.zustrichalnya.Models.Search;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.smakab.datingapp.zustrichalnya.JsonUtils.JsonUtil;
import javafx.event.ActionEvent;
import javafx.scene.layout.VBox;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.UUID;

public class Views {

    public UUID uuid;
    HashMap<String, Integer> religions = new HashMap<>();
    HashMap<String, Integer> languages = new HashMap<>();
    HashMap<String, Integer> movements = new HashMap<>();

    public Views(){

    }

    public Views(UUID uuid){
        this.uuid = uuid;
    }

    public HashMap<String, Integer> getLanguages() {
        return languages;
    }

    public void renameLanguage(String oldValue, String newValue, int sliderValue) {
        removeLanguage(oldValue);
        languages.put(newValue, sliderValue);
    }

    public void editLanguage(String value, int sliderValue) {
        languages.put(value, sliderValue);
    }

    public void removeLanguage(String value) {
        this.languages.remove(value);
    }

    public HashMap<String, Integer> getMovements() {
        return movements;
    }

    public void renameMovement(String oldValue, String newValue, int sliderValue) {
        removeMovement(oldValue);
        movements.put(newValue, sliderValue);
    }

    public void editMovement(String value, int sliderValue) {
        movements.put(value, sliderValue);
    }

    public void removeMovement(String value) {
        this.movements.remove(value);
    }

    public HashMap<String, Integer> getReligions() {
        return religions;
    }

    public void renameReligion(String oldValue, String newValue, int sliderValue) {
        removeReligion(oldValue);
        religions.put(newValue, sliderValue);
    }

    public void editReligion(String value, int sliderValue) {
        religions.put(value, sliderValue);
    }

    public void removeReligion(String value) {
        this.religions.remove(value);
    }

    public void setMovements(HashMap<String, Integer> movements) {
        this.movements = movements;
    }

    public void setLanguages(HashMap<String, Integer> languages) {
        this.languages = languages;
    }

    public void setReligions(HashMap<String, Integer> religions) {
        this.religions = religions;
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
