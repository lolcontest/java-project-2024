# Game Manager

Application professionnelle de gestion d'items de jeu développée en Java/JavaFX. Ce logiciel permet la gestion complète des items avec leurs statistiques, offrant une interface moderne et intuitive.

## 🚀 Fonctionnalités

### Gestion des Items
- Création, modification et suppression d'items
- Recherche avancée par ID ou nom
- Gestion dynamique des statistiques
- Support du format hexadécimal pour les valeurs de stats
- Validation complète des données

### Interface Utilisateur
- Interface moderne avec JavaFX
- Support des thèmes clair/sombre
- Raccourcis clavier pour toutes les actions principales
- Zones cliquables pour la navigation
- Interface responsive et adaptative

### Données et Persistance
- Sauvegarde locale des données
- Export/Import au format ZIP
- Export des données en CSV
- Système de backup automatique
- Configuration via XML

### Fonctionnalités Avancées
- Système de logs détaillé
- Gestion des erreurs robuste
- Aide intégrée
- Préférences utilisateur persistantes

## 🛠️ Prérequis

- Java 17 ou supérieur
- Maven 3.8 ou supérieur
- Au moins 512 Mo de RAM disponible
- Espace disque : 100 Mo minimum

## ⚙️ Installation

1. Cloner le repository :
```bash
git clone https://github.com/lolcontest/java-project-2024.git
```

2. Compiler le projet :
```bash
cd java-project-2024
mvn clean install
```

3. Lancer l'application :
```bash
mvn javafx:run
```

## 🎮 Utilisation

### Raccourcis Clavier
| Raccourci    | Action                    |
|--------------|---------------------------|
| Ctrl+S       | Sauvegarder              |
| Ctrl+N       | Nouvel item              |
| Ctrl+F       | Rechercher               |
| Delete       | Supprimer l'item         |
| Ctrl+E       | Exporter                 |
| Ctrl+I       | Importer                 |
| Ctrl+T       | Changer de thème         |
| F1           | Aide                     |

### Format des Statistiques
Les statistiques suivent le format : `statId#minValue#maxValue`

Exemple : `7d#3E8#3E8` représente :
- statId: 7d (dommages)
- minValue: 3E8 (1000 en hexadécimal)
- maxValue: 3E8 (1000 en hexadécimal)

## 🔧 Configuration

Le fichier `config.xml` à la racine permet de configurer :
- Les chemins de sauvegarde
- Les options d'interface
- Les paramètres de log

## 📚 Documentation

- La documentation utilisateur est accessible via F1 dans l'application
- La documentation technique complète est disponible dans le dossier `/docs`
- Les JavaDocs sont générés lors de la compilation

## 🧪 Tests

Exécuter les tests :
```bash
mvn test
```

La couverture de tests peut être consultée dans `target/site/jacoco/index.html` après l'exécution de :
```bash
mvn jacoco:report
```

## 🤝 Contribution

1. Fork le projet
2. Créer une branche (`git checkout -b feature/nouvelle-fonctionnalite`)
3. Commit les changements (`git commit -am 'Ajout d'une nouvelle fonctionnalité'`)
4. Push la branche (`git push origin feature/nouvelle-fonctionnalite`)
5. Créer une Pull Request

## 📝 License

Ce projet est sous licence MIT - voir le fichier [LICENSE.md](LICENSE.md) pour plus de détails.

## 👥 Auteurs

- Développement initial : équipe Game Manager
- Maintenance : [Votre équipe]

## 🔄 Mises à jour

Le projet suit la gestion sémantique des versions. Les notes de version sont disponibles dans [CHANGELOG.md](CHANGELOG.md).

## 📞 Support

Pour toute question ou problème :
1. Consulter la documentation intégrée (F1)
2. Vérifier les [Issues GitHub](https://github.com/lolcontest/java-project-2024/issues)
3. Ouvrir une nouvelle issue si nécessaire