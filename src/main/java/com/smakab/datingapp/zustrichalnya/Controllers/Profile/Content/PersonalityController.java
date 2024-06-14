package com.smakab.datingapp.zustrichalnya.Controllers.Profile.Content;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.smakab.datingapp.zustrichalnya.Controllers.JsonUtil;
import com.smakab.datingapp.zustrichalnya.Interfaces.ProfileDataDelegate;
import com.smakab.datingapp.zustrichalnya.Models.Person;
import com.smakab.datingapp.zustrichalnya.Models.Profile.GeneralInfo;
import com.smakab.datingapp.zustrichalnya.Models.Profile.Personality;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PersonalityController extends ProfileContentClass {
    public VBox slidersContainer;
    public ChoiceBox<String> personalitiesCB;
    private Personality model;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void initSliders() {
        for (int i = 0; i < slidersContainer.getChildren().size(); i++) {

            HBox child = (HBox) slidersContainer.getChildren().get(i);
            Slider slider = (Slider) child.getChildren().get(1);
            Label label = (Label) child.getChildren().get(2);

            if(model.getSliders().containsKey(label.getText())) {
                slider.valueProperty().set(model.getSliders().get(label.getText()));
            } else {
                model.updateSlider(label.getText(), (int) slider.getValue());
            }
            setListener(slider, label.getText());
        }
    }

    public void initCB() {
        ObservableList<String> list = FXCollections.observableArrayList("Архітектор (INTJ)", "Логік (INTP)", "Командир (ENTJ)", "Полеміст (ENTP)",
                "Адвокат (INFJ)", "Посередник (INFP)", "Протагоніст (ENFJ)", "Активіст (ENFP)",
                "Логіст (ISTJ)", "Захисник (ISFJ)", "Керівник (ESTJ)", "Консул (ESFJ)",
                "Віртуоз (ISTP)", "Авантюрист (ISFP)", "Підприємець (ESTP)", "Шоумен (ESFP)");
        personalitiesCB.setItems(list);

        if(!model.getPersonality().isEmpty()) {
            personalitiesCB.valueProperty().set(model.getPersonality());
        }

        personalitiesCB.valueProperty().addListener(
                (observableValue, oldValue, newValue)
                        -> model.setPersonality(newValue));

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
    public void setModel(Person person) {
        this.model = person.getPersonality();
    }

    @Override
    public void loadModelData() {
        try {
            File jsonFile = new File("personPersonality.json");
            if(jsonFile.exists()) {
                model = JsonUtil.fromJsonFile(jsonFile, Personality.class);
                System.out.println("loaded");
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        initSliders();
        initCB();
    }
}
