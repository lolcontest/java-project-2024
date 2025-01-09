package com.gamemanager.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import com.gamemanager.model.Item;

public class MainController {
    @FXML
    private TextField searchField;
    
    @FXML
    private TableView<Item> itemTable;
    
    @FXML
    private TextField nameField;
    
    @FXML
    private void handleSearch() {
        // TODO: Implement search
    }
}