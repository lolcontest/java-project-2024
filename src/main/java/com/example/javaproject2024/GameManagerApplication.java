
package com.example.javaproject2024;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GameManagerApplication extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Créer l'instance de ItemEditorView
        ItemEditorView itemEditorView = new ItemEditorView();

        // Créer la scène et l'ajouter à la fenêtre principale
        Scene scene = new Scene(itemEditorView, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Game Manager");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
