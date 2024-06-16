package com.smakab.datingapp.zustrichalnya.Controllers.Profile.Content;

import com.smakab.datingapp.zustrichalnya.Models.Person;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class ProfileShowcase implements Initializable {
    public ImageView imageView;
    public int currentImage;
    public Label isPhotoRealCB;

    public Person model;
    public Label name, age, description, region,
            city, sex, gender, orientation,
            workStatus, university, profession, requestList;

    private ArrayList<HashMap<String, Boolean>> photos = new ArrayList<>();
    private boolean noPhotos = true;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setModel(Person model) {
        this.model = model;
    }

    public void loadModelData() {
        photos = model.getGeneralInfo().getPhotos();
        noPhotos = photos == null || photos.isEmpty();

        loadPhoto();
        name.textProperty().set(model.getGeneralInfo().fullName());
        age.textProperty().set(String.valueOf(model.getGeneralInfo().getAge()));
        description.textProperty().set(model.getGeneralInfo().getDescription());
        region.textProperty().set(model.getGeneralInfo().getRegion());
        city.textProperty().set(model.getGeneralInfo().getCity());
        sex.textProperty().set(model.getGeneralInfo().getSex());
        gender.textProperty().set(model.getGeneralInfo().getGender());
        orientation.textProperty().set(model.getGeneralInfo().getOrientation());
        university.textProperty().set(model.getGeneralInfo().getUniversity());
        profession.textProperty().set(model.getGeneralInfo().getProfession());

        boolean wordS = model.getGeneralInfo().isIsWorking();
        if(wordS) workStatus.textProperty().set("✓");
        else workStatus.textProperty().set(" ");

    }

    public Person getModel() {
        return model;
    }

    public void setIsPhotoRealLabel(Boolean value) {
        if(value) {
            isPhotoRealCB.textProperty().set("✓");
        } else {
            isPhotoRealCB.textProperty().set(" ");
        }
    }

    public void prevImage(ActionEvent actionEvent) {
        currentImage--;
        if(currentImage < 0) currentImage = photos.size()-1;
        loadPhoto();
    }

    public void loadPhoto() {
        if(noPhotos) return;

        imageView.imageProperty().set(new Image(photos.get(currentImage).keySet().toArray(new String[1])[0]));
        setIsPhotoRealLabel(photos.get(currentImage).values().toArray(new Boolean[1])[0]);
    }

    public void nextImage(ActionEvent actionEvent) {
        currentImage++;
        if(currentImage >= photos.size()) currentImage = 0;
        loadPhoto();
    }




}
