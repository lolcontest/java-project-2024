# Documentation API

## Services Principaux

### ItemService

```java
public class ItemService {
    /**
     * Ajoute un nouvel item
     * @param item Item à ajouter
     * @throws IllegalArgumentException si l'item est invalide
     */
    public void addItem(Item item);

    /**
     * Recherche des items
     * @param query Terme de recherche
     * @return Liste des items correspondants
     */
    public List<Item> searchItems(String query);
}
```

### ExportImportService

```java
public class ExportImportService {
    /**
     * Exporte les données au format ZIP
     * @param items Liste des items à exporter
     * @param file Fichier de destination
     */
    public void exportToZip(List<Item> items, File file);

    /**
     * Importe les données depuis un ZIP
     * @param file Fichier source
     * @return Liste des items importés
     */
    public List<Item> importFromZip(File file);
}
```

## Modèles

### Item

```java
public class Item {
    // Propriétés
    private final IntegerProperty id;
    private final StringProperty name;
    private final ListProperty<Stat> stats;

    // Méthodes principales
    public Item();
    public void addStat(Stat stat);
    public void removeStat(Stat stat);
    public boolean validate();
}
```

### Stat

```java
public class Stat {
    // Propriétés
    private final StringProperty statId;
    private final IntegerProperty minValue;
    private final IntegerProperty maxValue;

    // Méthodes principales
    public Stat(String statId, int minValue, int maxValue);
    public String toStatsTemplate();
    public static Stat fromString(String template);
}
```

## Composants UI

### StatsPanel

```java
public class StatsPanel extends VBox {
    // Constructeurs
    public StatsPanel();

    // Méthodes principales
    public void setItem(Item item);
    public List<Stat> getStats();
    public void addNewStatRow();
}
```

## Utilitaires

### ValidationUtils

```java
public class ValidationUtils {
    public static List<String> validateItem(Item item);
    public static boolean isValidStatsTemplate(String template);
    public static boolean isValidHexValue(String hex);
}
```

### LogManager

```java
public class LogManager {
    public static void logException(String context, Throwable e);
    public static void logUserAction(String action, String details);
}
```