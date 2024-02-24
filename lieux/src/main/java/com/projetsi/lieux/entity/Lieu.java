package com.projetsi.lieux.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

/**
 * Entité représentant un lieu avec un identifiant, son nom, son adresse et sa capacité.
 *
 * @author Maxime A.
 */
@Entity
@Table(name = "lieu")
public class Lieu {

    /**
     * Identifiant unique du lieu.
     */
    @Id
    @Column(name = "id_lieu")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id_lieu")
    private int id;

    /**
     * Nom du lieu.
     */
    @Column(name = "nom_lieu")
    private String nom;

    /**
     * Adresse du lieu.
     */
    @Column(name = "adresse_lieu")
    private String adresse;

    /**
     * Capacité du lieu.
     */
    @Column(name = "capacite_lieu")
    private int capacite;

    /**
     * Obtient l'identifiant du lieu.
     *
     * @return L'identifiant du lieu.
     */
    public int getId() {
        return id;
    }

    /**
     * Définit l'id du lieu.
     *
     * @param id L'id du lieu.
     */
    public void setId(int id) { this.id = id; }

    /**
     * Obtient le nom du lieu.
     *
     * @return  Le nom du lieu.
     */
    public String getNom() {
        return nom;
    }

    /**
     * Définit le nom du lieu.
     *
     * @param nom Le nom à définir.
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Obtient l'adresse du lieu.
     *
     * @return L'adresse du lieu.
     */
    public String getAdresse() {
        return adresse;
    }

    /**
     * Définit l'adresse du lieu.
     *
     * @param adresse L'adresse à définir.
     */
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    /**
     * Obtient la capacité du lieu.
     *
     * @return La capacité du lieu.
     */
    public int getCapacite() {
        return capacite;
    }

    /**
     * Définit la capacité du lieu.
     *
     * @param capacite La capacité à définir.
     */
    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }
}
