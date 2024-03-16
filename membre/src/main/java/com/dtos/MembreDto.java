package com.dtos;

import lombok.Data;

/**
 * Objet de Transfert de Données (DTO) représentant un membre.
 *
 * @author Xavier F.
 */
@Data
public class MembreDto {

    /** Identifiant unique du membre. */
    private Integer Id;

    /** Adresse e-mail du membre. */
    private String email;

    /** Mot de passe associé au compte du membre. */
    private String password;

    /** Nom de famille du membre. */
    private String nom;

    /** Prénom du membre. */
    private String prenom;

    /** Rôle ou position du membre. */
    private String role;

    /** Adresse du membre. */
    private String adresse;

    /** URL de la photo du membre. */
    private String photo;
}
