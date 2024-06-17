package com.smakab.datingapp.zustrichalnya.Controllers.Search.Content;

import com.smakab.datingapp.zustrichalnya.Interfaces.TemplateDataDelegate;
import com.smakab.datingapp.zustrichalnya.Models.Search.Personality;
import com.smakab.datingapp.zustrichalnya.Models.Search.Template;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.controlsfx.control.RangeSlider;
import org.javatuples.Pair;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.UUID;

public class PersonalityController extends TemplateContentClass {
    public VBox personalitiesContainer;
    public VBox slidersContainer;
    public Personality model;
    public Label name;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void initSliders() {
        for (int i = 0; i < slidersContainer.getChildren().size(); i++) {

            HBox child = (HBox) slidersContainer.getChildren().get(i);
            RangeSlider slider = (RangeSlider) child.getChildren().get(1);
            Label label = (Label) child.getChildren().get(2);

            if(model.getSliders().containsKey(label.getText())) {
                slider.lowValueProperty().set(model.getSliders().get(label.getText()).get(0));
                slider.highValueProperty().set(model.getSliders().get(label.getText()).get(1));
            } else {
                ArrayList<Integer> slide = new ArrayList<>();
                slide.add((int)slider.lowValueProperty().get());
                slide.add((int)slider.highValueProperty().get());
                model.updateSlider(label.getText(), 0, slider.lowValueProperty().intValue());
                model.updateSlider(label.getText(), 1, slider.highValueProperty().intValue());
            }
            setSliderListener(slider, label.getText());
        }
    }

    public void initPersonalities() {
        for (int i = 0; i < personalitiesContainer.getChildren().size(); i++) {

            HBox child = (HBox) personalitiesContainer.getChildren().get(i);
            Label label = (Label) child.getChildren().get(0);
            HBox sliderContainer = (HBox) child.getChildren().get(2);
            Slider slider = (Slider) sliderContainer.getChildren().get(1);

            if(model.getPersonalities().containsKey(label.getText())) {
                slider.valueProperty().set(model.getPersonalities().get(label.getText()));
            } else {
                model.updatePersonality(label.getText(), (int) slider.getValue());
            }
            setPersonalityListener(slider, label.getText());
        }
    }

    public void setSliderListener(RangeSlider slider, String label) {
        slider.lowValueProperty().addListener(
                (observableValue, oldValue, newValue)
                        -> model.updateSlider(label, 0, newValue.intValue()));

        slider.highValueProperty().addListener(
                (observableValue, oldValue, newValue)
                        -> model.updateSlider(label, 1, newValue.intValue()));
    }

    public void setPersonalityListener(Slider slider, String label) {
        slider.valueProperty().addListener(
                (observableValue, oldValue, newValue)
                        -> model.updatePersonality(label, newValue.intValue()));
    }
    @Override
    public void setDelegate(TemplateDataDelegate delegate) {

    }

    @Override
    public void setModel(Template template) {
        this.model = template.getPersonality();
    }

    @Override
    public void setName(String name) {
        this.name.textProperty().set(name);

    }

    @Override
    public void setUserUUID(UUID uuid) {
        this.userUUID = uuid;

    }

    @Override
    public void loadModelData() {
        initPersonalities();
        initSliders();
    }
}