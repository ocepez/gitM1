package com.ubo.commentaires;

import com.mongodb.ConnectionString;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.ubo.entity.Commentaire;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import java.io.IOException;

import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

@WebServlet(name = "CommentaireServlet", value = "/commentaires", description = "Commentaires des événements")
public class CommentaireServlet extends HttpServlet {

    private MongoCollection<Commentaire> commentaireMongoCollection;

    public CommentaireServlet() {
        super();
        CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
        CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));

        ConnectionString connectionString = new ConnectionString("mongodb://localhost:27017");
        MongoClient mongoClient = MongoClients.create(connectionString);
        MongoDatabase database = mongoClient.getDatabase("commentaires").withCodecRegistry(pojoCodecRegistry);
        System.out.println("Connexion établie\n");

        this.commentaireMongoCollection = database.getCollection("commentaires", Commentaire.class);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Logique pour traiter la création d'un commentaire (POST)
        response.setStatus(HttpServletResponse.SC_CREATED);
        response.getWriter().println("Commentaire créé avec succès");
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getPathInfo(); // Récupérer le chemin de l'URL après "/commentaire/"

        // Vérifier si un ID a été fourni
        if (id == null || id.length() <= 1) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().println("ID du commentaire manquant dans le chemin de l'URL");
            return;
        }

        // Récupérer l'ID du commentaire
        id = id.substring(1); // Supprimer le "/" initial
        // Logique pour supprimer le commentaire avec l'ID spécifié (DELETE)
        response.getWriter().println("Commentaire avec l'ID " + id + " supprimé avec succès");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo(); // Récupérer le chemin de l'URL après "/commentaire/"

        // Vérifier si un ID a été fourni
        if (pathInfo == null || pathInfo.length() <= 1) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().println("ID du commentaire manquant dans le chemin de l'URL");
            return;
        }

        // Récupérer l'ID du commentaire
        String id = pathInfo.substring(1); // Supprimer le "/" initial

        if (pathInfo.startsWith("/membres")) {
            // Logique pour récupérer les commentaires associés à un membre avec l'ID spécifié (GET)
            response.getWriter().println("Commentaires associés au membre avec l'ID " + id);
        } else if (pathInfo.startsWith("/evenements")) {
            // Logique pour récupérer les commentaires associés à un événement avec l'ID spécifié (GET)
            response.getWriter().println("Commentaires associés à l'événement avec l'ID " + id);
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().println("Méthode de récupération de commentaire non valide");
        }
    }
}