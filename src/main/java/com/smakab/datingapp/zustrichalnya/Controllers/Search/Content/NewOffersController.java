package com.smakab.datingapp.zustrichalnya.Controllers.Search.Content;

import com.smakab.datingapp.zustrichalnya.Interfaces.TemplateDataDelegate;
import com.smakab.datingapp.zustrichalnya.Models.Search.Template;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.UUID;

public class NewOffersController extends TemplateContentClass {

    public Template model;
    public FlowPane greenFlagsContainer;
    public FlowPane redFlagsContainer;
    public VBox subculturesContainer;
    public VBox hobbiesContainer;
    public VBox prefContainer;
    public VBox religionsContainer;
    public VBox languagesContainer;
    public VBox movementsContainer;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @Override
    public void setDelegate(TemplateDataDelegate delegate) {
        this.delegate = delegate;
    }

    @Override
    public void setModel(Template template) {
        this.model = template;
    }

    @Override
    public void setName(String name) {

    }

    @Override
    public void setUserUUID(UUID uuid) {
        this.userUUID = uuid;

    }

    @Override
    public void loadModelData() {

    }

    public void addGreenFlag(ActionEvent actionEvent) {
    }

    public void addRegFlag(ActionEvent actionEvent) {
    }

    public void addSubculture(ActionEvent actionEvent) {
    }

    public void addHobby(ActionEvent actionEvent) {
    }

    public void addPreference(ActionEvent actionEvent) {
    }

    public void addReligion(ActionEvent actionEvent) {
    }

    public void addLanguage(ActionEvent actionEvent) {
    }

    public void addMovement(ActionEvent actionEvent) {

    }

    public void openProfileShowcase(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/smakab/datingapp/zustrichalnya/Views/Profile/ProfileShowcase.fxml"));
        Parent view = loader.load();
        Stage stage = new Stage();
        stage.setTitle("Огляд профілю");
        stage.setScene(new Scene(view, 1280, 900));
        stage.show();
    }
}
