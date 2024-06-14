package com.smakab.datingapp.zustrichalnya.Controllers.Profile;

import com.google.gson.Gson;
import com.smakab.datingapp.zustrichalnya.Controllers.BaseController;
import com.smakab.datingapp.zustrichalnya.Controllers.Profile.Content.ProfileContentClass;
import com.smakab.datingapp.zustrichalnya.Interfaces.ProfileDataDelegate;
import com.smakab.datingapp.zustrichalnya.Models.Person;
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
import java.util.UUID;

public class ProfileController extends BaseController implements Initializable, ProfileDataDelegate {

    @FXML
    public Button mainB, personalityB, hobbiesB, prefB, viewsB;
    public AnchorPane container;
    private Person person;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //fetchProfileData(UUID.randomUUID());
    }

    public void fetchProfileData(UUID uuid) {
        Person person = new Person(uuid);
        this.person = person;
    }
    public void switchMenu(ActionEvent actionEvent) {
        Object source = actionEvent.getSource();

        if(source.equals(mainB)) {
            loadView("ProfileMainView");
        } else if (source.equals(personalityB)) {
            loadView("PersonalityView");
        } else if (source.equals(hobbiesB)) {
            loadView("HobbiesView");
        } else if (source.equals(prefB)) {
            loadView("PreferencesView");
        } else if (source.equals(viewsB)) {
            loadView("ViewsView");
        }
    }

    public void loadView(String menu) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/smakab/datingapp/zustrichalnya/Views/Profile/Content/"+menu+".fxml"));
            Parent view = loader.load();

            AnchorPane.setTopAnchor(view, 0.0);
            AnchorPane.setRightAnchor(view, 0.0);
            AnchorPane.setBottomAnchor(view, 0.0);
            AnchorPane.setLeftAnchor(view, 0.0);

            configureContentView(loader.getController());

            container.getChildren().setAll(view);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void configureContentView(ProfileContentClass controller) {
        controller.setModel(this.person);
        controller.loadModelData();

        controller.setDelegate(this);
    }

    @Override
    public void didUpdateData() {
        System.out.println(new Gson().toJson(person));
    }

    @Override
    public void setProfileUUID(UUID uuid) {
        this.uuid = uuid;
        fetchProfileData(uuid);
    }
}
