package com.projetsi.core.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Membre {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_membre;
    private String email_membre;
    private String nom_membre;
    private String prenom_membre;
    private String role_membre;
    private String adresse_membre;
    private String photo_membre;


}
