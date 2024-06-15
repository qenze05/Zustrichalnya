package com.smakab.datingapp.zustrichalnya.Models.Search;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.smakab.datingapp.zustrichalnya.JsonUtils.JsonUtil;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.UUID;

public class Hobbies {

    public UUID uuid;
    HashMap<String, Integer> subcultures = new HashMap<>();
    HashMap<String, Integer> hobbies = new HashMap<>();

    public Hobbies(){

    }

    public Hobbies(UUID uuid){
        this.uuid = uuid;
    }

    public HashMap<String, Integer> getHobbies() {
        return hobbies;
    }

    public void renameHobby(String oldValue, String newValue, int sliderValue) {
        removeHobby(oldValue);
        hobbies.put(newValue, sliderValue);
    }

    public void editHobby(String value, int sliderValue) {
        hobbies.put(value, sliderValue);
    }

    public void removeHobby(String value) {
        this.hobbies.remove(value);
    }
    
    public HashMap<String, Integer> getSubcultures() {
        return subcultures;
    }

    public void renameSubculture(String oldValue, String newValue, int sliderValue) {
        removeSubculture(oldValue);
        subcultures.put(newValue, sliderValue);
    }

    public void editSubculture(String value, int sliderValue) {
        subcultures.put(value, sliderValue);
    }

    public void removeSubculture(String value) {
        this.subcultures.remove(value);
    }
    
    public void setHobbies(HashMap<String, Integer> hobbies) {
        this.hobbies = hobbies;
    }

    public void setSubcultures(HashMap<String, Integer> subcultures) {
        this.subcultures = subcultures;
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
