package com.ubo.entity;


import lombok.Getter;
import lombok.Setter;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

import javax.persistence.Entity;

@Entity
public class Commentaire {


    @BsonProperty("_id")
    @BsonId
    private ObjectId id;
    @Getter
    @Setter
    @BsonProperty("contenu")
    private String contenu_commentaire;
    @Getter
    @Setter
    @BsonProperty("id_evenement")
    private int id_evenement;

    @Setter
    @Getter
    @BsonProperty("id_membre")
    private int id_membre;

}