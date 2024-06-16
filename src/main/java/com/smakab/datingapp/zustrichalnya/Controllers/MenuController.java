package com.smakab.datingapp.zustrichalnya.Controllers;

import com.smakab.datingapp.zustrichalnya.ApplicationLauncher;
import com.smakab.datingapp.zustrichalnya.Controllers.Search.SearchController;
import com.smakab.datingapp.zustrichalnya.Controllers.Settings.SettingsController;
import com.smakab.datingapp.zustrichalnya.Interfaces.ProfileDataDelegate;
import com.smakab.datingapp.zustrichalnya.Models.Person;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.UUID;

public class MenuController extends BaseController implements Initializable{
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
        }else if (source.equals(chatB)) {
            openMenu("Chat/ChatView");
        } else if (source.equals(settingsB)) {
            openMenu("Settings/SettingsView");
        } else if (source.equals(logoutB)) {
            backToLogin();
        }
    }

    private void openMenu(String menu) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/smakab/datingapp/zustrichalnya/Views/"+menu+".fxml"));
            SplitPane view = loader.load();

            AnchorPane.setTopAnchor(view, 0.0);
            AnchorPane.setRightAnchor(view, 0.0);
            AnchorPane.setBottomAnchor(view, 0.0);
            AnchorPane.setLeftAnchor(view, 0.0);

            configureMenu(loader.getController());

            container.getChildren().setAll(view);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void backToLogin() {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("/com/smakab/datingapp/zustrichalnya/Views/SignIn/SignInView.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Зустрічальня");
            stage.setScene(new Scene(root, 600, 400));
            stage.getIcons().add(new Image(getClass().getResourceAsStream("/icons/logo_bordered.png")));
            stage.show();
            // Hide this current window (if this is what you want)
            container.getScene().getWindow().hide();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void configureMenu(BaseController controller) {
        controller.setProfileUUID(uuid);
        try{
            SearchController cont = (SearchController) controller;
            cont.loadMainView();
        }catch (ClassCastException e) {
        }

        try{
            SettingsController cont = (SettingsController) controller;
            cont.loadSettings();
        }catch (ClassCastException e) {
            System.out.println("error");
        }
    }

    @Override
    public void setProfileUUID(UUID uuid) {
        this.uuid = uuid;
        System.out.println(uuid);
    }
}
