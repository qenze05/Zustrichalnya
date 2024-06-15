package com.smakab.datingapp.zustrichalnya.Models.Search;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.smakab.datingapp.zustrichalnya.JsonUtils.JsonUtil;
import org.javatuples.Pair;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class Personality {

    public UUID uuid;
    private HashMap<String, ArrayList<Integer>> sliders;
    private HashMap<String, Integer> personalities;

    public Personality() {
        sliders = new HashMap<>();
        personalities = new HashMap<>();
    }

    public Personality(UUID uuid) {
        this.uuid = uuid;
        sliders = new HashMap<>();
        personalities = new HashMap<>();
    }
    public HashMap<String, Integer> getPersonalities() {
        return personalities;
    }

    public void setPersonalities(HashMap<String, Integer> personalities) {
        this.personalities = personalities;
    }

    public HashMap<String, ArrayList<Integer>> getSliders() {
        return sliders;
    }

    public void setSliders(HashMap<String, ArrayList<Integer>> sliders) {
        this.sliders = sliders;
    }

    public void updateSlider(String name, int index, int value) {
        if(sliders.get(name) == null){
            sliders.put(name, new ArrayList<>());
            sliders.get(name).add(0);
            sliders.get(name).add(0);
        }
        sliders.get(name).set(index, value);
    }

    public void updatePersonality(String name, Integer value) {
        personalities.put(name, value);
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
