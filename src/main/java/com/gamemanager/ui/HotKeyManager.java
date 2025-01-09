package com.gamemanager.ui;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import com.gamemanager.controller.MainController;

/**
 * Gestionnaire des raccourcis clavier globaux
 */
public class HotKeyManager {
    private final Scene scene;
    private final MainController controller;
    
    public HotKeyManager(Scene scene, MainController controller) {
        this.scene = scene;
        this.controller = controller;
        setupHotKeys();
    }
    
    private void setupHotKeys() {
        // Sauvegarde (Ctrl+S)
        scene.getAccelerators().put(
            new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN),
            () -> controller.handleSave()
        );
        
        // Nouvel item (Ctrl+N)
        scene.getAccelerators().put(
            new KeyCodeCombination(KeyCode.N, KeyCombination.CONTROL_DOWN),
            () -> controller.handleNewItem()
        );
        
        // Recherche (Ctrl+F)
        scene.getAccelerators().put(
            new KeyCodeCombination(KeyCode.F, KeyCombination.CONTROL_DOWN),
            () -> controller.focusSearch()
        );
        
        // Suppression (Delete)
        scene.getAccelerators().put(
            new KeyCodeCombination(KeyCode.DELETE),
            () -> controller.handleDelete()
        );
        
        // Export (Ctrl+E)
        scene.getAccelerators().put(
            new KeyCodeCombination(KeyCode.E, KeyCombination.CONTROL_DOWN),
            () -> controller.handleExport()
        );
        
        // Import (Ctrl+I)
        scene.getAccelerators().put(
            new KeyCodeCombination(KeyCode.I, KeyCombination.CONTROL_DOWN),
            () -> controller.handleImport()
        );
        
        // Basculer le thème (Ctrl+T)
        scene.getAccelerators().put(
            new KeyCodeCombination(KeyCode.T, KeyCombination.CONTROL_DOWN),
            () -> controller.toggleTheme()
        );
        
        // Aide (F1)
        scene.getAccelerators().put(
            new KeyCodeCombination(KeyCode.F1),
            () -> controller.showHelp()
        );
    }
}