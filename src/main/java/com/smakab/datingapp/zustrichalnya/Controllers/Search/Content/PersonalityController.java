package com.smakab.datingapp.zustrichalnya.Controllers.Search.Content;

import com.smakab.datingapp.zustrichalnya.Interfaces.TemplateDataDelegate;
import com.smakab.datingapp.zustrichalnya.Models.Search.Personality;
import com.smakab.datingapp.zustrichalnya.Models.Search.Template;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class PersonalityController extends TemplateContentClass {
    public VBox personalitiesContainer;
    public VBox slidersContainer;
    public Personality model;

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

    public void setSliderListener(Slider slider, String label) {
        slider.valueProperty().addListener(
                (observableValue, oldValue, newValue)
                        -> model.updateSlider(label, newValue.intValue()));
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
    public void loadModelData() {
        initPersonalities();
        initSliders();
    }
}
