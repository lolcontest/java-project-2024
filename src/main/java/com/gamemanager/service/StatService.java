package com.gamemanager.service;

import com.gamemanager.model.Stat;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.ArrayList;
import java.util.List;

/**
 * Service responsable de la gestion des statistiques
 */
public class StatService {
    private final ObservableList<Stat> stats = FXCollections.observableArrayList();

    /**
     * Parse une chaîne de template de stats et retourne une liste de stats
     * Format attendu : "statId#minValue#maxValue,statId#minValue#maxValue,..."
     */
    public List<Stat> parseStatsTemplate(String template) {
        if (template == null || template.trim().isEmpty()) {
            return new ArrayList<>();
        }

        List<Stat> result = new ArrayList<>();
        String[] statTemplates = template.split(",");
        
        for (String statTemplate : statTemplates) {
            try {
                result.add(Stat.fromTemplate(statTemplate.trim()));
            } catch (Exception e) {
                // Log l'erreur mais continue le parsing
                System.err.println("Erreur de parsing pour: " + statTemplate);