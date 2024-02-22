package com1.entities;


import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Commentaire {

    @Id
    private String contenu_commentaire;
    private String id_evenement;
    private String email_membre;


}