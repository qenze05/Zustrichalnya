package com.smakab.datingapp.zustrichalnya.Models.Profile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.smakab.datingapp.zustrichalnya.JsonUtils.JsonUtil;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.UUID;

public class Personality {

    public UUID uuid;
    private HashMap<String, Integer> sliders;
    private String personality;



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

    public Personality(UUID uuid){
        this.uuid = uuid;
        this.sliders = new HashMap<>();
        this.personality = "";
    }

    public Personality(){

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
