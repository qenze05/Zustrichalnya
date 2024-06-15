package com.smakab.datingapp.zustrichalnya.Models.Profile;

import com.smakab.datingapp.zustrichalnya.JsonUtils.JsonUtil;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import org.javatuples.Pair;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.UUID;

import com.fasterxml.jackson.core.JsonProcessingException;

public class GeneralInfo {

    public UUID uuid;
    public ArrayList<Pair<Image, Boolean>> photos = new ArrayList<>();
    public String description;
    public StringProperty surname = new SimpleStringProperty("");
    public StringProperty name = new SimpleStringProperty("");
    public StringProperty patronymic = new SimpleStringProperty("");
    public StringProperty nickname = new SimpleStringProperty("");
    public ListProperty<String> nameVisibility = new SimpleListProperty<>(FXCollections.observableArrayList("Імʼя"));
    public ListProperty<String> nameVisibilityAll = new SimpleListProperty<>(FXCollections.observableArrayList("Імʼя", "Прізвище", "По-батькові", "Нікнейм"));

    public int age = 25;
    public BooleanProperty birthdateVisibility = new SimpleBooleanProperty(true);
    public ObjectProperty<String> region = new SimpleObjectProperty<>("");
    public ListProperty<String> regionAll = new SimpleListProperty<>(FXCollections.observableArrayList("Вінницька", "Волинська", "Дніпропетровська", "Донецька", "Житомирська",
            "Закарпатська", "Запорізька", "Івано-Франківська", "Київська та Київ", "Кіровоградська",
            "Луганська", "Львівська", "Миколаївська", "Одеська", "Полтавська",
            "Рівненська", "Сумська", "Тернопільська", "Харківська", "Херсонська",
            "Хмельницька", "Черкаська", "Чернівецька", "Чернігівська",
            "Севастополь та АР Крим"));

    public StringProperty city = new SimpleStringProperty("");
    public ObjectProperty<String> sex = new SimpleObjectProperty<>("Чоловік");
    public ListProperty<String> sexAll = new SimpleListProperty<>(FXCollections.observableArrayList("Чоловік", "Жінка"));
    public StringProperty gender = new SimpleStringProperty("");
    public StringProperty orientation = new SimpleStringProperty("");
    public BooleanProperty isWorking = new SimpleBooleanProperty(false);
    public StringProperty university = new SimpleStringProperty("");
    public StringProperty profession = new SimpleStringProperty("");
    public ListProperty<String> relationshipTypeAll = new SimpleListProperty<>(FXCollections.observableArrayList("На один день", "Дружба", "Короткочасні романтичні стосунки", "Партнерство", "Сімʼяʼ"));
    public ListProperty<String> relationshipType = new SimpleListProperty<>(FXCollections.observableArrayList("На один день", "Дружба", "Короткочасні романтичні стосунки", "Партнерство", "Сімʼяʼ"));


    public ListProperty<String> regionAllProperty() {
        return regionAll;
    }

    public ObservableList<String> getRegionAll() {
        return regionAll.get();
    }

    public ObservableList<String> getRelationshipTypeAll() {
        return relationshipTypeAll.get();
    }

    public ListProperty<String> relationshipTypeAllProperty() {
        return relationshipTypeAll;
    }

    public ObservableList<String> getNameVisibilityAll() {
        return nameVisibilityAll.get();
    }

    public ObservableList<String> getRelationshipType() {
        return relationshipType.get();
    }

    public ListProperty<String> relationshipTypeProperty() {
        return relationshipType;
    }

    public ListProperty<String> nameVisibilityAllProperty() {
        return nameVisibilityAll;
    }

    public ArrayList<Pair<Image, Boolean>> getPhotos() {
        return photos;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPhotos(ArrayList<Pair<Image, Boolean>> photos) {
        this.photos = photos;
    }

    public ObservableList<String> getSexAll() {
        return sexAll.get();
    }

    public ListProperty<String> sexAllProperty() {
        return sexAll;
    }


    public int getAge(){
        return age;
    }

    public boolean isBirthdateVisibility() {
        return birthdateVisibility.get();
    }

    public boolean isIsWorking() {
        return isWorking.get();
    }

    public ObservableList<String> getNameVisibility() {
        return nameVisibility.get();
    }

    public String getCity() {
        return city.get();
    }


    public String getGender() {
        return gender.get();
    }

    public String getName() {
        return name.get();
    }

    public String getNickname() {
        return nickname.get();
    }

    public String getOrientation() {
        return orientation.get();
    }

    public String getPatronymic() {
        return patronymic.get();
    }

    public String getProfession() {
        return profession.get();
    }

    public String getRegion() {
        return region.get();
    }

    public String getSex() {
        return sex.get();
    }

    public String getSurname() {
        return surname.get();
    }

    public String getUniversity() {
        return university.get();
    }


    public StringProperty professionProperty() {
        return profession;
    }

    public BooleanProperty birthdateVisibilityProperty() {
        return birthdateVisibility;
    }

    public BooleanProperty isWorkingProperty() {
        return isWorking;
    }

    public ListProperty<String> nameVisibilityProperty() {
        return nameVisibility;
    }



    public StringProperty cityProperty() {
        return city;
    }

    public StringProperty genderProperty() {
        return gender;
    }

    public StringProperty nameProperty() {
        return name;
    }

    public StringProperty nicknameProperty() {
        return nickname;
    }

    public StringProperty orientationProperty() {
        return orientation;
    }

    public StringProperty patronymicProperty() {
        return patronymic;
    }

    public ObjectProperty<String> regionProperty() {
        return region;
    }

    public ObjectProperty<String> sexProperty() {
        return sex;
    }

    public StringProperty surnameProperty() {
        return surname;
    }

    public StringProperty universityProperty() {
        return university;
    }


    public GeneralInfo(UUID uuid){
        this.uuid = uuid;
    }

    public GeneralInfo(){
    }

    public String toJson() throws JsonProcessingException {
        return JsonUtil.toJson(this);
    }

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
