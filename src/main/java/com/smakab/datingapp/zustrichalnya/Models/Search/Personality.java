package com.smakab.datingapp.zustrichalnya.Models.Search;

import org.javatuples.Pair;

import java.util.HashMap;

public class Personality {

    private HashMap<String, Pair<Integer, Integer>> sliders;
    private HashMap<String, Integer> personalities;

    public Personality() {
        sliders = new HashMap<>();
        personalities = new HashMap<>();
    }

    public HashMap<String, Integer> getPersonalities() {
        return personalities;
    }

    public void setPersonalities(HashMap<String, Integer> personalities) {
        this.personalities = personalities;
    }

    public HashMap<String, Pair<Integer, Integer>> getSliders() {
        return sliders;
    }

    public void setSliders(HashMap<String, Pair<Integer, Integer>> sliders) {
        this.sliders = sliders;
    }

    public void updateSlider(String name, Pair<Integer, Integer> value) {
        sliders.put(name, value);
    }

    public void updatePersonality(String name, Integer value) {
        personalities.put(name, value);
    }
}
