package com.ubo.evenements.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
@Entity
@Data
@Table(name="membre")
public class Membres {

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