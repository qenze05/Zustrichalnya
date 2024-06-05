package com.smakab.datingapp.zustrichalnya;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ApplicationLauncher extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ApplicationLauncher.class.getResource(
                "/com/smakab/datingapp/zustrichalnya/Views/MainView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1400, 750);
        stage.setTitle("Зустрічальня");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}