package com.smakab.datingapp.zustrichalnya.Controllers.Search.Content;

import com.smakab.datingapp.zustrichalnya.Interfaces.TemplateDataDelegate;
import com.smakab.datingapp.zustrichalnya.Models.Search.PersonComparable;
import com.smakab.datingapp.zustrichalnya.Models.Search.Searcher;
import com.smakab.datingapp.zustrichalnya.Models.Search.Template;
import com.smakab.datingapp.zustrichalnya.Views.Template.MainView;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class TemplateController extends TemplateContentClass {
    public Button saveB, removeB;
    public VBox infoContainer;
    public FlowPane greenFlagsContainer, redFlagsContainer;
    public Label personDescription, personName;
    public ImageView personImage;
    private int currentPerson;
    private ArrayList<PersonComparable> people = new ArrayList<>();
    MainView view;
    Template model;
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
        this.view = new MainView(model.getGeneralInfo(), saveB, removeB);
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
        infoContainer.getChildren().add(0, view.getGeneralInfoFormRenderer());
        model.getGeneralInfo().getGreenFlags().forEach(name -> greenFlagsContainer.getChildren().add(view.createFlag(name, model.getGeneralInfo().getGreenFlags(), greenFlagsContainer, "green")));
        model.getGeneralInfo().getRedFlags().forEach(name -> redFlagsContainer.getChildren().add(view.createFlag(name, model.getGeneralInfo().getRedFlags(), redFlagsContainer, "red")));

    }

    public void addFlag(FlowPane container, TreeSet<String> set, String style) {
        container.getChildren().add(view.createFlag("###", set, container, style));
    }

    public void addGreenFlag() {
        addFlag(greenFlagsContainer, model.getGeneralInfo().getGreenFlags(), "green");
    }

    public void addRegFlag() {
        addFlag(redFlagsContainer, model.getGeneralInfo().getRedFlags(), "red");
    }

    public void openProfileShowcase(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/smakab/datingapp/zustrichalnya/Views/Profile/ProfileShowcase.fxml"));
        Parent view = loader.load();
        Stage stage = new Stage();
        stage.setTitle("Огляд профілю");
        stage.setScene(new Scene(view, 1280, 900));
        stage.show();
    }

    public void findPeople(ActionEvent actionEvent) {
        System.out.println("user uuid "+this.userUUID);
        Searcher searcher = new Searcher(this.model, this.userUUID);
        people = searcher.search();
        searcher.tempSout();

        if(!people.isEmpty()) {
            currentPerson = 0;
            setPersonInfoLabels();
        }
    }

    public void nextPerson(ActionEvent actionEvent) {

    }

    public void removePerson(ActionEvent actionEvent) {
    }

    public void likePerson(ActionEvent actionEvent) {
    }

    public void prevPerson(ActionEvent actionEvent) {
    }

    private void setPersonInfoLabels() {
//        personDescription.textProperty().set(people.get(currentPerson).getPerson().getGeneralInfo().getDescription());
//        personName.textProperty().set(people.get(currentPerson).getPerson().getGeneralInfo().getFullName());
//        personImage.imageProperty().set(new Image(people.get(currentPerson).getPerson().getGeneralInfo().
//                getPhotos().getFirst().keySet().toArray(new String[1])[0]));
    }
}
