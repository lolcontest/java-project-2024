package com.gamemanager.service;

import com.gamemanager.model.Item;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.nio.file.Files;
import java.util.List;
import java.util.zip.*;
import java.util.logging.Logger;
import java.util.logging.Level;

/**
 * Service pour l'export et l'import des données
 */
public class ExportImportService {
    private static final Logger LOGGER = Logger.getLogger(ExportImportService.class.getName());
    private final ObjectMapper mapper = new ObjectMapper();
    
    /**
     * Exporte les items vers un fichier ZIP
     */
    public void exportData(List<Item> items, Stage stage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Exporter les données");
        fileChooser.getExtensionFilters().add(
            new FileChooser.ExtensionFilter("Fichier ZIP", "*.zip")
        );
        
        File file = fileChooser.showSaveDialog(stage);
        if (file != null) {
            try (ZipOutputStream zos = new ZipOutputStream(Files.newOutputStream(file.toPath()))) {
                // Export des items
                ZipEntry itemsEntry = new ZipEntry("items.json");
                zos.putNextEntry(itemsEntry);
                mapper.writeValue(zos, items);
                zos.closeEntry();
                
                NotificationService.getInstance().showSuccess(
                    "Données exportées avec succès vers " + file.getName()
                );
                
            } catch (Exception e) {
                ErrorHandler.handleError(e, "Export des données");
            }
        }
    }
    
    /**
     * Importe les items depuis un fichier ZIP
     */
    public List<Item> importData(Stage stage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Importer les données");
        fileChooser.getExtensionFilters().add(
            new FileChooser.ExtensionFilter("Fichier ZIP", "*.zip")
        );
        
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            try (ZipInputStream zis = new ZipInputStream(Files.newInputStream(file.toPath()))) {
                ZipEntry entry;
                while ((entry = zis.getNextEntry()) != null) {
                    if (entry.getName().equals("items.json")) {
                        List<Item> items = mapper.readValue(zis,
                            mapper.getTypeFactory().constructCollectionType(List.class, Item.class));
                        
                        NotificationService.getInstance().showSuccess(
                            "Données importées avec succès depuis " + file.getName()
                        );
                        
                        return items;
                    }
                }
            } catch (Exception e) {
                ErrorHandler.handleError(e, "Import des données");
            }
        }
        return null;
    }
    
    /**
     * Exporte un rapport CSV des items
     */
    public void exportToCsv(List<Item> items, Stage stage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Exporter en CSV");
        fileChooser.getExtensionFilters().add(
            new FileChooser.ExtensionFilter("Fichier CSV", "*.csv")
        );
        
        File file = fileChooser.showSaveDialog(stage);
        if (file != null) {
            try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
                // En-têtes CSV
                writer.println("ID,Nom,Type,Niveau,Pod,Panoplie,Stats");
                
                // Données
                for (Item item : items) {
                    writer.printf("%d,\"%s\",%d,%d,%d,%d,\"%s\"%n",
                        item.getId(),
                        escapeCsv(item.getName()),
                        item.getType(),
                        item.getLevel(),
                        item.getPod(),
                        item.getPanoplie(),
                        item.getStatsTemplate()
                    );
                }
                
                NotificationService.getInstance().showSuccess(
                    "Données exportées en CSV avec succès vers " + file.getName()
                );
                
            } catch (Exception e) {
                ErrorHandler.handleError(e, "Export CSV");
            }
        }
    }
    
    private String escapeCsv(String value) {
        if (value == null) return "";
        return value.replace("\"", "\"\"");
    }
}