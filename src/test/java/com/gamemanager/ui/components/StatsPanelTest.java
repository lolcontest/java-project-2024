package com.gamemanager.ui.components;

import com.gamemanager.model.Item;
import com.gamemanager.model.Stat;
import javafx.scene.Node;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StatsPanelTest extends ApplicationTest {
    private StatsPanel statsPanel;
    
    @Override
    public void start(Stage stage) {
        statsPanel = new StatsPanel();
        stage.setScene(new Scene(statsPanel));
        stage.show();
    }
    
    @Test
    void shouldStartEmpty() {
        List<Stat> stats = statsPanel.getStats();
        assertTrue(stats.isEmpty());
    }
    
    @Test
    void shouldLoadItemStats() {
        // Création d'un item de test
        Item item = new Item();
        item.setId(1);
        item.setName("Test Item");
        item.getStats().add(new Stat("7d", 1000, 1000));
        
        // Chargement de l'item
        statsPanel.setItem(item);
        
        // Vérification
        List<Stat> stats = statsPanel.getStats();
        assertEquals(1, stats.size());
        assertEquals("7d", stats.get(0).getStatId());
        assertEquals(1000, stats.get(0).getMinValue());
    }
}