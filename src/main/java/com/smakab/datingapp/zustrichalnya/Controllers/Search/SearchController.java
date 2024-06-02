package com.smakab.datingapp.zustrichalnya.Controllers.Search;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SearchController implements Initializable {

    @FXML
    public Button mainB, selectedTemplateB, generalInfoB, personalityB, hobbiesB, prefB, viewsB, newOffersB;
    public AnchorPane container;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void switchMenu(ActionEvent actionEvent) {
        Object source = actionEvent.getSource();

        if(source.equals(mainB)) {
            loadView("SearchMainView");
        } else if (source.equals(selectedTemplateB)) {
            loadView("Template/MainView");
        } else if (source.equals(generalInfoB)) {
            loadView("Template/GeneralInfo");
        } else if (source.equals(personalityB)) {
            loadView("Template/PersonalityView");
        } else if (source.equals(hobbiesB)) {
            loadView("Template/HobbiesView");
        } else if (source.equals(prefB)) {
            loadView("Template/PreferencesView");
        } else if (source.equals(viewsB)) {
            loadView("Template/ViewsView");
        } else if (source.equals(newOffersB)) {
            //loadView("NewOffersView");
        }
    }

    public void loadView(String menu) {

        try {
            Parent view = FXMLLoader.load(getClass().getResource("/com/smakab/datingapp/zustrichalnya/Views/Search/Content/"+menu+".fxml"));

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
