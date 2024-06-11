package com.smakab.datingapp.zustrichalnya.Controllers.SignUp;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void openLoginView(ActionEvent event) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("/com/smakab/datingapp/zustrichalnya/Views/SignIn/LoginView.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Увійти");
            stage.setScene(new Scene(root, 600, 400));
            stage.getIcons().add(new Image(getClass().getResourceAsStream("/icons/logo_bordered.png")));
            stage.show();
            // Hide this current window (if this is what you want)
            ((Node)(event.getSource())).getScene().getWindow().hide();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void openRegistrationView(ActionEvent event) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("/com/smakab/datingapp/zustrichalnya/Views/SignIn/RegistrationView.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Зареєструватися");
            stage.setScene(new Scene(root, 600, 400));
            stage.getIcons().add(new Image(getClass().getResourceAsStream("/icons/logo_bordered.png")));
            stage.show();
            // Hide this current window (if this is what you want)
            ((Node)(event.getSource())).getScene().getWindow().hide();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
