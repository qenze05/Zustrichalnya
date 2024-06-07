package com.smakab.datingapp.zustrichalnya.Controllers.Profile.Content;

import com.smakab.datingapp.zustrichalnya.Interfaces.ProfileDataDelegate;
import com.smakab.datingapp.zustrichalnya.Models.Person;
import com.smakab.datingapp.zustrichalnya.Models.Profile.Views;
import com.smakab.datingapp.zustrichalnya.Utils.EditableLabel;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

import java.net.URL;
import java.util.ResourceBundle;

public class ViewsController extends ProfileContentClass {
    public TextField religionField;
    public TextField langField;
    public FlowPane movementsContainer;
    public Views model;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    @Override
    public void setDelegate(ProfileDataDelegate delegate) {
        this.delegate = delegate;
    }

    @Override
    public void setModel(Person person) {
        this.model = person.getViews();
    }

    @Override
    public void loadModelData() {
        religionField.textProperty().set(model.getReligion());
        religionField.textProperty().addListener((observableValue, oldV, newV) -> model.setReligion(newV));

        langField.textProperty().set(model.getLanguage());
        langField.textProperty().addListener((observableValue, oldV, newV) -> model.setLanguage(newV));

        model.getMovements().forEach(name -> movementsContainer.getChildren().add(createMovement(name)));
    }

    public HBox createMovement(String name) {

        HBox container = new HBox();
        container.setPadding(new Insets(5, 5, 5, 5));
        container.spacingProperty().set(10);
        container.styleProperty().set("-fx-background-color: white;");
        container.alignmentProperty().set(Pos.CENTER);

        EditableLabel label = new EditableLabel(name);
        label.setFont(new Font(20));
        label.textProperty().addListener((observableValue, s, t1) -> {
            if(!t1.isEmpty()) {
                model.renameMovement(label.backup, t1);
            } else {
                label.textProperty().set(label.backup);
            }
        });

        Button delete = new Button("X");
        delete.setOnAction(event -> {
            model.removeMovement(label.getText());
            movementsContainer.getChildren().remove(container);
        });

        container.getChildren().add(label);
        container.getChildren().add(delete);

        return container;
    }

    public void addMovement(ActionEvent actionEvent) {
        movementsContainer.getChildren().add(createMovement("###"));
    }
}
