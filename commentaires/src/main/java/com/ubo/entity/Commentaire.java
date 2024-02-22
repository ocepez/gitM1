package com.ubo.entity;


import javax.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class Commentaire {

    private String contenu_commentaire;
    private String id_evenement;
    private String email_membre;


}