package com.smakab.datingapp.zustrichalnya.Models;
//
import com.smakab.datingapp.zustrichalnya.Models.Profile.*;
import javafx.scene.image.Image;
import org.javatuples.Pair;
import org.javatuples.Triplet;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

//
////TODO: знести оцей треш і написати нормально
public class Person {
    public UUID uuid;
    GeneralInfo generalInfo;
    Hobbies hobbies;
    Personality personality;
    Preferences preferences;
    Views views;

    public Person(UUID uuid) {
        this.uuid = uuid;
        //TODO: підтягнути інфу про профіль з бд і створювати профіль з нею замість пустого
        generalInfo = new GeneralInfo(this.uuid);
        hobbies = new Hobbies(this.uuid);
        personality = new Personality(this.uuid);
        preferences = new Preferences(this.uuid);
        views = new Views(this.uuid);
    }

    public Person(){};

    public Person(UUID uuid, GeneralInfo generalInfo, Hobbies hobbies, Personality personality, Preferences preferences, Views views){
        this.uuid = uuid;
        this.generalInfo = generalInfo;
        this.hobbies = hobbies;
        this.personality = personality;
        this.preferences = preferences;
        this.views = views;
    }

    public GeneralInfo getGeneralInfo() {
        return generalInfo;
    }

    public Hobbies getHobbies() {
        return hobbies;
    }

    public Personality getPersonality() {
        return personality;
    }

    public Preferences getPreferences() {
        return preferences;
    }

    public Views getViews() {
        return views;
    }

    public void setPersonality(Personality personality) {
        this.personality = personality;
    }

    public void setHobbies(Hobbies hobbies) {
        this.hobbies = hobbies;
    }

    public void setGeneralInfo(GeneralInfo generalInfo) {
        this.generalInfo = generalInfo;
    }

    public void setPreferences(Preferences preferences) {
        this.preferences = preferences;
    }

    public void setViews(Views views) {
        this.views = views;
    }


}