package com.gamemanager.util;

import com.gamemanager.model.Item;
import com.gamemanager.model.Stat;

import java.util.ArrayList;
import java.util.List;

/**
 * Utilitaire de validation des données
 */
public class ValidationUtils {
    
    /**
     * Valide un item complet
     * @return Liste des erreurs trouvées
     */
    public static List<String> validateItem(Item item) {
        List<String> errors = new ArrayList<>();
        
        // Validation de l'ID
        if (item.getId() <= 0) {
            errors.add("L'ID doit être positif");
        }
        
        // Validation du nom
        if (item.getName() == null || item.getName().trim().isEmpty()) {
            errors.add("Le nom est requis");
        }
        
        // Validation du niveau
        if (item.getLevel() < 0 || item.getLevel() > 200) {
            errors.add("Le niveau doit être entre 0 et 200");
        }
        
        // Validation du pod
        if (item.getPod() < 0) {
            errors.add("Le pod ne peut pas être négatif");
        }
        
        // Validation des stats
        validateStats(item.getStats(), errors);
        
        return errors;
    }
    
    /**
     * Valide une liste de stats
     */
    public static void validateStats(List<Stat> stats, List<String> errors) {
        if (stats == null) return;
        
        for (Stat stat : stats) {
            // Vérification du statId
            if (stat.getStatId() == null || stat.getStatId().trim().isEmpty()) {
                errors.add("L'ID de stat est requis");
                continue;
            }
            
            // Vérification des valeurs min/max
            if (stat.getMinValue() > stat.getMaxValue()) {
                errors.add(String.format(
                    "La valeur minimale (%d) ne peut pas être supérieure à la valeur maximale (%d) pour la stat %s",
                    stat.getMinValue(), stat.getMaxValue(), stat.getStatId()
                ));
            }
            
            // Vérification des limites
            if (stat.getMinValue() < 0 || stat.getMaxValue() < 0) {
                errors.add("Les valeurs de stat ne peuvent pas être négatives");
            }
        }
    }
    
    /**
     * Valide le format d'une chaîne de stats
     */
    public static boolean isValidStatsTemplate(String template) {
        if (template == null || template.trim().isEmpty()) return true;
        
        String[] stats = template.split(",");
        for (String stat : stats) {
            String[] parts = stat.split("#");
            if (parts.length != 3) return false;
            
            // Vérification du format hexadécimal
            try {
                Integer.parseInt(parts[1], 16);
                Integer.parseInt(parts[2], 16);
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Valide le format d'un ID hexadécimal
     */
    public static boolean isValidHexValue(String hex) {
        try {
            Integer.parseInt(hex, 16);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}