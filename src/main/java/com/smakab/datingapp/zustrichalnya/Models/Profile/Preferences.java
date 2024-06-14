package com.smakab.datingapp.zustrichalnya.Models.Profile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.smakab.datingapp.zustrichalnya.Controllers.JsonUtil;

import java.io.FileWriter;
import java.io.IOException;
import java.util.TreeMap;

public class Preferences {

    private TreeMap<String, Integer> preferences;

    public Preferences() {
        this.preferences = new TreeMap<>();
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

    public void writeInfoToFile(String filename) {
        try (FileWriter writer = new FileWriter(filename, false)) {
            writer.write(this.toJson());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
