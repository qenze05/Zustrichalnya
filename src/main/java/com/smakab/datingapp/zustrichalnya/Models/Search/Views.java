package com.smakab.datingapp.zustrichalnya.Models.Search;

import javafx.event.ActionEvent;
import javafx.scene.layout.VBox;

import java.util.HashMap;

public class Views {


    HashMap<String, Integer> religions = new HashMap<>();
    HashMap<String, Integer> languages = new HashMap<>();
    HashMap<String, Integer> movements = new HashMap<>();

    public HashMap<String, Integer> getLanguages() {
        return languages;
    }

    public void renameLanguage(String oldValue, String newValue, int sliderValue) {
        removeLanguage(oldValue);
        languages.put(newValue, sliderValue);
    }

    public void editLanguage(String value, int sliderValue) {
        languages.put(value, sliderValue);
    }

    public void removeLanguage(String value) {
        this.languages.remove(value);
    }

    public HashMap<String, Integer> getMovements() {
        return movements;
    }

    public void renameMovement(String oldValue, String newValue, int sliderValue) {
        removeMovement(oldValue);
        movements.put(newValue, sliderValue);
    }

    public void editMovement(String value, int sliderValue) {
        movements.put(value, sliderValue);
    }

    public void removeMovement(String value) {
        this.movements.remove(value);
    }

    public HashMap<String, Integer> getReligions() {
        return religions;
    }

    public void renameReligion(String oldValue, String newValue, int sliderValue) {
        removeReligion(oldValue);
        religions.put(newValue, sliderValue);
    }

    public void editReligion(String value, int sliderValue) {
        religions.put(value, sliderValue);
    }

    public void removeReligion(String value) {
        this.religions.remove(value);
    }

    public void setMovements(HashMap<String, Integer> movements) {
        this.movements = movements;
    }

    public void setLanguages(HashMap<String, Integer> languages) {
        this.languages = languages;
    }

    public void setReligions(HashMap<String, Integer> religions) {
        this.religions = religions;
    }


}
