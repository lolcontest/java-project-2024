package com.gamemanager.ui.actions;

import javafx.scene.control.MenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import com.gamemanager.controller.MainController;

/**
 * Gestionnaire des actions disponibles dans l'application
 * Crée le menu et gère les raccourcis
 */
public class ActionManager {

    public static MenuBar createMenuBar(MainController controller) {
        MenuBar menuBar = new MenuBar();
        
        // Menu Fichier
        Menu fileMenu = new Menu("Fichier");
        fileMenu.getItems().addAll(
            createMenuItem("Nouveau", "Ctrl+N", controller::handleNewItem),
            createMenuItem("Sauvegarder", "Ctrl+S", controller::handleQuickSave),
            createMenuItem("Exporter...", "Ctrl+E", controller::handleExport),
            createMenuItem("Importer...", "Ctrl+I", controller::handleImport),
            createMenuItem("Quitter", "Alt+F4", controller::handleExit)
        );
        
        // Menu Édition
        Menu editMenu = new Menu("Édition");
        editMenu.getItems().addAll(
            createMenuItem("Rechercher", "Ctrl+F", controller::focusSearch),
            createMenuItem("Supprimer", "Delete", controller::handleDelete)
        );
        
        // Menu Outils
        Menu toolsMenu = new Menu("Outils");
        toolsMenu.getItems().addAll(
            createMenuItem("Exporter en CSV", null, controller::handleCsvExport),
            createMenuItem("Créer une sauvegarde", null, controller::handleBackup),
            createMenuItem("Restaurer une sauvegarde", null, controller::handleRestore)
        );
        
        menuBar.getMenus().addAll(fileMenu, editMenu, toolsMenu);
        return menuBar;
    }
    
    private static MenuItem createMenuItem(String text, String accelerator, Runnable action) {
        MenuItem item = new MenuItem(text);
        if (accelerator != null) {
            item.setAccelerator(KeyCombination.valueOf(accelerator));
        }
        item.setOnAction(event -> action.run());
        return item;
    }
}