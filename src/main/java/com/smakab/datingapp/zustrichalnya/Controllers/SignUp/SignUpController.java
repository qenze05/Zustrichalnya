package com.smakab.datingapp.zustrichalnya.Controllers.SignUp;

import com.smakab.datingapp.zustrichalnya.Controllers.BaseController;
import com.smakab.datingapp.zustrichalnya.Database.DatabaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpController implements Initializable {

    public TextField emailField;
    public TextField loginField;
    public PasswordField passwordField;
    public Label errorLabel;
    public UUID uuid;

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

    public void registrationAction(ActionEvent actionEvent) {
        String errorMessage = verifyLogin(emailField.textProperty().get(), passwordField.textProperty().get(), loginField.textProperty().get());
        if(errorMessage.isEmpty()) {
            try {
                uuid = UUID.randomUUID();
                saveToDB();

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
                controller.setProfileUUID(uuid);
                // Hide this current window (if this is what you want)
                ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            errorLabel.textProperty().set(errorMessage);
        }
    }

    public void saveToDB() {
        Connection connection = new DatabaseConnection().getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO login (uuid, email, password, login) VALUES (?, ?, ?, ?)");
            statement.setString(1, uuid.toString());
            statement.setString(2, emailField.textProperty().get());
            statement.setString(3, hashPassword(passwordField.textProperty().get()));
            statement.setString(4, loginField.textProperty().get());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String verifyLogin(String email, String password, String name) {

        String EMAIL_REGEX = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\\\.[A-Za-z0-9-]+)*(\\\\.[A-Za-z]{2,})$";
        Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = EMAIL_PATTERN.matcher(email);
        boolean emailV = !matcher.matches();

        boolean passV = !password.isEmpty();

        boolean nameV = !name.isEmpty();

        if(!emailV) return "Некоректна пошта";
        else if (!passV) return "Пустий пароль";
        else if (!nameV) return "Пустий логін";

        if(!checkValue("email", email)) return "Введена пошта вже існує";
        if(!checkValue("login", name)) return "Введений логін вже існує";

        return "";
    }

    public boolean checkValue(String dbItem, String value) {
        try{
            Connection connection = new DatabaseConnection().getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM login WHERE "+dbItem+" = '"+value+"'");

            ResultSet items = statement.executeQuery();

            if(items != null && items.next()) return false;
            statement.close();

            return true;
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static String hashPassword(String password){
        try {
            MessageDigest md = MessageDigest.getInstance("SHA");
            md.update(password.getBytes());
            byte [] arr = md.digest();
            StringBuilder sb = new StringBuilder();

            for(byte b : arr){
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        }catch (Exception error){
            error.printStackTrace();
            error.getCause();
        }
        return null;
    }
}
