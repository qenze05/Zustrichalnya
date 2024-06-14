package com.smakab.datingapp.zustrichalnya.Controllers.Settings;

import com.smakab.datingapp.zustrichalnya.Controllers.BaseController;
import com.smakab.datingapp.zustrichalnya.Models.Settings.SettingsModel;
import com.smakab.datingapp.zustrichalnya.Views.Settings.MainView;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

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
        container.getChildren().addFirst(view.getGeneralInfoFormRenderer());
    }

    public void saveForm() {
        view.getGeneralInfoForm().persist();
    }

    public void clearForm() {
        view.getGeneralInfoForm().reset();
    }
}
