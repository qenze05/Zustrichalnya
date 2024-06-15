package com.smakab.datingapp.zustrichalnya.Models.Settings;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.smakab.datingapp.zustrichalnya.JsonUtils.JsonUtil;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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

    public String toJson() throws JsonProcessingException {
        return JsonUtil.toJson(this);
    }

    public SettingsModel(){}

    public void writeInfoToFile(String filename) throws IOException {
        Path path = Paths.get(filename).getParent();
        if (path != null) {
            Files.createDirectories(path);
        }
        // Write the JSON string to the specified file
        try (FileWriter writer = new FileWriter(filename, false)) {
            writer.write(this.toJson());
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

    }
}
