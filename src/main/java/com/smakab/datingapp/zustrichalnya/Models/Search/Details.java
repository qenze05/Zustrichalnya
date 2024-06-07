package com.smakab.datingapp.zustrichalnya.Models.Search;

import org.javatuples.Pair;

import java.util.TreeMap;

public class Details {

    private int realPhoto = 5;

    private Pair<Integer, Integer> age = new Pair<>(18, 120);
    private String geoLocation = "Всюди";
    private String noGeoLocation = "Допускати";
    private TreeMap<String, Integer> genders = new TreeMap<>();

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

}
