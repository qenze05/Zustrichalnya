package com.smakab.datingapp.zustrichalnya.Controllers.Chat;

import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class DialogController implements Initializable {
    public Label zustrichat;
    public Label instagram;
    public Label telegram;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setLabels(String telegram, String instagram, String zustrichat) {
        this.telegram.textProperty().set(telegram);
        this.instagram.textProperty().set(instagram);
        this.zustrichat.textProperty().set(zustrichat);

    }
}
