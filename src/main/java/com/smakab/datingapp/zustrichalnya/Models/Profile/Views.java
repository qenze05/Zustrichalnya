package com.smakab.datingapp.zustrichalnya.Models.Profile;

import java.util.TreeSet;

public class Views {
    private TreeSet<String> movements;
    private String religion;
    private String language;

    public Views() {
        this.movements = new TreeSet<>();
        this.religion = "";
        this.language = "";
    }

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
}
