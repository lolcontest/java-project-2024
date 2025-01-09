package com.gamemanager;

import javafx.application.Application;
import javafx.stage.Stage;

public class GameManagerApp extends Application {
    @Override
    public void start(Stage stage) {
        stage.setTitle("Game Manager");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}