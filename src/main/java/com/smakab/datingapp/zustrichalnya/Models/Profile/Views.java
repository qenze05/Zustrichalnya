package com.smakab.datingapp.zustrichalnya.Models.Profile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.smakab.datingapp.zustrichalnya.JsonUtils.JsonUtil;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.TreeSet;
import java.util.UUID;

public class Views {
    public UUID uuid;
    private TreeSet<String> movements;
    private String religion = "";
    private String language = "";


    public String getLanguage() {
        return language;
    }

    public String getReligion() {
        return religion;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public TreeSet<String> getMovements() {
        return movements;
    }

    public void setMovements(TreeSet<String> movements) {
        this.movements = movements;
    }

    public void renameMovement(String oldValue, String newValue) {
        removeMovement(oldValue);
        this.movements.add(newValue);
        System.out.println(movements);
    }

    public void removeMovement(String value) {
        this.movements.remove(value);
    }

    public Views(UUID uuid){
        this.uuid = uuid;
        this.movements = new TreeSet<>();
        this.language = "";
        this.religion = "";
    }

    public Views(){

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
