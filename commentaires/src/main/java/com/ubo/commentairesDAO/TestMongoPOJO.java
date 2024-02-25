package com.ubo.commentairesDAO;

import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringJoiner;

import static com.mongodb.client.model.Filters.eq;

import com.ubo.entity.Commentaire;
import lombok.extern.slf4j.XSlf4j;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import com.mongodb.ConnectionString;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;


@XSlf4j
public class TestMongoPOJO {

    public static void main(String[] args) {

        // connexion à la base Mongo et à la base "sports"
        CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
        CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));

        ConnectionString connectionString = new ConnectionString("mongodb://localhost:27017");
        MongoClient mongoClient = MongoClients.create(connectionString);
        MongoDatabase database = mongoClient.getDatabase("commentaires").withCodecRegistry(pojoCodecRegistry);
        System.out.println("Connexion établie\n");

        MongoCollection<Commentaire> commentaireMongoCollection = database.getCollection("commentaires", Commentaire.class);

        Iterable<Commentaire> commentaires =  commentaireMongoCollection.find();

        Iterator<Commentaire> com = commentaires.iterator();
        for(Commentaire fed : commentaireMongoCollection.find()) {
            System.out.println("\n** membre : "+fed.getId_membre()+" **");
            System.out.println("\n** evenement : "+fed.getId_evenement()+" **");
            System.out.println("\n**  contenu : "+fed.getContenu_commentaire()+" **");


        }

        for(int j = 0; j< 10;j++){
            // ajout d'un commentaire
            Commentaire saturnin = new Commentaire();
            saturnin.setId_evenement(j+1);
            saturnin.setId_membre(j+1);
            saturnin.setContenu_commentaire("Bien essayé !  "+j);
            commentaireMongoCollection.insertOne(saturnin);

        }

     /*   for(Commentaire fed : commentaireMongoCollection.find()) {
            System.out.println("\n** membre : "+fed.getId_membre()+" **");
            System.out.println("\n** evenement : "+fed.getId_evenement()+" **");
            System.out.println("\n**  contenu : "+fed.getContenu_commentaire()+" **");
        }

      */
    }

}