package com.gamemanager.model;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class StatTest {
    
    @Test
    void testStatCreation() {
        Stat stat = new Stat("7d", 1000, 1000);
        assertEquals("7d", stat.getStatId());
        assertEquals(1000, stat.getMinValue());
        assertEquals(1000, stat.getMaxValue());
    }
    
    @Test
    void testParseValidStat() {
        String statString = "7d#3E8#3E8";
        Stat stat = Stat.fromString(statString);
        
        assertEquals("7d", stat.getStatId());
        assertEquals(1000, stat.getMinValue()); // 3E8 hex = 1000 dec
        assertEquals(1000, stat.getMaxValue());
    }
    
    @Test
    void testInvalidStatFormat() {
        String invalidStat = "7d#3E8"; // Missing one value
        assertThrows(IllegalArgumentException.class, () -> {
            Stat.fromString(invalidStat);
        });
    }
    
    @Test
    void testInvalidHexValues() {
        String invalidHex = "7d#XYZ#3E8"; // Invalid hex value
        assertThrows(NumberFormatException.class, () -> {
            Stat.fromString(invalidHex);
        });
    }
    
    @Test
    void testToStatsTemplate() {
        Stat stat = new Stat("7d", 1000, 2000);
        String template = stat.toStatsTemplate();
        assertEquals("7d#3E8#7D0", template); // 1000 = 3E8, 2000 = 7D0 in hex
    }
    
    @Test
    void testStatDescription() {
        Stat stat = new Stat("7d", 100, 100);
        assertEquals("Dommages", StatDescriptions.getInstance().getDescription("7d"));
    }
    
    @Test
    void testPropertyBindings() {
        Stat stat = new Stat("7d", 100, 200);
        
        // Test property bindings
        boolean[] propertyChanged = {false};
        stat.minValueProperty().addListener((obs, old, newVal) -> {
            propertyChanged[0] = true;
        });
        
        stat.setMinValue(150);
        assertTrue(propertyChanged[0]);
        assertEquals(150, stat.getMinValue());
    }
    
    @Test
    void testStatValidation() {
        Stat stat = new Stat("7d", 200, 100); // min > max
        assertThrows(IllegalArgumentException.class, () -> {
            stat.validate();
        });
    }
}