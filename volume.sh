

echo "----Creer le volume----"
docker volume create dbdata
echo  "----Copier le fichier SQL dans le conteneur MariaDB----"
docker cp dbdata/projet_api.sql dbdata:/home/projet_api.sql
echo "----listes tout les volumes----"
docker volume ls
echo "----On vérifie ce que contient dbdata----"
docker volume inspect dbdata
echo "----On supprime dbdata----"
# On supprime le volume
docker volume rm dbdata