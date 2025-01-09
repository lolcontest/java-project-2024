package com.gamemanager.config;

import java.util.prefs.Preferences;

/**
 * Gestionnaire des préférences utilisateur
 */
public class UserPreferences {
    private static final String DARK_MODE = "darkMode";
    private static final String WINDOW_WIDTH = "windowWidth";
    private static final String WINDOW_HEIGHT = "windowHeight";
    private static final String WINDOW_X = "windowX";
    private static final String WINDOW_Y = "windowY";
    private static final String LAST_DIRECTORY = "lastDirectory";
    
    private final Preferences prefs;
    
    public UserPreferences() {
        prefs = Preferences.userNodeForPackage(UserPreferences.class);
    }
    
    // Thème
    public boolean isDarkMode() {
        return prefs.getBoolean(DARK_MODE, false);
    }
    
    public void setDarkMode(boolean darkMode) {
        prefs.putBoolean(DARK_MODE, darkMode);
    }
    
    // Taille de la fenêtre
    public double getWindowWidth() {
        return prefs.getDouble(WINDOW_WIDTH, 1200);
    }
    
    public void setWindowWidth(double width) {
        prefs.putDouble(WINDOW_WIDTH, width);
    }
    
    public double getWindowHeight() {
        return prefs.getDouble(WINDOW_HEIGHT, 800);
    }
    
    public void setWindowHeight(double height) {
        prefs.putDouble(WINDOW_HEIGHT, height);
    }
    
    // Position de la fenêtre
    public double getWindowX() {
        return prefs.getDouble(WINDOW_X, 100);
    }
    
    public void setWindowX(double x) {
        prefs.putDouble(WINDOW_X, x);
    }
    
    public double getWindowY() {
        return prefs.getDouble(WINDOW_Y, 100);
    }
    
    public void setWindowY(double y) {
        prefs.putDouble(WINDOW_Y, y);
    }
    
    // Dernier répertoire utilisé
    public String getLastDirectory() {
        return prefs.get(LAST_DIRECTORY, System.getProperty("user.home"));
    }
    
    public void setLastDirectory(String path) {
        prefs.put(LAST_DIRECTORY, path);
    }
    
    /**
     * Sauvegarder toutes les préférences de fenêtre
     */
    public void saveWindowState(double x, double y, double width, double height) {
        setWindowX(x);
        setWindowY(y);
        setWindowWidth(width);
        setWindowHeight(height);
    }
}