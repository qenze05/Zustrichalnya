package com.smakab.datingapp.zustrichalnya.Models.Search;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.TreeSet;

public class GeneralInfo {
    private StringProperty name = new SimpleStringProperty("Назва");
    private StringProperty purpose = new SimpleStringProperty("");
    private StringProperty description = new SimpleStringProperty("Опис");
    private ObjectProperty<String> relationshipType = new SimpleObjectProperty<>("На один день");
    private ListProperty<String> relationshipTypeAll = new SimpleListProperty<>(FXCollections.observableArrayList("На один день", "Дружба", "Короткочасні романтичні стосунки", "Партнерство", "Сімʼяʼ"));
    private TreeSet<String> greenFlags = new TreeSet<>();
    private TreeSet<String> redFlags = new TreeSet<>();

    public TreeSet<String> getGreenFlags() {
        return greenFlags;
    }

    public TreeSet<String> getRedFlags() {
        return redFlags;
    }

    public void renameFlag(String oldValue, String newValue, TreeSet<String> set) {
        removeFlag(oldValue, set);
        set.add(newValue);
    }

    public void removeFlag(String value, TreeSet<String> set) {
        set.remove(value);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public String getPurpose() {
        return purpose.get();
    }

    public StringProperty purposeProperty() {
        return purpose;
    }

    public String getDescription() {
        return description.get();
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public String getRelationshipType() {
        return relationshipType.get();
    }

    public ObjectProperty<String> relationshipTypeProperty() {
        return relationshipType;
    }

    public ObservableList<String> getRelationshipTypeAll() {
        return relationshipTypeAll.get();
    }

    public ListProperty<String> relationshipTypeAllProperty() {
        return relationshipTypeAll;
    }
}
