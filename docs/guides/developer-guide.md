# Guide du Développeur

## Installation de l'Environnement

### Prérequis
- JDK 17+
- Maven 3.8+
- IDE (IntelliJ IDEA recommandé)
- Git

### Configuration
```bash
git clone https://github.com/lolcontest/java-project-2024.git
cd java-project-2024
mvn install
```

## Structure du Projet

### Packages Principaux
```
src/
├── main/
│   ├── java/
│   │   └── com/gamemanager/
│   ├── resources/
│   └── docs/
└── test/
```

### Conventions
- Code en UTF-8
- Indentation : 4 espaces
- Javadoc obligatoire pour les classes publiques

## Développement

### Création d'une Feature
1. Créer une branche : `feature/nom-feature`
2. Développer la fonctionnalité
3. Ajouter des tests
4. Créer une PR

### Tests
```bash
# Tests unitaires
mvn test

# Couverture
mvn jacoco:report
```

### Build
```bash
# Développement
mvn javafx:run

# Production
mvn package
```

## Modèles de Données

### Item
```java
public class Item {
    private IntegerProperty id;
    private StringProperty name;
    private List<Stat> stats;
    // ...
}
```

### Stat
```java
public class Stat {
    private String statId;
    private int minValue;
    private int maxValue;
    // ...
}
```

## Services

### ItemService
```java
public class ItemService {
    public void addItem(Item item);
    public void updateItem(Item item);
    public void deleteItem(int id);
    public List<Item> searchItems(String query);
}
```

## Interface Utilisateur

### MainFrame
- Utilise FXML pour la structure
- Bindings pour les mises à jour
- Gestion des événements

### Composants Personnalisés
1. Créer une classe héritant de Control/Region
2. Définir le style CSS
3. Implémenter les propriétés

## Debug

### Logs
```java
private static final Logger LOGGER = 
    Logger.getLogger(MaClasse.class.getName());

LOGGER.info("Message");
LOGGER.warning("Attention");
LOGGER.severe("Erreur");
```

### Profiling
1. Utiliser VisualVM
2. Activer JMX dans les options Java
3. Analyser les performances

## Déploiement

### Production
1. `mvn clean package`
2. Récupérer le JAR dans `target/`
3. Distribuer avec JRE