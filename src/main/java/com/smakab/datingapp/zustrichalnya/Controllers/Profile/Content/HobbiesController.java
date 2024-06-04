package com.smakab.datingapp.zustrichalnya.Controllers.Profile.Content;

import com.smakab.datingapp.zustrichalnya.Interfaces.ProfileDataDelegate;
import com.smakab.datingapp.zustrichalnya.Models.Person;
import com.smakab.datingapp.zustrichalnya.Models.Profile.Hobbies;
import com.smakab.datingapp.zustrichalnya.Utils.EditableLabel;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

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
        container.styleProperty().set("-fx-background-color: white;");
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
        loadModelData();
    }

    @Override
    public void loadModelData() {
        System.out.println(model.getHobbies().size());
        model.getSubcultures().forEach(name -> subculturesContainer.getChildren().addLast(createSubculture(name)));
        model.getHobbies().forEach((name, value) -> hobbiesContainer.getChildren().addLast(createHobby(name, value)));
    }

    public void addHobby() {
        hobbiesContainer.getChildren().addLast(createHobby("###", 5));
    }

    public void addSubculture() {
        subculturesContainer.getChildren().addLast(createSubculture("###"));
    }

    public HBox createHobby(String name, Integer value) {

        HBox container = new HBox();
        EditableLabel label = new EditableLabel(name);
        Slider slider = new Slider(0, 10, value);

        container.spacingProperty().set(20);
        container.alignmentProperty().set(Pos.CENTER_LEFT);
        container.prefWidthProperty().set(600);
        container.paddingProperty().set(new Insets(0, 0, 0, 20));

        slider.setShowTickLabels(true);
        slider.setSnapToTicks(true);
        slider.majorTickUnitProperty().set(1);
        slider.minorTickCountProperty().set(1);
        slider.prefWidthProperty().set(300);
        slider.prefHeightProperty().set(50);
        slider.valueProperty().addListener((observableValue, number, t1) -> model.editHobby(label.getText(), t1.intValue()));


        label.setFont(new Font(20));
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

        container.getChildren().add(label);
        container.getChildren().add(slider);
        container.getChildren().add(delete);


        return container;
    }
}