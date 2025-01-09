# Exemple d'Import de Données

## Import CSV

```java
public class CsvImporter {
    public List<Item> importFromCsv(File file) {
        List<Item> items = new ArrayList<>();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            // Ignorer l'en-tête
            String header = reader.readLine();
            
            // Lire les données
            String line;
            while ((line = reader.readLine()) != null) {
                Item item = parseItemFromCsv(line);
                if (item != null) {
                    items.add(item);
                }
            }
        } catch (IOException e) {
            LogManager.logException("Import CSV", e);
            throw new ImportException("Erreur lors de l'import CSV", e);
        }
        
        return items;
    }
    
    private Item parseItemFromCsv(String line) {
        String[] parts = line.split(",");
        if (parts.length < 7) return null;
        
        try {
            Item item = new Item();
            item.setId(Integer.parseInt(parts[0]));
            item.setName(unescapeCsv(parts[1]));
            item.setType(Integer.parseInt(parts[2]));
            item.setLevel(Integer.parseInt(parts[3]));
            item.setPod(Integer.parseInt(parts[4]));
            item.setPanoplie(Integer.parseInt(parts[5]));
            item.setStatsTemplate(unescapeCsv(parts[6]));
            
            return item;
        } catch (NumberFormatException e) {
            LogManager.logException("Parse CSV line", e);
            return null;
        }
    }
    
    private String unescapeCsv(String value) {
        if (value.startsWith("\"") && value.endsWith("\"")) {
            value = value.substring(1, value.length() - 1);
        }
        return value.replace("\"\"", "\"");
    }
}
```

## Import JSON

```java
public class JsonImporter {
    private final ObjectMapper mapper = new ObjectMapper();
    
    public List<Item> importFromJson(File file) {
        try {
            return mapper.readValue(file,
                mapper.getTypeFactory().constructCollectionType(List.class, Item.class));
        } catch (IOException e) {
            LogManager.logException("Import JSON", e);
            throw new ImportException("Erreur lors de l'import JSON", e);
        }
    }
}
```

## Import ZIP

```java
public class ZipImporter {
    private final ObjectMapper mapper = new ObjectMapper();
    
    public List<Item> importFromZip(File zipFile) {
        try (ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFile))) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().equals("items.json")) {
                    return mapper.readValue(zis,
                        mapper.getTypeFactory().constructCollectionType(List.class, Item.class));
                }
            }
            throw new ImportException("Fichier items.json non trouvé dans l'archive");
        } catch (IOException e) {
            LogManager.logException("Import ZIP", e);
            throw new ImportException("Erreur lors de l'import ZIP", e);
        }
    }
}
```

## Utilisation

```java
// Import CSV
CsvImporter csvImporter = new CsvImporter();
List<Item> itemsFromCsv = csvImporter.importFromCsv(new File("items.csv"));

// Import JSON
JsonImporter jsonImporter = new JsonImporter();
List<Item> itemsFromJson = jsonImporter.importFromJson(new File("items.json"));

// Import ZIP
ZipImporter zipImporter = new ZipImporter();
List<Item> itemsFromZip = zipImporter.importFromZip(new File("backup.zip"));
```

## Validation des Données Importées

```java
public class ImportValidator {
    public List<String> validateImport(List<Item> items) {
        List<String> errors = new ArrayList<>();
        
        // Vérifier les doublons d'ID
        Set<Integer> ids = new HashSet<>();
        for (Item item : items) {
            if (!ids.add(item.getId())) {
                errors.add("ID en double : " + item.getId());
            }
        }
        
        // Valider chaque item
        for (Item item : items) {
            errors.addAll(ValidationUtils.validateItem(item));
        }
        
        return errors;
    }
}
```

## Gestion des Erreurs

```java
public class ImportException extends RuntimeException {
    public ImportException(String message) {
        super(message);
    }
    
    public ImportException(String message, Throwable cause) {
        super(message, cause);
    }
}
```