package com.ubo.entity;


import javax.persistence.Entity;
import lombok.Data;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

@Entity
public class Commentaire {



   @BsonProperty("_id")
   @BsonId
    private ObjectId id;
    @BsonProperty("contenu")
    private String contenu_commentaire;
    @BsonProperty("id_evenement")
    private int id_evenement;

    @BsonProperty("id_membre")
    private int id_membre;


    public String getContenu_commentaire() {
        return contenu_commentaire;
    }

    public void setContenu_commentaire(String contenu_commentaire) {
        this.contenu_commentaire = contenu_commentaire;
    }

    public int getId_evenement() {
        return id_evenement;
    }

    public void setId_evenement(int id_evenement) {
        this.id_evenement = id_evenement;
    }

    public int getId_membre() {
        return id_membre;
    }

    public void setId_membre(int id_membre) {
        this.id_membre = id_membre;
    }
}