# Projet SI : Application de gestion des évènements d'un association

Cette application web vise à faciliter la gestion des événements d'une association ainsi que des informations relatives à ses membres. L'application propose plusieurs services implémentés à l'aide de différentes technologies telles que Servlet et Spring, et repose sur des serveurs de bases de données SQL et MongoDB.

## Auteurs
 -- Maxime Andre
 -- Oceane Pezennec
 -- Xavier Flegeau
## Entités principales

## Comment lancer le projet
Vous devrez d'abord installer le projet.
```bash
git clone https://github.com/ocepez/gitM1.git
```
À la racine du projet installé. Pour lancer les APIs côté back :
```bash
docker-compose up -d --build
```
Pour avoir un jeu de données Mongodb et Mariadb:
```bash
sudo ./mongodb.sh
sudo ./volume.sh
```
Pour arrêter les APIS : 
```bash
docker-compose down -v
```
Pour lancer le serveur de développement, utilisez la commande :
```bash
cd front
npm run server
```


### Membres

Un membre est défini par les éléments suivants :
* Email
* Nom
* Prénom
* Rôle
* Adresse

Chaque membre est identifié de manière unique par son nom et son prénom. L'authentification dans l'application se fait en utilisant un mot de passe.

### Événement

Un événement est défini par les éléments suivants :
* Nom
* Date de début
* Durée
* Description
* Nombre max de participants
* Lieu

Deux événements ne peuvent pas avoir lieu simultanément au même endroit. Le lieu choisi pour l'événement doit avoir une capacité suffisante pour accueillir tous les participants. Les membres peuvent également ajouter des commentaires sur un événement.

### Lieu

Un lieu est défini par les éléments suivants :
* Nom
* Adresse
* Capacité d'accueil

### Inscription

Un membre peut s'inscrire à un événement en respectant les contraintes suivantes :
* Ne pas être déjà inscrit à un événement se chevauchant dans le temps.
* Ne s'inscrire qu'à un événement qui n'a pas encore eu lieu et dont le nombre maximal de participants n'est pas atteint.
* Possibilité de se désinscrire d'un événement si celui-ci n'a pas encore eu lieu.

## Fonctionnalités attendus

L'application doit offrir les fonctionnalités suivantes :
* Visualisation de l'ensemble des membres de l'association.
* Visualisation de l'ensemble des événements (tous ou à venir).
* Affichage des inscriptions pour un événement donné, avec le nombre d'inscrits.
* Liste des événements auxquels un membre est inscrit (tous ou à venir).
* Création, modification et suppression de membres et d'événements (ouvert à tous les membres connectés pour créer un événement).
* Affichage des événements d'un lieu (tous ou à venir) avec la carte d'accès associée.

## Application Vue.Js (FRONTEND)

L'interface utilisateur de notre application sera développée en utilisant Vue.js. Cette section se concentrera sur la mise en place du frontend de l'application, permettant aux utilisateurs d'interagir avec les fonctionnalités décrites précédemment.

### Structure du projet


### Composants Principaux
```
|-- src
|   |-- components
|   |
|   |-- pages
|   |
|   |-- router
|   |
|   |-- store
|   |
```
* components : Contient des composants réutilisables utilisés dans différentes parties de l'application. Ces composants peuvent inclure des éléments tels que des formulaires, etc.
* pages : Chaque fichier correspond à une page spécifique de l'application. Ces pages sont responsables de la mise en page et de la logique associée à chaque vue.
* router : Contient la configuration du routeur Vue.js. Cela permet la navigation entre les différentes pages de l'application.
* store : Contient la gestion de l'état global de l'application à l'aide de Vuex. Cela inclut la définition des actions, mutations et états nécessaires.

### Mise en place

Pour lancer le serveur de développement, utilisez la commande :
```bash
npm run server
```

## API Événements (BACKEND)

### Structure du projet
L'API est implémentée en Spring.
Utilisation d’une base de données MariaDB.
L’API Événements permet de :
 - Récupérer la liste de tous les événements stockés en base de données.
 - Ajouter un événement en base de données.
 - Récupérer les informations d’un événement via son identifiant.
 - Mettre à jour les informations d’un événement en base de données (avec son identifiant).
 - Supprimer un événement en base de données (avec son identifiant).
 - Récupérer la liste de membres inscrits à un événement (avec son identifiant).
 - Inscrire un membre à un événement.
 - Récupérer la liste des inscriptions d’un membre (avec l’identifiant du membre).
 - Supprimer l’inscription d’un membre à un événement (avec l’identifiant du membre et de l’événement).

### Mise en place

Pour lancer le serveur de l'api Événements, utilisez la commande :
```bash
docker-compose up evenements 
```
#### Swagger
```bash
locahost:8040/swagger-ui/index.html
```
## API Membres (BACKEND)

### Structure du projet
L'API est implémentée en Sping.
Utilisation d’une base de données MariaDB.

L’API Membres permet de :
 - Créer une session de connexion pour un membre (avec son email et son mot de passe).
 - Récupérer la liste de tous les membres stockés en base de données.
 - Ajouter un membre en base de données.
 - Récupérer les informations d’un membre via son identifiant.
 - Mettre à jour les informations d’un membre en base de données (avec son identifiant).
 - Supprimer un membre en base de données (avec son identifiant).
 - Récupérer la liste des événements pour lesquels le membre est inscrit (avec son identifiant).

### Mise en place
Pour lancer le serveur de l'api Membres, utilisez la commande :
```bash
docker-compose up membres 
```
#### Swagger
```bash
locahost:8050/swagger-ui/index.html
```
## API Commentaires (BACKEND)

### Structure du projet
L'API est implémentée en Servlet.
Utilisation d’une base de données MongoDB.

L’API Commentaire permet de :
 - Ajouter un commentaire en base de données.
 - Supprimer un commentaire en base de données (avec son identifiant).
 - Récupérer la liste des commentaires qu’un membre a posté (avec l’identifiant du membre).
 - Récupérer la liste des commentaires d’un événement (avec l’identifiant de l’événement).

### Mise en place
Pour lancer le serveur de l'api Commentaires, utilisez la commande :
```bash
docker-compose up commentaires 
```
#### Swagger
```bash
locahost:8080/swagger
```
## API Lieux (BACKEND)

### Structure du projet
l'API est implémentée en Servlet.
Utilisation d’une base de données MariaDB.

L’API Lieux permet de :
 - Récupérer la liste de tous les lieux stockés en base de données.
 - Ajouter un lieu en base de données.
 - Récupérer les informations d’un lieu via son identifiant.
 - Supprimer un lieu en base de données (avec son identifiant).

### Mise en place
Pour lancer le serveur de l'api Lieux, utilisez la commande :
```bash
docker-compose up lieux 
```
#### Swagger
```bash
locahost:9080/swagger
```
