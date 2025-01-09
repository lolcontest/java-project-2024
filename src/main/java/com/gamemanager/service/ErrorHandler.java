package com.gamemanager.service;

import java.util.logging.Logger;
import java.util.logging.Level;

/**
 * Gestionnaire centralisé des erreurs
 */
public class ErrorHandler {
    private static final Logger LOGGER = Logger.getLogger(ErrorHandler.class.getName());
    private static final NotificationService notificationService = NotificationService.getInstance();
    
    /**
     * Gestion des erreurs de l'application
     */
    public static void handleError(Exception e, String context) {
        LOGGER.log(Level.SEVERE, "Erreur dans " + context, e);
        
        String message = switch (e) {
            case IllegalArgumentException iae -> 
                "Données invalides: " + iae.getMessage();
            case java.io.IOException ioe -> 
                "Erreur d'accès aux fichiers: " + ioe.getMessage();
            case NullPointerException npe -> 
                "Erreur interne: donnée manquante";
            default -> 
                "Une erreur inattendue est survenue: " + e.getMessage();
        };
        
        notificationService.showError("Erreur - " + context, message);
    }
    
    /**
     * Gestion des erreurs critiques nécessitant l'arrêt de l'application
     */
    public static void handleFatalError(Throwable e, String context) {
        LOGGER.log(Level.SEVERE, "Erreur fatale dans " + context, e);
        
        notificationService.showError(
            "Erreur critique",
            "Une erreur critique est survenue dans '" + context + "'. \n" +
            "L'application va être fermée.\n" +
            "Détail: " + e.getMessage()
        );
        
        // Arrêt propre de l'application
        System.exit(1);
    }
    
    /**
     * Vérification de validité avec message personnalisé
     */
    public static void validateCondition(boolean condition, String errorMessage) {
        if (!condition) {
            throw new IllegalArgumentException(errorMessage);
        }
    }
}