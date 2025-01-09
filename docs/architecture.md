# Architecture du Projet

## Vue d'ensemble

L'application suit une architecture MVC (Modèle-Vue-Contrôleur) avec les composants suivants :

```
com.gamemanager/
├── config/           # Configuration et paramètres
├── model/            # Modèles de données
├── service/          # Services métier
├── ui/               # Interface utilisateur JavaFX
├── util/             # Utilitaires
└── persistence/      # Gestion de la persistance
```

## Composants Principaux

### Modèle (model/)
- `Item.java`: Représentation d'un item avec ses propriétés
- `Stat.java`: Gestion des statistiques
- `StatDescriptions.java`: Dictionnaire des descriptions

### Services (service/)
- `ItemService.java`: CRUD et recherche d'items
- `ExportImportService.java`: Import/Export de données
- `NotificationService.java`: Gestion des notifications

### Interface Utilisateur (ui/)
- `MainFrame.java`: Fenêtre principale
- `StatsPanel.java`: Panneau des statistiques
- `ThemeManager.java`: Gestion des thèmes

## Flux de Données

1. L'utilisateur interagit avec l'UI
2. Les contrôleurs capturent les événements
3. Les services traitent les requêtes
4. Les modèles sont mis à jour
5. L'UI est rafraîchie via les bindings JavaFX

## Patterns Utilisés

- **Singleton**: Services et Managers
- **Observer**: Bindings JavaFX
- **Factory**: Création de composants UI
- **Strategy**: Gestion des exportations