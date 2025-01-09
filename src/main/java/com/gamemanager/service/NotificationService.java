package com.gamemanager.service;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.application.Platform;

/**
 * Service de gestion des notifications et des dialogues
 * Utilise le pattern Singleton
 */
public class NotificationService {
    private static NotificationService instance;
    
    public static NotificationService getInstance() {
        if (instance == null) {
            instance = new NotificationService();
        }
        return instance;
    }
    
    /**
     * Affiche une notification d'erreur
     */
    public void showError(String title, String message) {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(title);
            alert.setContentText(message);
            alert.showAndWait();
        });
    }
    
    /**
     * Affiche une notification de succès
     */
    public void showSuccess(String message) {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succès");
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.showAndWait();
        });
    }
    
    /**
     * Demande une confirmation à l'utilisateur
     */
    public boolean confirmAction(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(title);
        alert.setContentText(message);
        
        return alert.showAndWait()
            .filter(response -> response == ButtonType.OK)
            .isPresent();
    }
    
    /**
     * Affiche une notification d'avertissement
     */
    public void showWarning(String title, String message) {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Avertissement");
            alert.setHeaderText(title);
            alert.setContentText(message);
            alert.showAndWait();
        });
    }
}