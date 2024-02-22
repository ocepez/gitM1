package com1.dtos;



import lombok.Data;


@Data

public class MembresDto {

    private int id_membre;
    private String email_membre;
    private String nom_membre;
    private String prenom_membre;
    private int age_membre;
    private String adresse_membre;
    private String photo_membre;
}