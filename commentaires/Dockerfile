FROM tomcat:latest

# Copie du fichier WAR dans le répertoire webapps de Tomcat
COPY ./build/libs/commentaires-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/commentaires.war

# Exposition du port par défaut de Tomcat
EXPOSE 8080