package com.smakab.datingapp.zustrichalnya.Controllers.SignUp;

import com.smakab.datingapp.zustrichalnya.Controllers.BaseController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.UUID;

public class SignInController implements Initializable {

    public TextField loginField;
    public PasswordField passwordField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void backToMenu(ActionEvent actionEvent) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("/com/smakab/datingapp/zustrichalnya/Views/SignIn/SignInView.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Зустрічальня");
            stage.setScene(new Scene(root, 600, 400));
            stage.getIcons().add(new Image(getClass().getResourceAsStream("/icons/logo_bordered.png")));
            stage.show();
            // Hide this current window (if this is what you want)
            ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loginAction(ActionEvent actionEvent) {
        boolean loginSuccessful = verifyLogin(loginField.textProperty().get(), passwordField.textProperty().get());
        if(loginSuccessful) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/smakab/datingapp/zustrichalnya/Views/MainView.fxml"));
                Parent root = loader.load();

                Stage stage = new Stage();
                stage.setTitle("Зустрічальня");
                stage.setScene(new Scene(root, 1920, 1000));
                stage.getIcons().add(new Image(getClass().getResourceAsStream("/icons/logo_bordered.png")));
                stage.setMaximized(true);
                stage.show();

                BaseController controller = loader.getController();

                //TODO: замінити фейковий айді на реальний
                controller.setProfileUUID(UUID.randomUUID());
                // Hide this current window (if this is what you want)
                ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            //TODO: вивести повідомлення про помилку
        }
    }

    public boolean verifyLogin(String login, String password) {

        return true;
    }


}
