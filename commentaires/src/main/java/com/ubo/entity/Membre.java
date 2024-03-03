package com.ubo.entity;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
public class Membre {

    @Getter
    @Setter
    private int id_membre;

    @Getter
    @Setter
    private String email_membre;
    private String nom_membre;

    @Getter
    @Setter
    private String prenom_membre;

    @Getter
    @Setter
    private int age_membre;

    @Getter
    @Setter
    private String adresse_membre;

    @Getter
    @Setter
    private String photo_membre;
}