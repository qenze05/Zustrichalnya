package com.smakab.datingapp.zustrichalnya.Controllers.Search.Content;

import com.smakab.datingapp.zustrichalnya.Interfaces.TemplateDataDelegate;
import com.smakab.datingapp.zustrichalnya.Models.Search.Preferences;
import com.smakab.datingapp.zustrichalnya.Models.Search.Template;
import com.smakab.datingapp.zustrichalnya.Utils.EditableLabel;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.net.URL;
import java.util.ResourceBundle;

public class PreferencesController extends TemplateContentClass {

    public VBox prefContainer;
    public Preferences model;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @Override
    public void setDelegate(TemplateDataDelegate delegate) {
        this.delegate = delegate;
    }

    @Override
    public void setModel(Template template) {
        this.model = template.getPreferences();
    }

    @Override
    public void loadModelData() {
        model.getPreferences().forEach((name, value) -> prefContainer.getChildren().add(createPreference(name, value)));
    }

    public void addPreference() {
        prefContainer.getChildren().add(createPreference("###", 5));
    }

    public HBox createPreference(String name, int value) {

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
        slider.valueProperty().addListener((observableValue, number, t1) -> model.editPreference(label.getText(), t1.intValue()));


        label.setFont(new Font(20));
        label.textProperty().addListener((observableValue, s, t1) -> {
            if(!t1.isEmpty()) {
                model.renamePreference(label.backup, t1, (int) slider.getValue());
            } else {
                label.textProperty().set(label.backup);
            }
        });

        Button delete = new Button("X");
        delete.setOnAction(event -> {
            model.removePreference(label.getText());
            prefContainer.getChildren().remove(container);
        });


        container.getChildren().addAll(label, slider, delete);

        return container;
    }
}
