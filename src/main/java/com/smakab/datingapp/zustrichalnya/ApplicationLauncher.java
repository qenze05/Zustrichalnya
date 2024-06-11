package com.smakab.datingapp.zustrichalnya;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class ApplicationLauncher extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ApplicationLauncher.class.getResource(
                "/com/smakab/datingapp/zustrichalnya/Views/SignIn/SignInView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Зустрічальня");
        stage.setScene(scene);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/icons/logo_bordered.png")));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}