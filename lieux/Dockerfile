FROM amazoncorretto:17-alpine-jdk AS builder

# Définir le répertoire de travail dans le conteneur
WORKDIR /app

# Copier le fichier pom.xml pour installer les dépendances
COPY pom.xml .

# Exécuter Maven pour télécharger les dépendances
RUN apk add --no-cache maven && mvn dependency:go-offline

# Copier le reste du code source
COPY src ./src

# Compiler l'application
RUN mvn package

# Deuxième étape pour réduire la taille de l'image finale
FROM amazoncorretto:17-alpine-jdk

# Copier l'application compilée depuis l'étape précédente
COPY --from=builder /app/target/lieux-api-1.0-SNAPSHOT.war /app/lieux.war

# Exposer le port sur lequel votre application écoute
EXPOSE 8080

# Commande pour démarrer l'application lorsque le conteneur démarre
CMD ["java", "-jar", "/app/lieux.war"]