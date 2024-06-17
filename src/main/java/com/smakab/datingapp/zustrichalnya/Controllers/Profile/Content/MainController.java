package com.smakab.datingapp.zustrichalnya.Controllers.Profile.Content;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.smakab.datingapp.zustrichalnya.JsonUtils.JsonUtil;
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
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
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

        loadMain();

        view = new MainView(model, saveButton, resetButton);
        formContainer.setContent(view.getGeneralInfoFormRenderer());


        //TODO: зробити плейсхолдер якщо фото немає
        if(!model.getPhotos().isEmpty()) {

            imageView.setImage(new Image(model.getPhotos().get(0).keySet().toArray(new String[1])[0]));
        }

        descriptionArea.textProperty().set(model.getDescription());
        descriptionArea.textProperty().addListener((observableValue, oldValue, newValue) -> model.setDescription(newValue));


    }

    public void loadStage(ActionEvent event) {
        Node node = (Node) event.getSource();
        this.stage = (Stage) node.getScene().getWindow();
    }

    public void loadPhoto(ActionEvent actionEvent) throws IOException {
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

    public void addImage(File file) throws IOException {

        File imgFile = new File("src\\main\\resources\\local-database\\"+model.uuid+"\\profile-data\\"+file.getName());
        Path path = Paths.get(imgFile.toURI()).getParent();
        if (path != null) {
            Files.createDirectories(path);
        }

        Files.copy(file.toPath(), imgFile.toPath());

        Image image = new Image(imgFile.toURI().toString());

        HashMap<String, Boolean> img = new HashMap<>();
        img.put(image.getUrl(), false);

        model.getPhotos().add(img);

        switchImage(model.getPhotos().size()-1);
    }

    /**
     * @param i pass -1 to remove image
     */
    private void switchImage(int i) {
        if(model.getPhotos().size() > i && i >= 0) {
            imageView.setImage(new Image(model.getPhotos().get(i).keySet().toArray(new String[1])[0]));
            currentImage = i;
            isPhotoRealCB.setSelected(model.getPhotos().get(i).values().toArray(new Boolean[1])[0]);
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
            HashMap<String, Boolean> img = model.getPhotos().get(currentImage);
            img.put(model.getPhotos().get(currentImage).keySet().toArray(new String[1])[0], isPhotoRealCB.isSelected());
            model.getPhotos().set(currentImage, img);
        }
    }

    public void deleteImage() throws IOException {
        if(!model.getPhotos().isEmpty()) {

            String[] pathSplit = model.getPhotos().get(currentImage).keySet().toArray(new String[1])[0].split("/");
            String path = pathSplit[pathSplit.length-1];
            Files.delete(Paths.get("src\\main\\resources\\local-database\\"+model.uuid+"\\profile-data\\"+path));

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
        saveMain();
    }

    public void saveMain(){
        try {
            model.writeInfoToFile("src\\main\\resources\\local-database\\"+model.uuid+"\\profile-data\\main.json");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadMain(){
        try {
            File jsonFile = new File("src\\main\\resources\\local-database\\"+model.uuid+"\\profile-data\\main.json");
            if(jsonFile.exists()) {
                model = JsonUtil.fromJsonFile(jsonFile, GeneralInfo.class);
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void clearForm() {
        view.getGeneralInfoForm().reset();
    }
}