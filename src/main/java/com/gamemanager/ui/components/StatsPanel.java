package com.gamemanager.ui.components;

import com.gamemanager.model.Item;
import com.gamemanager.model.Stat;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.Insets;
import java.util.ArrayList;
import java.util.List;

/**
 * Panneau personnalisé pour afficher et éditer les statistiques d'un item
 */
public class StatsPanel extends VBox {
    private final VBox statsContainer;
    private Item currentItem;
    
    public StatsPanel() {
        // Configuration du layout
        setSpacing(10);
        setPadding(new Insets(10));
        
        // En-tête
        Label title = new Label("Statistiques");
        title.getStyleClass().add("title-label");
        
        // Conteneur pour les lignes de stats
        statsContainer = new VBox(5);
        
        // Bouton d'ajout
        Button addButton = new Button("Ajouter une statistique");
        addButton.setOnAction(e -> addNewStatRow());
        
        // Assemblage
        getChildren().addAll(title, statsContainer, addButton);
    }
    
    /**
     * Met à jour l'affichage avec un nouvel item
     */
    public void setItem(Item item) {
        this.currentItem = item;
        refreshStats();
    }
    
    /**
     * Récupère toutes les statistiques actuellement affichées
     */
    public List<Stat> getStats() {
        List<Stat> stats = new ArrayList<>();
        for (var node : statsContainer.getChildren()) {
            if (node instanceof HBox) {
                Stat stat = StatRowFactory.extractStatFromRow(node);
                if (stat != null) {
                    stats.add(stat);
                }
            }
        }
        return stats;
    }
    
    private void refreshStats() {
        statsContainer.getChildren().clear();
        if (currentItem != null) {
            for (Stat stat : currentItem.getStats()) {
                addStatRow(stat);
            }
        }
    }
    
    private void addNewStatRow() {
        addStatRow(null);
    }
    
    private void addStatRow(Stat stat) {
        Node row = StatRowFactory.createStatRow(stat, () -> {
            // Action de suppression
            statsContainer.getChildren().remove(
                statsContainer.getChildren().size() - 1
            );
        });
        statsContainer.getChildren().add(row);
    }
}