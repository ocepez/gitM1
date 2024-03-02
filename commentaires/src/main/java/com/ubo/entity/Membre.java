package com.ubo.entity;


import lombok.Data;

import javax.persistence.Entity;

@Entity
@Data
public class Membre {

    private int id_membre;
    private String email_membre;
    private String nom_membre;
    private String prenom_membre;
    private int age_membre;
    private String adresse_membre;
    private String photo_membre;
}