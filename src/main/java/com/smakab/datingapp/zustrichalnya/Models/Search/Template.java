package com.smakab.datingapp.zustrichalnya.Models.Search;

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

        this.generalInfo = new GeneralInfo();
        this.details = new Details();
        this.personality = new Personality();
        this.hobbies = new Hobbies();
        this.preferences = new Preferences();
        this.views = new Views();
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
