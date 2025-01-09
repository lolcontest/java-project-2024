package com.gamemanager.util;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Gestionnaire centralisé des logs de l'application
 */
public class LogManager {
    private static final String LOG_FOLDER = "logs";
    private static LogManager instance;
    private FileHandler fileHandler;
    
    private LogManager() {
        try {
            // Création du dossier de logs si nécessaire
            Path logPath = Paths.get(LOG_FOLDER);
            if (!Files.exists(logPath)) {
                Files.createDirectories(logPath);
            }
            
            // Configuration du FileHandler
            fileHandler = new FileHandler(LOG_FOLDER + "/gamemanager_%g.log", 1024 * 1024, 5, true);
            fileHandler.setFormatter(new java.util.logging.SimpleFormatter());
            
            // Configuration du logger root
            Logger rootLogger = Logger.getLogger("");
            rootLogger.addHandler(fileHandler);
            
        } catch (IOException e) {
            System.err.println("Erreur lors de l'initialisation des logs: " + e.getMessage());
        }
    }
    
    public static LogManager getInstance() {
        if (instance == null) {
            instance = new LogManager();
        }
        return instance;
    }
    
    /**
     * Configure un logger pour une classe spécifique
     */
    public Logger getLogger(Class<?> clazz) {
        Logger logger = Logger.getLogger(clazz.getName());
        logger.setLevel(Level.FINE);
        if (fileHandler != null) {
            logger.addHandler(fileHandler);
        }
        return logger;
    }
    
    /**
     * Ferme proprement les handlers de log
     */
    public void cleanup() {
        if (fileHandler != null) {
            fileHandler.close();
        }
    }
    
    /**
     * Log une exception avec stack trace complète
     */
    public void logException(String context, Throwable e) {
        Logger logger = getLogger(LogManager.class);
        logger.log(Level.SEVERE, String.format("%s: %s", context, e.getMessage()), e);
    }
    
    /**
     * Log une action utilisateur
     */
    public void logUserAction(String action, String details) {
        Logger logger = getLogger(LogManager.class);
        logger.log(Level.INFO, String.format("User Action: %s - %s", action, details));
    }
}