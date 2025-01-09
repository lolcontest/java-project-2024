package com.gamemanager.service;

import com.gamemanager.model.Item;
import com.gamemanager.model.Stat;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class ItemServiceTest {
    private ItemService itemService;
    
    @BeforeEach
    void setUp() {
        itemService = new ItemService();
    }
    
    @Test
    void testAddItem() {
        // Arrange
        Item item = new Item();
        item.setId(1);
        item.setName("Test Item");
        
        // Act
        itemService.addOrUpdateItem(item);
        
        // Assert
        var found = itemService.findItemById(1);
        assertTrue(found.isPresent());
        assertEquals("Test Item", found.get().getName());
    }
    
    @Test
    void testUpdateItem() {
        // Arrange
        Item item = new Item();
        item.setId(1);
        item.setName("Original Name");
        itemService.addOrUpdateItem(item);
        
        // Act
        item.setName("Updated Name");
        itemService.addOrUpdateItem(item);
        
        // Assert
        var found = itemService.findItemById(1);
        assertTrue(found.isPresent());
        assertEquals("Updated Name", found.get().getName());
    }
    
    @Test
    void testDeleteItem() {
        // Arrange
        Item item = new Item();
        item.setId(1);
        itemService.addOrUpdateItem(item);
        
        // Act
        itemService.deleteItem(1);
        
        // Assert
        var found = itemService.findItemById(1);
        assertFalse(found.isPresent());
    }
    
    @Test
    void testSearchItems() {
        // Arrange
        Item item1 = new Item();
        item1.setId(1);
        item1.setName("Sword");
        
        Item item2 = new Item();
        item2.setId(2);
        item2.setName("Shield");
        
        itemService.addOrUpdateItem(item1);
        itemService.addOrUpdateItem(item2);
        
        // Act & Assert
        List<Item> results = itemService.searchItems("Sword");
        assertEquals(1, results.size());
        assertEquals("Sword", results.get(0).getName());
    }
    
    @Test
    void testSearchByPartialName() {
        // Arrange
        Item item1 = new Item();
        item1.setId(1);
        item1.setName("Iron Sword");
        
        Item item2 = new Item();
        item2.setId(2);
        item2.setName("Steel Sword");
        
        itemService.addOrUpdateItem(item1);
        itemService.addOrUpdateItem(item2);
        
        // Act
        List<Item> results = itemService.searchItems("Sword");
        
        // Assert
        assertEquals(2, results.size());
    }
}