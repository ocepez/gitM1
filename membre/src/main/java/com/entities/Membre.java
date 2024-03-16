package com.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entité représentant un membre.
 *
 * @author Xavier F.
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Membre {

    /** Identifiant unique du membre. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_membre;

    /** Adresse e-mail du membre. */
    private String email_membre;

    /** Mot de passe associé au compte du membre. */
    private String password_membre;

    /** Nom de famille du membre. */
    private String nom_membre;

    /** Prénom du membre. */
    private String prenom_membre;

    /** Rôle ou position du membre. */
    private String role_membre;

    /** Adresse du membre. */
    private String adresse_membre;

    /** URL de la photo du membre. */
    private String photo_membre;
}
