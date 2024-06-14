package com.smakab.datingapp.zustrichalnya.Models.Profile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.smakab.datingapp.zustrichalnya.Controllers.JsonUtil;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class Personality {

    private HashMap<String, Integer> sliders;
    private String personality;

    public Personality() {
        sliders = new HashMap<>();
        personality = "";
    }

    public String getPersonality() {
        return personality;
    }

    public void setPersonality(String personality) {
        this.personality = personality;
    }

    public HashMap<String, Integer> getSliders() {
        return sliders;
    }

    public void setSliders(HashMap<String, Integer> sliders) {
        this.sliders = sliders;
    }

    public void updateSlider(String name, Integer value) {
        sliders.put(name, value);
        System.out.println(sliders.get(name));
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
