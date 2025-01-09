# Game Manager Application

Application Java/JavaFX pour la gestion d'items de jeu.

## Structure du projet

```
com.gamemanager
  ├── config/        # Configuration XML et gestion
  ├── model/         # Classes de modèle (Item, Stat, etc.)
  ├── service/       # Services métier
  ├── ui/           # Interface utilisateur JavaFX
  └── util/         # Classes utilitaires
```

## Prérequis

- Java 17 ou supérieur
- Maven 3.8 ou supérieur

## Installation

1. Cloner le repository
2. Exécuter `mvn clean install`
3. Lancer avec `mvn javafx:run`

## Fonctionnalités

- Gestion des items (création, modification, recherche)
- Gestion des statistiques dynamiques
- Interface moderne avec JavaFX
- Sauvegarde locale des données