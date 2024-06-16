package com.smakab.datingapp.zustrichalnya.Controllers.Profile.Content;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.smakab.datingapp.zustrichalnya.JsonUtils.JsonUtil;
import com.smakab.datingapp.zustrichalnya.Interfaces.ProfileDataDelegate;
import com.smakab.datingapp.zustrichalnya.Models.Person;
import com.smakab.datingapp.zustrichalnya.Models.Profile.Hobbies;
import com.smakab.datingapp.zustrichalnya.Utils.EditableLabel;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HobbiesController extends ProfileContentClass {
    public FlowPane subculturesContainer;
    public VBox hobbiesContainer;
    private Hobbies model;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public HBox createSubculture(String name) {

        HBox container = new HBox();
        container.setPadding(new Insets(5, 5, 5, 5));
        container.spacingProperty().set(10);
        container.getStylesheets().add("styles.css");
        container.getStyleClass().add("label-personality");
        container.alignmentProperty().set(Pos.CENTER);

        EditableLabel label = new EditableLabel(name);
        label.setFont(new Font(20));
        label.textProperty().addListener((observableValue, s, t1) -> {
            if(!t1.isEmpty()) {
                model.renameSubculture(label.backup, t1);
            } else {
                label.textProperty().set(label.backup);
            }
        });

        Button delete = new Button("X");
        delete.setOnAction(event -> {
            model.removeSubCulture(label.getText());
            subculturesContainer.getChildren().remove(container);
        });
        delete.getStyleClass().add("delete-button");

        container.getChildren().add(label);
        container.getChildren().add(delete);

        return container;
    }
    @Override
    public void setDelegate(ProfileDataDelegate delegate) {
        this.delegate = delegate;
    }

    @Override
    public void setModel(Person person) {
        this.model = person.getHobbies();
    }

    @Override
    public void loadModelData() {
        loadHobbies();
        System.out.println(model.getHobbies().size());
        model.getSubcultures().forEach(name -> subculturesContainer.getChildren().add(createSubculture(name)));
        model.getHobbies().forEach((name, value) -> hobbiesContainer.getChildren().add(createHobby(name, value)));
    }

    public void addHobby() {
        hobbiesContainer.getChildren().add(createHobby("###", 5));
    }

    public void loadHobbies(){
        try {
            File jsonFile = new File("src\\main\\resources\\local-database\\"+model.uuid+"\\profile-data\\hobbies.json");
            if(jsonFile.exists()) {
                model = JsonUtil.fromJsonFile(jsonFile, Hobbies.class);
                System.out.println("loaded");
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void saveHobbies(){
        try {
            model.writeInfoToFile("src\\main\\resources\\local-database\\"+model.uuid+"\\profile-data\\hobbies.json");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void addSubculture() {
        subculturesContainer.getChildren().add(createSubculture("###"));
    }

    public HBox createHobby(String name, Integer value) {

        HBox container = new HBox();
        EditableLabel label = new EditableLabel(name);
        Slider slider = new Slider(0, 10, value);

        container.spacingProperty().set(20);
        container.alignmentProperty().set(Pos.CENTER_LEFT);
        container.prefWidthProperty().set(600);
        container.paddingProperty().set(new Insets(0, 0, 0, 20));
        container.getStylesheets().add("styles.css");

        slider.setShowTickLabels(true);
        slider.setSnapToTicks(true);
        slider.majorTickUnitProperty().set(1);
        slider.minorTickCountProperty().set(1);
        slider.prefWidthProperty().set(300);
        slider.prefHeightProperty().set(50);
        slider.valueProperty().addListener((observableValue, number, t1) -> model.editHobby(label.getText(), t1.intValue()));


        label.setFont(new Font(20));
        label.getStyleClass().add("label-personality");
        label.textProperty().addListener((observableValue, s, t1) -> {
            if(!t1.isEmpty()) {
                model.renameHobby(label.backup, t1, (int) slider.getValue());
            } else {
                label.textProperty().set(label.backup);
            }
        });

        Button delete = new Button("X");
        delete.setOnAction(event -> {
            model.removeHobby(label.getText());
            hobbiesContainer.getChildren().remove(container);
        });
        delete.getStyleClass().add("delete-button");

        container.getChildren().add(label);
        container.getChildren().add(slider);
        container.getChildren().add(delete);

        return container;
    }

    public void saveForm(ActionEvent actionEvent) {
        saveHobbies();
    }
}
