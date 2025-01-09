package com.gamemanager.ui;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;

/**
 * Gestionnaire des raccourcis clavier de l'application
 */
public class KeyBindings {

    public static void setupGlobalShortcuts(Scene scene, MainController controller) {
        // Sauvegarde rapide (Ctrl+S)
        scene.getAccelerators().put(
            new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN),
            controller::handleQuickSave
        );
        
        // Nouveau item (Ctrl+N)
        scene.getAccelerators().put(
            new KeyCodeCombination(KeyCode.N, KeyCombination.CONTROL_DOWN),
            controller::handleNewItem
        );
        
        // Recherche rapide (Ctrl+F)
        scene.getAccelerators().put(
            new KeyCodeCombination(KeyCode.F, KeyCombination.CONTROL_DOWN),
            controller::focusSearch
        );
        
        // Export (Ctrl+E)
        scene.getAccelerators().put(
            new KeyCodeCombination(KeyCode.E, KeyCombination.CONTROL_DOWN),
            controller::handleExport
        );
        
        // Import (Ctrl+I)
        scene.getAccelerators().put(
            new KeyCodeCombination(KeyCode.I, KeyCombination.CONTROL_DOWN),
            controller::handleImport
        );
        
        // Suppression (Delete)
        scene.getAccelerators().put(
            new KeyCodeCombination(KeyCode.DELETE),
            controller::handleDelete
        );
    }
}