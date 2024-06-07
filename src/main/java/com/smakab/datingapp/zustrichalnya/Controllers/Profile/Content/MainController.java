package com.smakab.datingapp.zustrichalnya.Controllers.Profile.Content;

import com.smakab.datingapp.zustrichalnya.Interfaces.ProfileDataDelegate;
import com.smakab.datingapp.zustrichalnya.Models.Person;
import com.smakab.datingapp.zustrichalnya.Models.Profile.GeneralInfo;
import com.smakab.datingapp.zustrichalnya.Views.Profile.MainView;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.javatuples.Pair;

import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class MainController extends ProfileContentClass {

    //profile image and description
    public ImageView imageView;
    public CheckBox isPhotoRealCB;
    public TextArea descriptionArea;
    public ScrollPane formContainer;
    public MainView view;
    public GeneralInfo model;
    public Button saveButton;
    public Button resetButton;
    private int currentImage = 0;
    private Stage stage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @Override
    public void setDelegate(ProfileDataDelegate delegate) {
        this.delegate = delegate;
    }

    @Override
    public void setModel(Person person) {
        this.model = person.getGeneralInfo();
    }

    @Override
    public void loadModelData() {

        view = new MainView(model, saveButton, resetButton);
        formContainer.setContent(view.getGeneralInfoFormRenderer());

        //TODO: зробити плейсхолдер якщо фото немає
        if(!model.getPhotos().isEmpty()) {
            imageView.setImage(model.getPhotos().get(0).getValue0());
        }

        descriptionArea.textProperty().set(model.getDescription());
        descriptionArea.textProperty().addListener((observableValue, oldValue, newValue) -> model.setDescription(newValue));
    }

    public void loadStage(ActionEvent event) {
        Node node = (Node) event.getSource();
        this.stage = (Stage) node.getScene().getWindow();
    }

    public void loadPhoto(ActionEvent actionEvent) {
        if(stage == null) loadStage(actionEvent);

        FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter[] extFilters =
                {new FileChooser.ExtensionFilter("Image files (*.png, *.jpg, *.jpeg, *.bmp)", Arrays.asList("*.png", "*.jpg", "*.jpeg", "*.bmp"))};
        fileChooser.getExtensionFilters().addAll(extFilters);

        File selectedFile = fileChooser.showOpenDialog(this.stage);
        if(selectedFile.isFile()) {
            addImage(selectedFile);
        }
    }

    public void addImage(File file) {
        Image image = new Image(file.toURI().toString());
        model.getPhotos().add(new Pair<>(image, false));

        switchImage(model.getPhotos().size()-1);
    }

    /**
     * @param i pass -1 to remove image
     */
    private void switchImage(int i) {
        if(model.getPhotos().size() > i && i >= 0) {
            imageView.setImage(model.getPhotos().get(i).getValue0());
            currentImage = i;
            isPhotoRealCB.setSelected(model.getPhotos().get(i).getValue1());
        } else if (i == -1) {
            imageView.setImage(null);
            isPhotoRealCB.setSelected(false);
        }
    }

    public void nextImage() {
        if(currentImage < model.getPhotos().size()-1) {
            switchImage(currentImage+1);
        } else {
            switchImage(0);
        }
    }

    public void prevImage() {
        if(currentImage > 0) {
            switchImage(currentImage-1);
        } else {
            switchImage(model.getPhotos().size()-1);
        }
    }

    public void setIsPhotoReal() {
        if(!model.getPhotos().isEmpty()) {
            Pair<Image, Boolean> pair = model.getPhotos().get(currentImage).setAt1(isPhotoRealCB.isSelected());
            model.getPhotos().set(currentImage, pair);
        }
    }

    public void deleteImage() {
        if(!model.getPhotos().isEmpty()) {

            model.getPhotos().remove(currentImage);

            if (model.getPhotos().isEmpty()) {
                switchImage(-1);
            } else if(currentImage == 0) {
                switchImage(0);
            } else {
                prevImage();
            }
        }
    }

    public void saveForm() {
        view.getGeneralInfoForm().persist();
    }

    public void clearForm() {
        view.getGeneralInfoForm().reset();
    }
}
