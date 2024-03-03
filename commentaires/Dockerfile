# Utilisez une image de base contenant Java 8 et Tomcat
FROM tomcat:latest

# Copiez le fichier war de votre API dans le répertoire webapps de Tomcat
COPY build/libs/commentaires-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/commentaires.war

# Exposez le port sur lequel Tomcat s'exécute
EXPOSE 8080