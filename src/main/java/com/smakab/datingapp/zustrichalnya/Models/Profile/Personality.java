package com.smakab.datingapp.zustrichalnya.Models.Profile;

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
}
