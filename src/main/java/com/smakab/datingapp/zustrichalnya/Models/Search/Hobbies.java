package com.smakab.datingapp.zustrichalnya.Models.Search;

import java.util.HashMap;

public class Hobbies {
    HashMap<String, Integer> subcultures = new HashMap<>();
    HashMap<String, Integer> hobbies = new HashMap<>();

    public HashMap<String, Integer> getHobbies() {
        return hobbies;
    }

    public void renameHobby(String oldValue, String newValue, int sliderValue) {
        removeHobby(oldValue);
        hobbies.put(newValue, sliderValue);
    }

    public void editHobby(String value, int sliderValue) {
        hobbies.put(value, sliderValue);
    }

    public void removeHobby(String value) {
        this.hobbies.remove(value);
    }
    
    public HashMap<String, Integer> getSubcultures() {
        return subcultures;
    }

    public void renameSubculture(String oldValue, String newValue, int sliderValue) {
        removeSubculture(oldValue);
        subcultures.put(newValue, sliderValue);
    }

    public void editSubculture(String value, int sliderValue) {
        subcultures.put(value, sliderValue);
    }

    public void removeSubculture(String value) {
        this.subcultures.remove(value);
    }
    
    public void setHobbies(HashMap<String, Integer> hobbies) {
        this.hobbies = hobbies;
    }

    public void setSubcultures(HashMap<String, Integer> subcultures) {
        this.subcultures = subcultures;
    }
}
