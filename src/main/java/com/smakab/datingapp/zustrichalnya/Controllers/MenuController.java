package com.smakab.datingapp.zustrichalnya.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable {
    public AnchorPane container;
    public Button profileB, searchB, chatB, settingsB, logoutB;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    public void switchMenu(ActionEvent actionEvent) {
        Object source = actionEvent.getSource();

        if(source.equals(profileB)) {
            openMenu("Profile/ProfileView");
        } else if (source.equals(searchB)) {
            openMenu("Search/SearchView");
        } // дописати
    }

    private void openMenu(String menu) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/smakab/datingapp/zustrichalnya/Views/"+menu+".fxml"));
            SplitPane view = loader.load();

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
