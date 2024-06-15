package com.smakab.datingapp.zustrichalnya.Models.Search;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.smakab.datingapp.zustrichalnya.JsonUtils.JsonUtil;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.UUID;

public class Preferences {

    public UUID uuid;
    private TreeMap<String, Integer> preferences = new TreeMap<>();

    public Preferences() {
    }

    public Preferences(UUID uuid) {
        this.uuid = uuid;
    }

    public TreeMap<String, Integer> getPreferences() {
        return preferences;
    }

    public void setPreferences(TreeMap<String, Integer> preferences) {
        this.preferences = preferences;
    }

    public void renamePreference(String oldValue, String newValue, int sliderValue) {
        removePreference(oldValue);
        preferences.put(newValue, sliderValue);
    }

    public void editPreference(String value, int sliderValue) {
        preferences.put(value, sliderValue);
        System.out.println(preferences);
    }

    public void removePreference(String value) {
        this.preferences.remove(value);
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
