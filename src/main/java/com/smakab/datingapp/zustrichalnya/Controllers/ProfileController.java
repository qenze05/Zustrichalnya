package com.smakab.datingapp.zustrichalnya.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.net.URL;
import java.util.EventObject;
import java.util.ResourceBundle;

public class ProfileController implements Initializable {

    @FXML
    public Button mainB, personalityB, hobbiesB, prefB, viewsB;
    public AnchorPane container;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void switchMenu(ActionEvent actionEvent) {
        Object source = actionEvent.getSource();

        if(source.equals(mainB)) {
            openMenu("ProfileMainView");
        } else if (source.equals(personalityB)) {
            openMenu("PersonalityView");
        } // дописати
    }

    private void openMenu(String menu) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/smakab/datingapp/zustrichalnya/Views/Profile/Content/"+menu+".fxml"));
            HBox view = loader.load();

            AnchorPane.setTopAnchor(view, 0.0);
            AnchorPane.setRightAnchor(view, 0.0);
            AnchorPane.setBottomAnchor(view, 0.0);
            AnchorPane.setLeftAnchor(view, 0.0);

            container.getChildren().setAll(view);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
