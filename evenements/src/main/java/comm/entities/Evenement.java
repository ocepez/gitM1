package comm.entities;


import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "evenement")
public class Evenement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_evenement;
    private String nom_evenement;
    private int duree_evenement;
    private Date date_evenement;
    private String desc_evenement;
    private String image_evenement;
    private int nb_max_evenement;

    @ManyToMany
    @JoinTable(name = "inscription",
            joinColumns = @JoinColumn(name = "id_evenement"),
            inverseJoinColumns = @JoinColumn(name = "id_membre"))
    @JsonManagedReference
    private Set<Membre> membres = new HashSet<>();
    private int id_lieu;

    public int getId_evenement() {
        return id_evenement;
    }

    public void setId_evenement(int id_evenement) {
        this.id_evenement = id_evenement;
    }

    public String getNom_evenement() {
        return nom_evenement;
    }

    public void setNom_evenement(String nom_evenement) {
        this.nom_evenement = nom_evenement;
    }

    public int getDuree_evenement() {
        return duree_evenement;
    }

    public void setDuree_evenement(int duree_evenement) {
        this.duree_evenement = duree_evenement;
    }

    public Date getDate_evenement() {
        return date_evenement;
    }

    public void setDate_evenement(Date date_evenement) {
        this.date_evenement = date_evenement;
    }

    public String getDesc_evenement() {
        return desc_evenement;
    }

    public void setDesc_evenement(String desc_evenement) {
        this.desc_evenement = desc_evenement;
    }

    public String getImage_evenement() {
        return image_evenement;
    }

    public void setImage_evenement(String image_evenement) {
        this.image_evenement = image_evenement;
    }

    public int getId_lieu() {
        return id_lieu;
    }

    public void setId_lieu(int id_lieu) {
        this.id_lieu = id_lieu;
    }

    public Set<Membre> getMembres() {
        return membres;
    }

    public void setMembres(Set<Membre> membres) {
        this.membres = membres;
    }

    public int getNb_max_evenement() {
        return nb_max_evenement;
    }

    public void setNb_max_evenement(int nb_max_evenement) {
        this.nb_max_evenement = nb_max_evenement;
    }
}