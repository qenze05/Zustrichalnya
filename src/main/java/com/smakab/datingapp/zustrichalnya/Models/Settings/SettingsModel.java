package com.smakab.datingapp.zustrichalnya.Models.Settings;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class SettingsModel {
    private StringProperty telegram = new SimpleStringProperty("");
    private StringProperty instagram = new SimpleStringProperty("");
    private StringProperty zustrichat = new SimpleStringProperty("");

    public StringProperty instagramProperty() {
        return instagram;
    }

    public StringProperty telegramProperty() {
        return telegram;
    }

    public StringProperty zustrichatProperty() {
        return zustrichat;
    }

    public String getInstagram() {
        return instagram.get();
    }

    public String getTelegram() {
        return telegram.get();
    }

    public String getZustrichat() {
        return zustrichat.get();
    }
}
