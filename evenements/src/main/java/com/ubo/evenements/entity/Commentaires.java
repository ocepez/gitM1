package com.ubo.evenements.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.NotFound;

@Entity
@Data
@Table(name="commentaire")
public class Commentaires {

    private String contenu_commentaire;
    private String id_evenement;
    private String email_membre;


}