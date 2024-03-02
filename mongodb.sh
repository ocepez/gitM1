# Copier le fichier BSON dans le conteneur MongoDB
sudo docker cp dbdata/commentaires.bson mongodb:/commentaires.bson

# Exécuter mongorestore dans le conteneur pour importer les données
sudo docker exec -it mongodb mongorestore --db commentaires --collection commentaires /commentaires.bson
