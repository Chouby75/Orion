# P6 - Projet Full-Stack

Ce dépôt contient le code source d'une application web Full-Stack développée dans le cadre du projet P6.

> [Ajoutez ici une brève description du projet, de ses objectifs et de ses fonctionnalités principales.]

## Table des matières

- [Technologies](#technologies)
- [Prérequis](#prérequis)
- [Installation](#installation)
  - [Partie Backend (Spring)](#partie-backend-spring)
  - [Partie Frontend (Angular)](#partie-frontend-angular)
- [Scripts utiles](#scripts-utiles)

## Technologies

L'application est construite avec les technologies suivantes :

- **Frontend** : Angular (v14.1.3)
- **Backend** : Spring Boot (Java)
- **Base de données** : MySQL

## Prérequis

Avant de procéder à l'installation, assurez-vous que les outils suivants sont installés sur votre système :

- Node.js et npm
- Angular CLI
- JDK 11 ou une version supérieure
- Un serveur MySQL fonctionnel

## Installation

Suivez ces étapes pour configurer et lancer le projet en local.

### Partie Backend (Spring)

1.  **Créer la base de données**
    Connectez-vous à votre client MySQL et exécutez la requête suivante :

    ```sql
    CREATE DATABASE mdd_api;
    ```

2.  **Configurer la connexion**
    Dans le répertoire du backend, accédez au fichier `src/main/resources/application.properties`. Renseignez les informations de connexion à votre base de données :

    ```properties
    # Port du serveur backend
    server.port=3001

    # Configuration de la source de données MySQL
    spring.datasource.url=jdbc:mysql://localhost:3306/mdd_api
    spring.datasource.username=VOTRE_USERNAME_SQL
    spring.datasource.password=VOTRE_MOT_DE_PASSE_SQL

    # Configuration Hibernate
    spring.jpa.hibernate.ddl-auto=update
    ```

    _Remplacez `VOTRE_USERNAME_SQL` et `VOTRE_MOT_DE_PASSE_SQL` par vos identifiants._

3.  **Lancer le serveur**
    Démarrez l'application Spring Boot via votre IDE (ex: IntelliJ, Eclipse) ou en utilisant le terminal avec Maven ou Gradle. Le serveur sera accessible à l'adresse `http://localhost:3001`.

### Partie Frontend (Angular)

1.  **Accéder au répertoire**
    Ouvrez un terminal et naviguez jusqu'au dossier racine du projet frontend.

2.  **Installer les dépendances**
    Exécutez la commande suivante pour installer tous les packages requis :

    ```bash
    npm install
    ```

3.  **Démarrer le serveur de développement**
    Lancez le serveur avec la commande :
    ```bash
    ng serve
    ```
    L'application sera alors disponible à l'adresse `http://localhost:4200/`.

## Scripts utiles

Les scripts suivants sont disponibles pour la gestion du projet frontend :

- `ng serve` : Démarre le serveur de développement local.
- `ng build` : Compile l'application pour un environnement de production. Les fichiers sont générés dans le répertoire `dist/`.
