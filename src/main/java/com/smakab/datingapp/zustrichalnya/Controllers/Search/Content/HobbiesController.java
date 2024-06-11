package com.smakab.datingapp.zustrichalnya.Controllers.Search.Content;

import com.smakab.datingapp.zustrichalnya.Interfaces.TemplateDataDelegate;
import com.smakab.datingapp.zustrichalnya.Models.Search.Hobbies;
import com.smakab.datingapp.zustrichalnya.Models.Search.Template;
import com.smakab.datingapp.zustrichalnya.Utils.EditableLabel;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.net.URL;
import java.util.ResourceBundle;

public class HobbiesController extends TemplateContentClass{

    public VBox subculturesContainer;
    public VBox hobbiesContainer;
    public Label name;
    Hobbies model;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @Override
    public void setDelegate(TemplateDataDelegate delegate) {
        this.delegate = delegate;
    }

    @Override
    public void setModel(Template template) {
        this.model = template.getHobbies();
    }

    @Override
    public void setName(String name) {
        this.name.textProperty().set(name);

    }

    @Override
    public void loadModelData() {
        model.getSubcultures().forEach((name, value) -> subculturesContainer.getChildren().add(createSubculture(name, value)));
        model.getHobbies().forEach((name, value) -> hobbiesContainer.getChildren().add(createHobby(name, value)));
    }

    public void addSubculture(ActionEvent actionEvent) {
        subculturesContainer.getChildren().add(createSubculture("###", 5));
    }

    public void addHobby(ActionEvent actionEvent) {
        hobbiesContainer.getChildren().add(createHobby("###", 5));

    }

    public HBox createHobby(String name, int value) {

        HBox container = new HBox();
        EditableLabel label = new EditableLabel(name);

        Slider slider = new Slider(0, 10, value);

        container.spacingProperty().set(20);
        container.alignmentProperty().set(Pos.CENTER_LEFT);
        container.prefWidthProperty().set(600);
        container.paddingProperty().set(new Insets(0, 0, 0, 20));


        slider.setShowTickLabels(true);
        slider.setSnapToTicks(true);
        slider.minProperty().set(-5);
        slider.maxProperty().set(5);
        slider.valueProperty().set(0);
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
        label.getStyleClass().add("label-personality");

        Button delete = new Button("X");
        delete.setOnAction(event -> {
            model.removeHobby(label.getText());
            hobbiesContainer.getChildren().remove(container);
        });
        delete.getStyleClass().add("delete-button");


        container.getChildren().addAll(label, slider, delete);

        return container;
    }

    public HBox createSubculture(String name, int value) {

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
        slider.minProperty().set(-5);
        slider.maxProperty().set(5);
        slider.valueProperty().set(0);
        slider.majorTickUnitProperty().set(1);
        slider.minorTickCountProperty().set(1);
        slider.prefWidthProperty().set(300);
        slider.prefHeightProperty().set(50);
        slider.valueProperty().addListener((observableValue, number, t1) -> model.editSubculture(label.getText(), t1.intValue()));


        label.setFont(new Font(20));
        label.textProperty().addListener((observableValue, s, t1) -> {
            if(!t1.isEmpty()) {
                model.renameSubculture(label.backup, t1, (int) slider.getValue());
            } else {
                label.textProperty().set(label.backup);
            }
        });
        label.getStyleClass().add("label-personality");

        Button delete = new Button("X");
        delete.setOnAction(event -> {
            model.removeSubculture(label.getText());
            subculturesContainer.getChildren().remove(container);
        });
        delete.getStyleClass().add("delete-button");


        container.getChildren().addAll(label, slider, delete);

        return container;
    }

}
