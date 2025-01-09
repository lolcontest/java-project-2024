package com.gamemanager.ui.components;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.input.MouseEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * Composant personnalisé pour gérer les zones cliquables
 * Permet de définir des zones rectangulaires et de détecter les clics
 */
public class ClickableZonePane extends Pane {
    private final List<ClickableZone> zones = new ArrayList<>();
    private boolean showDebugZones = false;
    
    public ClickableZonePane() {
        // Gestion des clics sur le panneau
        setOnMouseClicked(this::handleClick);
        
        // Style par défaut
        setStyle("-fx-background-color: transparent;");
    }
    
    /**
     * Ajoute une nouvelle zone cliquable
     * @param name Nom identifiant la zone
     * @param x Position X
     * @param y Position Y
     * @param width Largeur
     * @param height Hauteur
     * @param action Action à exécuter lors du clic
     */
    public void addZone(String name, double x, double y, double width, double height, Runnable action) {
        ClickableZone zone = new ClickableZone(name, x, y, width, height, action);
        zones.add(zone);
        
        if (showDebugZones) {
            // Affiche un rectangle de debug
            Rectangle debug = new Rectangle(x, y, width, height);
            debug.setFill(Color.TRANSPARENT);
            debug.setStroke(Color.RED);
            debug.setStrokeWidth(1);
            getChildren().add(debug);
        }
    }
    
    /**
     * Active/désactive l'affichage des zones pour le debug
     */
    public void setDebugMode(boolean debug) {
        this.showDebugZones = debug;
        getChildren().clear();
        if (debug) {
            for (ClickableZone zone : zones) {
                Rectangle debugRect = new Rectangle(zone.x, zone.y, zone.width, zone.height);
                debugRect.setFill(Color.TRANSPARENT);
                debugRect.setStroke(Color.RED);
                debugRect.setStrokeWidth(1);
                getChildren().add(debugRect);
            }
        }
    }
    
    private void handleClick(MouseEvent event) {
        double x = event.getX();
        double y = event.getY();
        
        for (ClickableZone zone : zones) {
            if (zone.contains(x, y)) {
                zone.action.run();
                break;
            }
        }
    }
    
    /**
     * Représente une zone cliquable avec ses coordonnées et son action
     */
    private static class ClickableZone {
        final String name;
        final double x;
        final double y;
        final double width;
        final double height;
        final Runnable action;
        
        ClickableZone(String name, double x, double y, double width, double height, Runnable action) {
            this.name = name;
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            this.action = action;
        }
        
        boolean contains(double px, double py) {
            return px >= x && px <= x + width && py >= y && py <= y + height;
        }
    }
}