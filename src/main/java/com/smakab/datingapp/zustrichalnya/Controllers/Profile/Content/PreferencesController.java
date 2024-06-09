package com.smakab.datingapp.zustrichalnya.Controllers.Profile.Content;

import com.smakab.datingapp.zustrichalnya.Interfaces.ProfileDataDelegate;
import com.smakab.datingapp.zustrichalnya.Models.Person;
import com.smakab.datingapp.zustrichalnya.Models.Profile.Preferences;
import com.smakab.datingapp.zustrichalnya.Utils.EditableLabel;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.net.URL;
import java.util.ResourceBundle;

public class PreferencesController extends ProfileContentClass {
    public TextArea description;
    public VBox prefContainer;
    private Preferences model;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @Override
    public void setDelegate(ProfileDataDelegate delegate) {
        this.delegate = delegate;
    }

    @Override
    public void setModel(Person person) {
        this.model = person.getPreferences();
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
        container.getStylesheets().add("styles.css");

        slider.setShowTickLabels(true);
        slider.setSnapToTicks(true);
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
        label.getStyleClass().add("label-personality");

        Button delete = new Button("X");
        delete.setOnAction(event -> {
            model.removePreference(label.getText());
            prefContainer.getChildren().remove(container);
        });
        delete.getStyleClass().add("delete-button");

        container.getChildren().add(label);
        container.getChildren().add(slider);
        container.getChildren().add(delete);

        return container;
    }
}
