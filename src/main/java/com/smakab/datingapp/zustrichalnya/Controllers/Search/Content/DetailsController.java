package com.smakab.datingapp.zustrichalnya.Controllers.Search.Content;

import com.smakab.datingapp.zustrichalnya.Interfaces.TemplateDataDelegate;
import com.smakab.datingapp.zustrichalnya.Models.Search.Details;
import com.smakab.datingapp.zustrichalnya.Models.Search.Template;
import com.smakab.datingapp.zustrichalnya.Utils.EditableLabel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import org.controlsfx.control.RangeSlider;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.UUID;

public class DetailsController extends TemplateContentClass{
    public Slider realPhotoSlider;
    public RangeSlider ageRangeSlider;
    public ChoiceBox<String> geoLocationCB;
    public VBox gendersContainer;
    public Details model;
    public Label name;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> geoLocationOptions = FXCollections.observableArrayList("Тільки місто", "Можна область", "Можна країна", "Всюди");
        ObservableList<String> noGeoLocationOptions = FXCollections.observableArrayList("Допускати", "Допускати рідше", "Не допускати");

        geoLocationCB.setItems(geoLocationOptions);

        geoLocationCB.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> model.setGeoLocation(t1));

        realPhotoSlider.valueProperty().addListener((observableValue, number, t1) -> model.setRealPhoto(t1.intValue()));
        ageRangeSlider.lowValueProperty().addListener((observableValue, number, t1) -> model.setLowerAge(t1.intValue()));
        ageRangeSlider.highValueProperty().addListener((observableValue, number, t1) -> model.setUpperAge(t1.intValue()));

    }

    @Override
    public void setDelegate(TemplateDataDelegate delegate) {
        this.delegate = delegate;
    }

    @Override
    public void setModel(Template template) {
        this.model = template.getDetails();
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
        this.realPhotoSlider.valueProperty().set(model.getRealPhoto());
        this.ageRangeSlider.adjustLowValue(model.getAge().get(0));
        this.ageRangeSlider.adjustHighValue(model.getAge().get(1));
        this.geoLocationCB.getSelectionModel().select(model.getGeoLocation());
        this.model.getGenders().forEach((name, value) -> gendersContainer.getChildren().add(createGender(name, value)));
    }


    public void addGender(ActionEvent actionEvent) {
        gendersContainer.getChildren().add(createGender("###", 5));
    }

    public HBox createGender(String name, int value) {

        HBox container = new HBox();
        EditableLabel label = new EditableLabel(name);
        Label label1 = new Label("Небажано");
        Label label2 = new Label("Бажано");
        Separator separator = new Separator(Orientation.VERTICAL);
        Slider slider = new Slider(0, 10, value);

        container.spacingProperty().set(20);
        container.alignmentProperty().set(Pos.CENTER_LEFT);
        container.prefWidthProperty().set(600);
        container.paddingProperty().set(new Insets(0, 0, 0, 20));
        container.getStylesheets().add("styles.css");

        separator.prefHeightProperty().set(40);

        slider.setShowTickLabels(true);
        slider.setSnapToTicks(true);
        slider.minProperty().set(-5);
        slider.maxProperty().set(5);
        slider.valueProperty().set(0);
        slider.majorTickUnitProperty().set(1);
        slider.minorTickCountProperty().set(1);
        slider.prefWidthProperty().set(300);
        slider.prefHeightProperty().set(50);
        slider.valueProperty().addListener((observableValue, number, t1) -> model.editGender(label.getText(), t1.intValue()));


        label.setFont(new Font(20));
        label.textProperty().addListener((observableValue, s, t1) -> {
            if(!t1.isEmpty()) {
                model.renameGender(label.backup, t1, (int) slider.getValue());
            } else {
                label.textProperty().set(label.backup);
            }
        });
        label.getStyleClass().add("label-personality");

        Button delete = new Button("X");
        delete.setOnAction(event -> {
            model.removeGender(label.getText());
            gendersContainer.getChildren().remove(container);
        });
        delete.getStyleClass().add("delete-button");

        label1.fontProperty().set(new Font(20));
        label1.getStyleClass().add("label-personality");
        label2.getStyleClass().add("label-personality");
        label2.fontProperty().set(new Font(20));

        container.getChildren().addAll(label, separator, delete, label1, slider, label2);

        return container;
    }
}
