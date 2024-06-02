package com.smakab.datingapp.zustrichalnya.Controllers.Profile.Content;

import com.smakab.datingapp.zustrichalnya.Interfaces.ProfileDataDelegate;
import com.smakab.datingapp.zustrichalnya.Models.Person;
import com.smakab.datingapp.zustrichalnya.Models.Profile.GeneralInfo;
import com.smakab.datingapp.zustrichalnya.Views.ProfileView;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.javatuples.Pair;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController extends ProfileContentClass {

    //profile image and description
    public ImageView imageView;
    public CheckBox isPhotoRealCB;
    public TextArea descriptionArea;
    public ScrollPane formContainer;
    public ProfileView view;
    public GeneralInfo model;

//    //general info
//    public TextField surnameField, nameField, patronymicField, nickname;
//    public ChoiceBox visibilityCB;
//
//    //birthdate
//    public DatePicker birthDatePicker;
//    public CheckBox isBirthDateShownCB;
//
//    //location
//    public TextField region, city;
//
//    //sex and gender
//    public ChoiceBox sexCB;
//    public TextField genderField, sexOrientationField;
//
//    //studying field and work
//    public TextField university, workField;
//    public ChoiceBox hasWorkCB;

    private int currentImage = 0;
    private Stage stage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        model = new GeneralInfo();
        view = new ProfileView(model);
        formContainer.setContent(view.getGeneralInfoFormRenderer());
    }

    @Override
    public void setDelegate(ProfileDataDelegate delegate) {
        this.delegate = delegate;
    }

    @Override
    public void setPerson(Person person) {
        try{
            this.person = (Person) person.clone();
            loadPersonInfo();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    public void loadPersonInfo() {
        //TODO: зробити плейсхолдер якщо фото немає

        if(!person.getPhotos().isEmpty()) {
            imageView.setImage(person.getPhotos().get(0).getValue0());
        }
    }

    public void loadStage(ActionEvent event) {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        this.stage = stage;
    }

    public void loadPhoto(ActionEvent actionEvent) {
        if(stage == null) loadStage(actionEvent);

        FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter[] extFilters =
                {new FileChooser.ExtensionFilter("PNG files (*.png)", "*.png"),
                        new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.jpg"),
                        new FileChooser.ExtensionFilter("JPEG files (*.jpeg)", "*.jpeg"),
                        new FileChooser.ExtensionFilter("BMP files (*.bmp)", "*.bmp")};
        fileChooser.getExtensionFilters().addAll(extFilters);

        File selectedFile = fileChooser.showOpenDialog(this.stage);
        if(selectedFile.isFile()) {
            addImage(selectedFile);
        }
    }

    public void addImage(File file) {
        Image image = new Image(file.toURI().toString());
        person.getPhotos().add(new Pair<>(image, false));

        switchImage(person.getPhotos().size()-1);
    }

    /**
     * @param i pass -1 to remove image
     */
    private void switchImage(int i) {
        if(person.getPhotos().size() > i && i >= 0) {
            imageView.setImage(person.getPhotos().get(i).getValue0());
            currentImage = i;
            isPhotoRealCB.setSelected(person.getPhotos().get(i).getValue1());
            System.out.println(person.getPhotos().get(i).getValue1());
        } else if (i == -1) {
            imageView.setImage(null);
            isPhotoRealCB.setSelected(false);
        }
    }

    public void nextImage() {
        if(currentImage < person.getPhotos().size()-1) {
            switchImage(currentImage+1);
        } else {
            switchImage(0);
        }
    }

    public void prevImage() {
        if(currentImage > 0) {
            switchImage(currentImage-1);
        } else {
            switchImage(person.getPhotos().size()-1);
        }
    }

    public void setIsPhotoReal() {
        if(!person.getPhotos().isEmpty()) {
            Pair pair = person.getPhotos().get(currentImage).setAt1(isPhotoRealCB.isSelected());
            person.getPhotos().set(currentImage, pair);
        }
    }

    public void deleteImage() {
        if(!person.getPhotos().isEmpty()) {

            person.getPhotos().remove(currentImage);

            if (person.getPhotos().isEmpty()) {
                switchImage(-1);
            } else if(currentImage == 0) {
                switchImage(0);
            } else {
                prevImage();
            }
        }
    }
}
