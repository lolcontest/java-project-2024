package com.example.javaproject2024;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ItemEditorView extends VBox {
    private TextField idField;
    private TextField nameField;
    private ComboBox<String> typeComboBox;
    private TextField statsField;
    private TextField niveauField;
    private TextField podsField;
    private TextField idPanoplieField;
    private TextField prixField;
    private TextField conditionsField;
    private TextField armesInfosField;
    private TextField gfxIdField;
    private TextArea descriptionArea;
    private Button searchButton;
    private Button addModifyButton;

    public ItemEditorView() {
        setPadding(new Insets(20));
        setSpacing(10);

        // ID, Nom, Type
        GridPane topGrid = new GridPane();
        topGrid.setHgap(10);
        topGrid.setVgap(10);

        topGrid.add(new Label("ID :"), 0, 0);
        idField = new TextField();
        topGrid.add(idField, 1, 0);

        topGrid.add(new Label("Nom :"), 0, 1);
        nameField = new TextField();
        topGrid.add(nameField, 1, 1);

        topGrid.add(new Label("Type :"), 0, 2);
        typeComboBox = new ComboBox<>();
        topGrid.add(typeComboBox, 1, 2);

        // Reste des champs
        GridPane middleGrid = new GridPane();
        middleGrid.setHgap(10);
        middleGrid.setVgap(10);

        middleGrid.add(new Label("Stats :"), 0, 0);
        statsField = new TextField();
        middleGrid.add(statsField, 1, 0);

        middleGrid.add(new Label("Niveau :"), 0, 1);
        niveauField = new TextField();
        middleGrid.add(niveauField, 1, 1);

        // Reste des champs, sauf respect de la ligne du dessus
        // Description
        Label descriptionLabel = new Label("Description :");
        descriptionArea = new TextArea();
        descriptionArea.setPromptText("Saisissez la description ici...");
        descriptionArea.setPrefHeight(100);

        // Boutons
        searchButton = new Button("Rechercher");
        addModifyButton = new Button("Ajouter / Modifier");
        HBox buttonBox = new HBox(searchButton, addModifyButton);
        buttonBox.setSpacing(10);

        // Ajout des éléments à la vue
        getChildren().addAll(topGrid, middleGrid, descriptionLabel, descriptionArea, buttonBox);
    }
}