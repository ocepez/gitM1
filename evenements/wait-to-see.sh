#!/bin/bash

# Attendez quelques secondes pour que MariaDB démarre
sleep 10

# Copiez le fichier init.sql dans le conteneur MariaDB
docker cp init.sql mariadb:/docker-entrypoint-initdb.d/init.sql

# Redémarrez le conteneur MariaDB pour exécuter le script SQL
docker-compose restart mariadb