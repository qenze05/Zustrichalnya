package com.smakab.datingapp.zustrichalnya.Models.Search;

import java.util.Date;
import java.util.UUID;

public class Template {
    UUID uuid;
    GeneralInfo generalInfo;
    Details details;
    Personality personality;
    Hobbies hobbies;
    Preferences preferences;
    Views views;


    public Template(UUID uuid) {
        this.uuid = uuid;

        this.generalInfo = new GeneralInfo(this.uuid);
        this.details = new Details(this.uuid);
        this.personality = new Personality(this.uuid);
        this.hobbies = new Hobbies(this.uuid);
        this.preferences = new Preferences(this.uuid);
        this.views = new Views(this.uuid);
    }

    public Template(UUID uuid, GeneralInfo generalInfo, Details details, Personality personality, Hobbies hobbies, Preferences preferences, Views views){
        this.uuid = uuid;

        this.generalInfo = generalInfo;
        this.details = details;
        this.personality = personality;
        this.hobbies = hobbies;
        this.preferences = preferences;
        this.views = views;
    }

    public Template(){

    }

    public Preferences getPreferences() {
        return preferences;
    }

    public Hobbies getHobbies() {
        return hobbies;
    }

    public Views getViews() {
        return views;
    }

    public Personality getPersonality() {
        return personality;
    }

    public GeneralInfo getGeneralInfo() {
        return generalInfo;
    }

    public Details getDetails() {
        return details;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }
}
