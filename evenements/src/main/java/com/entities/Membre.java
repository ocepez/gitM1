package com.entities;



import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Membre {

    @Id
    @GeneratedValue
    private int id_membre;
    private String email_membre;
    private String nom_membre;
    private String prenom_membre;
    private int age_membre;
    private String adresse_membre;
    private String photo_membre;
}