package com.ubo.commentaires;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import java.util.ArrayList;
import java.util.List;

import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;


/**
 * Classe Commentaire
 *
 * @author O. Pezennec
 */
@WebServlet(name = "CommentaireServlet", value = "/commentaires", description = "Commentaires des événements")
public class CommentaireServlet extends HttpServlet {


    /**
     * Collection MongoDb commentaires
     */
    private final MongoCollection<Commentaire> commentaireMongoCollection;


    /**
     * Constructeur qui construit la connexion avec la base de données MongoDB
     */
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

    /**
     * @param id l'id d'un membre
     * @return la liste des commentaires d'un membre
     */
    private List<Commentaire> getListeByMembre(int id) {
        List<Commentaire> commentaries = new ArrayList<>();
        for (Commentaire commentaire : this.commentaireMongoCollection.find()) {
            if (commentaire.getId_membre() == id) {
                commentaries.add(commentaire);
            }
        }
        return commentaries;
    }

    /**
     * @param id l'id d'un evenement
     * @return La liste de commentaires d'un événement
     */
    private List<Commentaire> getListeByEvenement(int id) {
        List<Commentaire> commentaires = new ArrayList<>();
        for (Commentaire commentaire : this.commentaireMongoCollection.find()) {
            if (commentaire.getId_evenement() == id) {
                commentaires.add(commentaire);
            }
        }
        return commentaires;
    }


    /***
     * @param request Contient le request body qui sera inséré dans la base de données MongoDB
     * @param response Renvoie un code de retour HTTP
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Logique pour traiter la création d'un commentaire (POST)
        String pathInfo = request.getPathInfo();
        System.out.println("doPost : " + pathInfo);

        ObjectMapper objectMapper = new ObjectMapper();
        response.setContentType("application/json");

        Commentaire com = objectMapper.readValue(request.getReader(), Commentaire.class);
        this.commentaireMongoCollection.insertOne(com);
        String json = objectMapper.writeValueAsString(Commentaire.class);
        response.getWriter().write(json);
        response.setStatus(HttpServletResponse.SC_CREATED);
        response.getWriter().println("Commentaire créé avec succès");
    }

    /**
     * @param request  Contient l'id du commentaire
     * @param response Renvoie le code de retour HTTP
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo(); // Récupérer le chemin de l'URL après "/commentaire/"
        System.out.println("doDelete : " + pathInfo);
        // Vérifier si un ID a été fourni
        if (pathInfo == null || pathInfo.length() <= 1) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().println("ID du commentaire manquant dans le chemin de l'URL");
            return;
        }

        // Récupérer l'ID du commentaire
        String[] ids = pathInfo.split("/");
        String id = ids[2];
        System.out.println("ID = " + id);
        // Logique pour supprimer le commentaire avec l'ID spécifié (DELETE)
        response.getWriter().println("Commentaire avec l'ID " + id + " supprimé avec succès");
    }

    /**
     * @param request  Récupère le chemin de l'url
     * @param response Renvoie la liste de commentaires, soit d'un membre, soit d'un commentaire
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo(); // Récupérer le chemin de l'URL après "/commentaire/"
        System.out.println("doGet : " + pathInfo);
        // Vérifier si un ID a été fourni
        if (pathInfo == null || pathInfo.length() <= 1) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().println("ID du commentaire manquant dans le chemin de l'URL");
            return;
        }
        // Récupérer l'ID
        String[] ids = pathInfo.split("/");
        String id = ids[2];
        response.setContentType("application/json");
        if (pathInfo.startsWith("/membre")) {
            // Logique pour récupérer les commentaires associés à un membre avec l'ID spécifié (GET)
            try {
                List<Commentaire> com = this.getListeByMembre(Integer.parseInt(id));
                ObjectMapper mapper = new ObjectMapper();
                String jsonCommentaires = mapper.writeValueAsString(com);
                // System.out.println("json = "+jsonCommentaires);
                response.getWriter().println(jsonCommentaires);
            } catch (NumberFormatException e) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().println("Méthode de récupération de commentaire non valide");

            }

        } else if (pathInfo.startsWith("/evenement")) {
            // Logique pour récupérer les commentaires associés à un événement avec l'ID spécifié (GET)
            List<Commentaire> com = this.getListeByEvenement(Integer.parseInt(id));
            ObjectMapper mapper = new ObjectMapper();
            String jsonCommentaires = mapper.writeValueAsString(com);
            response.getWriter().println(jsonCommentaires);
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().println("Méthode de récupération de commentaire non valide");
        }
    }
}