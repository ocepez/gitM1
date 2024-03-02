//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.projetsi.core.entities;

import jakarta.persistence.Table;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Table(name = "Evenement")
public class Evenement {
    @Id
    @GeneratedValue
    private int id_evenement;
    private String nom_evenement;
    private int duree_evenement;
    private Date date_evenement;
    private String desc_evenement;
    private String image_evenement;
    private int id_lieu;

    public Evenement() {
    }

    public int getId_evenement() {
        return this.id_evenement;
    }

    public void setId_evenement(int id_evenement) {
        this.id_evenement = id_evenement;
    }

    public String getNom_evenement() {
        return this.nom_evenement;
    }

    public void setNom_evenement(String nom_evenement) {
        this.nom_evenement = nom_evenement;
    }

    public int getDuree_evenement() {
        return this.duree_evenement;
    }

    public void setDuree_evenement(int duree_evenement) {
        this.duree_evenement = duree_evenement;
    }

    public Date getDate_evenement() {
        return this.date_evenement;
    }

    public void setDate_evenement(Date date_evenement) {
        this.date_evenement = date_evenement;
    }

    public String getDesc_evenement() {
        return this.desc_evenement;
    }

    public void setDesc_evenement(String desc_evenement) {
        this.desc_evenement = desc_evenement;
    }

    public String getImage_evenement() {
        return this.image_evenement;
    }

    public void setImage_evenement(String image_evenement) {
        this.image_evenement = image_evenement;
    }

    public int getId_lieu() {
        return this.id_lieu;
    }

    public void setId_lieu(int id_lieu) {
        this.id_lieu = id_lieu;
    }
}
