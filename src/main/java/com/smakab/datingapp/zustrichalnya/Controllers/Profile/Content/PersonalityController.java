package com.smakab.datingapp.zustrichalnya.Controllers.Profile.Content;

import com.smakab.datingapp.zustrichalnya.Interfaces.ProfileDataDelegate;
import com.smakab.datingapp.zustrichalnya.Models.Person;
import com.smakab.datingapp.zustrichalnya.Models.Profile.Personality;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class PersonalityController extends ProfileContentClass {
    public VBox slidersContainer;
    public ChoiceBox personalitiesCB;
    private Personality model;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        model = new Personality();
        initSliders();
        initCB();
    }

    public void initSliders() {
        for (int i = 0; i < slidersContainer.getChildren().size(); i++) {

            HBox child = (HBox) slidersContainer.getChildren().get(i);
            Slider slider = (Slider) child.getChildren().get(1);
            Label label = (Label) child.getChildren().get(2);

            model.updateSlider(label.getText(), (int) slider.getValue());
            setListener(slider, label.getText());
        }
    }

    public void initCB() {
        ObservableList<String> list = FXCollections.observableArrayList("Архітектор (INTJ)", "Логік (INTP)", "Командир (ENTJ)", "Полеміст (ENTP)",
                "Адвокат (INFJ)", "Посередник (INFP)", "Протагоніст (ENFJ)", "Активіст (ENFP)",
                "Логіст (ISTJ)", "Захисник (ISFJ)", "Керівник (ESTJ)", "Консул (ESFJ)",
                "Віртуоз (ISTP)", "Авантюрист (ISFP)", "Підприємець (ESTP)", "Шоумен (ESFP)");
        personalitiesCB.setItems(list);

        personalitiesCB.valueProperty().addListener(
                (observableValue, oldValue, newValue)
                        -> model.setPersonality(newValue.toString()));

    }
    public void setListener(Slider slider, String label) {
        slider.valueProperty().addListener(
                (observableValue, oldValue, newValue)
                        -> model.updateSlider(label, newValue.intValue()));
    }

    @Override
    public void setDelegate(ProfileDataDelegate delegate) {
        this.delegate = delegate;
    }

    @Override
    public void setPerson(Person person) {
        try {
            this.person = (Person) person.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }
}
