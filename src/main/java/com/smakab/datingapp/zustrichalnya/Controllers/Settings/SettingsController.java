package com.smakab.datingapp.zustrichalnya.Controllers.Settings;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.smakab.datingapp.zustrichalnya.Controllers.BaseController;
import com.smakab.datingapp.zustrichalnya.JsonUtils.JsonUtil;
import com.smakab.datingapp.zustrichalnya.Models.Profile.GeneralInfo;
import com.smakab.datingapp.zustrichalnya.Models.Profile.Hobbies;
import com.smakab.datingapp.zustrichalnya.Models.Settings.SettingsModel;
import com.smakab.datingapp.zustrichalnya.Views.Settings.MainView;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.UUID;

public class SettingsController extends BaseController implements Initializable {
    public VBox container;
    public SettingsModel model;
    public MainView view;
    public Button saveButton;
    public Button resetButton;

    @Override
    public void setProfileUUID(UUID uuid) {
        this.uuid = uuid;

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.model = new SettingsModel();
        this.view = new MainView(model, saveButton, resetButton);
        container.getChildren().add(0,view.getGeneralInfoFormRenderer());
    }

    public void loadSettings(){
        try {
            File jsonFile = new File("src\\main\\resources\\local-database\\"+this.uuid+"\\settings\\contacts.json");
            if(jsonFile.exists()) {
                SettingsModel model = JsonUtil.fromJsonFile(jsonFile, SettingsModel.class);
                this.model.zustrichatProperty().set(model.getZustrichat());
                this.model.instagramProperty().set(model.getInstagram());
                this.model.telegramProperty().set(model.getTelegram());
                System.out.println("loaded "+model.getZustrichat());
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void saveForm() {
        view.getGeneralInfoForm().persist();
        try {
            model.writeInfoToFile("src\\main\\resources\\local-database\\"+this.uuid+"\\settings\\contacts.json");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void clearForm() {
        view.getGeneralInfoForm().reset();
    }
}
