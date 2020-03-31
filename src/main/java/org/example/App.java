package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {
    @Override
    public void start(Stage stage) {
        FXMLLoader loader;
        Parent root;
        try {
            loader = new FXMLLoader(getClass().getResource("primary.fxml"));
            root = loader.load();
            MainController mainController = loader.getController();
            mainController.setStageListener(stage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.setTitle("The Game");
        stage.setScene(new Scene(root, 800, 700));
        stage.setMaximized(true);
        stage.setMinHeight(960);
        stage.setMinWidth(1180);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}