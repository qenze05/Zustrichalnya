package com.smakab.datingapp.zustrichalnya.Models.Profile;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import org.javatuples.Pair;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

public class GeneralInfo {
    private ArrayList<Pair<Image, Boolean>> photos = new ArrayList<>();
    private String description;
    private StringProperty surname = new SimpleStringProperty("");
    private StringProperty name = new SimpleStringProperty("");
    private StringProperty patronymic = new SimpleStringProperty("");
    private StringProperty nickname = new SimpleStringProperty("");
    private ListProperty<String> nameVisibility = new SimpleListProperty<>(FXCollections.observableArrayList("Імʼя"));
    private ListProperty<String> nameVisibilityAll = new SimpleListProperty<>(FXCollections.observableArrayList("Імʼя", "Прізвище", "По-батькові", "Нікнейм"));
    private ObjectProperty<LocalDate> birthdate = new SimpleObjectProperty<>(LocalDate.now());
    private int age = 0;
    private BooleanProperty birthdateVisibility = new SimpleBooleanProperty(true);
    private ObjectProperty<String> region = new SimpleObjectProperty<>("");
    private ListProperty<String> regionAll = new SimpleListProperty<>(FXCollections.observableArrayList("Вінницька", "Волинська", "Дніпропетровська", "Донецька", "Житомирська",
            "Закарпатська", "Запорізька", "Івано-Франківська", "Київська та Київ", "Кіровоградська",
            "Луганська", "Львівська", "Миколаївська", "Одеська", "Полтавська",
            "Рівненська", "Сумська", "Тернопільська", "Харківська", "Херсонська",
            "Хмельницька", "Черкаська", "Чернівецька", "Чернігівська",
            "Севастополь та АР Крим"));

    private StringProperty city = new SimpleStringProperty("");
    private ObjectProperty<String> sex = new SimpleObjectProperty<>("Чоловік");
    private ListProperty<String> sexAll = new SimpleListProperty<>(FXCollections.observableArrayList("Чоловік", "Жінка"));
    private StringProperty gender = new SimpleStringProperty("");
    private StringProperty orientation = new SimpleStringProperty("");
    private BooleanProperty isWorking = new SimpleBooleanProperty(false);
    private StringProperty university = new SimpleStringProperty("");
    private StringProperty profession = new SimpleStringProperty("");
    private ListProperty<String> relationshipTypeAll = new SimpleListProperty<>(FXCollections.observableArrayList("На один день", "Дружба", "Короткочасні романтичні стосунки", "Партнерство", "Сімʼяʼ"));
    private ListProperty<String> relationshipType = new SimpleListProperty<>(FXCollections.observableArrayList("На один день", "Дружба", "Короткочасні романтичні стосунки", "Партнерство", "Сімʼяʼ"));


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

    public LocalDate getBirthdate() {
        return birthdate.get();
    }

    public int getAge(){
        LocalDate today = LocalDate.now();
        Period age = Period.between(getBirthdate(), today);
        return age.getYears();
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

    public ObjectProperty<LocalDate> birthdateProperty() {
        return birthdate;
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
}
