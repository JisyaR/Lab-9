package org.example.labo08;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class CafeteriaApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(CafeteriaApplication.class.getResource("cafeteria.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 800);
        stage.setScene(scene);
        stage.setTitle("Cafetería UCA");
        stage.show();
    }

    public static void main(String[] args) {
        //System.out.println("JavaFX versión: " +  System.getProperty("javafx.version"));
        launch();

    }
}