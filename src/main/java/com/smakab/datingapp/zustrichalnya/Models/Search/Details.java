package com.smakab.datingapp.zustrichalnya.Models.Search;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.smakab.datingapp.zustrichalnya.JsonUtils.JsonUtil;
import org.javatuples.Pair;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.TreeMap;
import java.util.UUID;

public class Details {

    public UUID uuid;
    private int realPhoto = 0;

    private Pair<Integer, Integer> age = new Pair<>(18, 120);
    private String geoLocation = "Всюди";
    private String noGeoLocation = "Допускати";
    private TreeMap<String, Integer> genders = new TreeMap<>();

    public Details(){

    }
    public Details(UUID uuid){
        this.uuid = uuid;
    }

    public int getRealPhoto() {
        return realPhoto;
    }

    public Pair<Integer, Integer> getAge() {
        return age;
    }

    public void setUpperAge(int age) {
        this.age = this.age.setAt1(age);
    }

    public void setLowerAge(int age) {
        this.age = this.age.setAt0(age);
    }

    public String getGeoLocation() {
        return geoLocation;
    }

    public String getNoGeoLocation() {
        return noGeoLocation;
    }

    public TreeMap<String, Integer> getGenders() {
        return genders;
    }

    public void setAge(Pair<Integer, Integer> age) {
        this.age = age;
    }

    public void setGenders(TreeMap<String, Integer> genders) {
        this.genders = genders;
    }

    public void setGeoLocation(String geoLocation) {
        this.geoLocation = geoLocation;
    }

    public void setNoGeoLocation(String noGeoLocation) {
        this.noGeoLocation = noGeoLocation;
    }

    public void setRealPhoto(int realPhoto) {
        this.realPhoto = realPhoto;
    }

    public void renameGender(String oldValue, String newValue, int sliderValue) {
        removeGender(oldValue);
        genders.put(newValue, sliderValue);
    }

    public void editGender(String value, int sliderValue) {
        genders.put(value, sliderValue);
    }

    public void removeGender(String value) {
        this.genders.remove(value);
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
