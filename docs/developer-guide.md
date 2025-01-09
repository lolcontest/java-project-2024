# Guide du développeur

## Architecture

L'application suit le pattern MVC (Modèle-Vue-Contrôleur) avec les particularités suivantes :

### Modèle

- Utilisation des propriétés JavaFX pour le binding bidirectionnel
- Support de la sérialisation JSON
- Validation des données intégrée

### Vue

- Interface FXML pour la séparation claire du design
- Composants réutilisables
- Support des thèmes CSS

### Contrôleur

- Gestion centralisée des événements
- Support des raccourcis clavier
- Injection de dépendances via FXML

## Bonnes pratiques

### Gestion des erreurs

1. Utiliser ErrorHandler pour la gestion centralisée
2. Logger les erreurs avec le niveau approprié
3. Afficher des messages utilisateur clairs

### Performance

1. Utiliser des propriétés observables pour les mises à jour UI
2. Éviter les calculs lourds dans le thread UI
3. Implémenter la pagination pour les grandes listes

### Tests

1. Tests unitaires pour chaque composant
2. Tests d'intégration pour les workflows critiques
3. Tests UI avec TestFX

## Guide d'implémentation

### Ajout d'une nouvelle fonctionnalité

1. Créer une branche feature/
2. Implémenter les modèles nécessaires
3. Créer les services associés
4. Ajouter les contrôleurs et vues
5. Écrire les tests
6. Documenter

### Modification du modèle de données

1. Mettre à jour les classes modèles
2. Adapter les services de persistance
3. Mettre à jour les tests
4. Gérer la compatibilité descendante

### Personnalisation de l'interface

1. Utiliser les feuilles de style CSS
2. Créer des composants réutilisables
3. Supporter les thèmes sombres/clairs

## API et Services

### DataManager

Gère la persistance des données :
```java
public void saveItems(List<Item> items)
public List<Item> loadItems()
public void createBackup()
public void restoreFromBackup()
```

### ExportImportService

Gère les exports/imports :
```java
public void exportData(List<Item> items, Stage stage)
public List<Item> importData(Stage stage)
public void exportToCsv(List<Item> items, Stage stage)
```

### NotificationService

Gère les notifications utilisateur :
```java
public void showError(String title, String message)
public void showSuccess(String message)
public boolean confirmAction(String title, String message)
```

## Workflow Git

1. Créer une branche pour chaque fonctionnalité
2. Faire des commits atomiques et descriptifs
3. Créer une Pull Request pour la revue
4. Merger après validation

## Debug et Monitoring

### Logs

Utiliser le logger approprié :
```java
private static final Logger LOGGER = Logger.getLogger(ClassName.class.getName());
LOGGER.info("Message d'information");
LOGGER.warning("Avertissement");
LOGGER.severe("Erreur critique");
```

### Débogage

1. Utiliser les points d'arrêt JavaFX pour l'UI
2. Logger les états importants
3. Implémenter des vues de debug (optionnel)

## Build et Déploiement

### Maven

Configuration requise :
```xml
<properties>
    <maven.compiler.source>17</maven.compiler.source>
    <maven.compiler.target>17</maven.compiler.target>
    <javafx.version>21.0.1</javafx.version>
</properties>
```

### Création du package

1. `mvn clean package` - crée le JAR
2. `mvn javafx:jlink` - crée une image runtime
3. `mvn javafx:run` - lance l'application

## Maintenance

### Mises à jour

1. Vérifier les dépendances régulièrement
2. Mettre à jour les bibliothèques
3. Tester la rétrocompatibilité

### Performance

1. Profiler l'application régulièrement
2. Optimiser les requêtes et le chargement
3. Surveiller l'utilisation mémoire

## Sécurité

1. Valider toutes les entrées utilisateur
2. Échapper les caractères spéciaux
3. Gérer les permissions fichiers
4. Sécuriser les données sensibles