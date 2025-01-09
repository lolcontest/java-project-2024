package com.gamemanager.persistence;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.gamemanager.model.Item;
import com.gamemanager.config.Configuration;

import java.nio.file.*;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Logger;
import java.util.logging.Level;

/**
 * Gestionnaire central de la persistance des données
 * Utilise Jackson pour JSON et JAXB pour XML
 */
public class DataManager {
    private static final Logger LOGGER = Logger.getLogger(DataManager.class.getName());
    private static final String DATA_DIR = "data";
    private static final String ITEMS_FILE = "items.json";
    
    private final ObjectMapper mapper;
    private final Path dataDirectory;
    
    public DataManager() {
        mapper = new ObjectMapper()
            .enable(SerializationFeature.INDENT_OUTPUT)
            .enable(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS);
        
        dataDirectory = Paths.get(DATA_DIR);
        initializeDataDirectory();
    }
    
    private void initializeDataDirectory() {
        try {
            Files.createDirectories(dataDirectory);
            LOGGER.info("Répertoire de données initialisé: " + dataDirectory);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Impossible de créer le répertoire de données", e);
        }
    }
    
    public void saveItems(List<Item> items) {
        try {
            Path filePath = dataDirectory.resolve(ITEMS_FILE);
            mapper.writeValue(filePath.toFile(), items);
            LOGGER.info("Items sauvegardés dans: " + filePath);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Erreur lors de la sauvegarde des items", e);
            throw new RuntimeException("Erreur de sauvegarde: " + e.getMessage(), e);
        }
    }
    
    public List<Item> loadItems() {
        try {
            Path filePath = dataDirectory.resolve(ITEMS_FILE);
            if (Files.exists(filePath)) {
                return mapper.readValue(filePath.toFile(),
                    mapper.getTypeFactory().constructCollectionType(List.class, Item.class));
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Erreur lors du chargement des items", e);
        }
        return new ArrayList<>();
    }
    
    /**
     * Sauvegarde un backup des données
     */
    public void createBackup() {
        try {
            Path itemsPath = dataDirectory.resolve(ITEMS_FILE);
            if (Files.exists(itemsPath)) {
                Path backupPath = dataDirectory.resolve(ITEMS_FILE + ".backup");
                Files.copy(itemsPath, backupPath, StandardCopyOption.REPLACE_EXISTING);
                LOGGER.info("Backup créé: " + backupPath);
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Erreur lors de la création du backup", e);
        }
    }
    
    /**
     * Restaure les données depuis un backup
     */
    public void restoreFromBackup() {
        try {
            Path backupPath = dataDirectory.resolve(ITEMS_FILE + ".backup");
            if (Files.exists(backupPath)) {
                Path itemsPath = dataDirectory.resolve(ITEMS_FILE);
                Files.copy(backupPath, itemsPath, StandardCopyOption.REPLACE_EXISTING);
                LOGGER.info("Données restaurées depuis: " + backupPath);
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Erreur lors de la restauration du backup", e);
        }
    }
}