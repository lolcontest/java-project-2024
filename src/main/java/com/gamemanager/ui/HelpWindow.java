package com.gamemanager.ui;

import javafx.scene.Scene;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.Modality;

/**
 * Fenêtre d'aide de l'application
 */
public class HelpWindow extends Stage {
    
    public HelpWindow(Stage owner) {
        // Configuration de la fenêtre
        initOwner(owner);
        initModality(Modality.APPLICATION_MODAL);
        setTitle("Aide - Game Manager");
        
        // Création du WebView pour afficher l'aide
        WebView webView = new WebView();
        String helpUrl = getClass().getResource("/help/help.html").toExternalForm();
        webView.getEngine().load(helpUrl);
        
        // Configuration de la scène
        Scene scene = new Scene(webView, 800, 600);
        setScene(scene);
    }
}