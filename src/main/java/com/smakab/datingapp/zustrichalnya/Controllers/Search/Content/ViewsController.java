package com.smakab.datingapp.zustrichalnya.Controllers.Search.Content;

import com.smakab.datingapp.zustrichalnya.Interfaces.TemplateDataDelegate;
import com.smakab.datingapp.zustrichalnya.Models.Search.Template;
import com.smakab.datingapp.zustrichalnya.Models.Search.Views;
import com.smakab.datingapp.zustrichalnya.Utils.EditableLabel;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.net.URL;
import java.util.ResourceBundle;

public class ViewsController extends TemplateContentClass {
    public VBox religionsContainer;
    public VBox languagesContainer;
    public VBox movementsContainer;
    public Views model;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @Override
    public void setDelegate(TemplateDataDelegate delegate) {
        this.delegate = delegate;
    }

    @Override
    public void setModel(Template template) {
        this.model = template.getViews();
    }

    @Override
    public void loadModelData() {
        model.getReligions().forEach((name, value) -> religionsContainer.getChildren().add(createReligion(name, value)));
        model.getLanguages().forEach((name, value) -> languagesContainer.getChildren().add(createLanguage(name, value)));
        model.getMovements().forEach((name, value) -> movementsContainer.getChildren().add(createMovement(name, value)));
    }

    public void addReligion(ActionEvent actionEvent) {
        religionsContainer.getChildren().add(createReligion("###", 5));
    }

    public void addLanguage(ActionEvent actionEvent) {
        languagesContainer.getChildren().add(createLanguage("###", 5));

    }

    public void addMovement(ActionEvent actionEvent) {
        movementsContainer.getChildren().add(createMovement("###", 5));
    }

    public HBox createLanguage(String name, int value) {

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
        slider.valueProperty().addListener((observableValue, number, t1) -> model.editLanguage(label.getText(), t1.intValue()));


        label.setFont(new Font(20));
        label.textProperty().addListener((observableValue, s, t1) -> {
            if(!t1.isEmpty()) {
                model.renameLanguage(label.backup, t1, (int) slider.getValue());
            } else {
                label.textProperty().set(label.backup);
            }
        });

        Button delete = new Button("X");
        delete.setOnAction(event -> {
            model.removeLanguage(label.getText());
            languagesContainer.getChildren().remove(container);
        });


        container.getChildren().addAll(label, slider, delete);

        return container;
    }

    public HBox createReligion(String name, int value) {

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
        slider.valueProperty().addListener((observableValue, number, t1) -> model.editReligion(label.getText(), t1.intValue()));


        label.setFont(new Font(20));
        label.textProperty().addListener((observableValue, s, t1) -> {
            if(!t1.isEmpty()) {
                model.renameReligion(label.backup, t1, (int) slider.getValue());
            } else {
                label.textProperty().set(label.backup);
            }
        });

        Button delete = new Button("X");
        delete.setOnAction(event -> {
            model.removeReligion(label.getText());
            religionsContainer.getChildren().remove(container);
        });


        container.getChildren().addAll(label, slider, delete);

        return container;
    }

    public HBox createMovement(String name, int value) {

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
        slider.valueProperty().addListener((observableValue, number, t1) -> model.editMovement(label.getText(), t1.intValue()));


        label.setFont(new Font(20));
        label.textProperty().addListener((observableValue, s, t1) -> {
            if(!t1.isEmpty()) {
                model.renameMovement(label.backup, t1, (int) slider.getValue());
            } else {
                label.textProperty().set(label.backup);
            }
        });

        Button delete = new Button("X");
        delete.setOnAction(event -> {
            model.removeMovement(label.getText());
            movementsContainer.getChildren().remove(container);
        });


        container.getChildren().addAll(label, slider, delete);

        return container;
    }
}
