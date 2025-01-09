package com.gamemanager.ui;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import java.util.prefs.Preferences;

/**
 * Gestionnaire de thèmes de l'application
 * Permet de basculer entre le thème clair et sombre
 */
public class ThemeManager {
    private static final String LIGHT_THEME = "/styles/light-theme.css";
    private static final String DARK_THEME = "/styles/dark-theme.css";
    private static final String PREF_DARK_MODE = "darkMode";
    
    private Scene scene;
    private boolean isDarkTheme;
    private final Preferences prefs;
    
    public ThemeManager(Scene scene) {
        this.scene = scene;
        this.prefs = Preferences.userNodeForPackage(ThemeManager.class);
        this.isDarkTheme = prefs.getBoolean(PREF_DARK_MODE, false);
        applyTheme();
    }
    
    /**
     * Basculer entre les thèmes
     */
    public void toggleTheme() {
        isDarkTheme = !isDarkTheme;
        prefs.putBoolean(PREF_DARK_MODE, isDarkTheme);
        applyTheme();
    }
    
    /**
     * Appliquer le thème actuel
     */
    public void applyTheme() {
        scene.getStylesheets().clear();
        String themePath = isDarkTheme ? DARK_THEME : LIGHT_THEME;
        String themeUrl = getClass().getResource(themePath).toExternalForm();
        scene.getStylesheets().add(themeUrl);
    }
    
    /**
     * Retourne si le thème sombre est actif
     */
    public boolean isDarkTheme() {
        return isDarkTheme;
    }
    
    /**
     * Appliquer le thème aux alertes
     */
    public void applyThemeToDialog(Alert alert) {
        Scene dialogScene = alert.getDialogPane().getScene();
        dialogScene.getStylesheets().clear();
        String themePath = isDarkTheme ? DARK_THEME : LIGHT_THEME;
        String themeUrl = getClass().getResource(themePath).toExternalForm();
        dialogScene.getStylesheets().add(themeUrl);
    }
    
    /**
     * Définir explicitement un thème
     */
    public void setDarkTheme(boolean darkTheme) {
        if (this.isDarkTheme != darkTheme) {
            this.isDarkTheme = darkTheme;
            prefs.putBoolean(PREF_DARK_MODE, darkTheme);
            applyTheme();
        }
    }
}