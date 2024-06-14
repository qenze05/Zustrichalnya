package com.smakab.datingapp.zustrichalnya.Models.Profile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.smakab.datingapp.zustrichalnya.Controllers.JsonUtil;

import java.io.FileWriter;
import java.io.IOException;
import java.util.TreeMap;
import java.util.TreeSet;

public class Hobbies {

    private TreeSet<String> subcultures;
    private TreeMap<String, Integer> hobbies;

    public Hobbies() {
        this.subcultures = new TreeSet<>();
        this.hobbies = new TreeMap<>();
    }

    public TreeMap<String, Integer> getHobbies() {
        return hobbies;
    }

    public void setHobbies(TreeMap<String, Integer> hobbies) {
        this.hobbies = hobbies;
    }

    public TreeSet<String> getSubcultures() {
        return subcultures;
    }

    public void setSubcultures(TreeSet<String> subcultures) {
        this.subcultures = subcultures;
    }

    public void renameSubculture(String oldValue, String newValue) {
        removeSubCulture(oldValue);
        this.subcultures.add(newValue);
        System.out.println(subcultures);
    }

    public void removeSubCulture(String value) {
        this.subcultures.remove(value);
    }

    public void renameHobby(String oldValue, String newValue, int sliderValue) {
        removeHobby(oldValue);
        hobbies.put(newValue, sliderValue);
    }

    public void editHobby(String value, int sliderValue) {
        hobbies.put(value, sliderValue);
        System.out.println(hobbies);
    }

    public void removeHobby(String value) {
        this.hobbies.remove(value);
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
