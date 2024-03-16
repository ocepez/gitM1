package comm.entities;



import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="membre")
public class Membre {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id_membre;
    private String email_membre;
    private String nom_membre;
    private String prenom_membre;
    private int age_membre;
    private String adresse_membre;
    private String photo_membre;


    @ManyToMany(mappedBy = "membres")
    @JsonBackReference
  //  @JsonIgnore
    private Set<Evenement> evenements = new HashSet<>();


    public int getId_membre() {
        return id_membre;
    }

    public void setId_membre(int id_membre) {
        this.id_membre = id_membre;
    }

    public String getEmail_membre() {
        return email_membre;
    }

    public void setEmail_membre(String email_membre) {
        this.email_membre = email_membre;
    }

    public String getNom_membre() {
        return nom_membre;
    }

    public void setNom_membre(String nom_membre) {
        this.nom_membre = nom_membre;
    }

    public String getPrenom_membre() {
        return prenom_membre;
    }

    public void setPrenom_membre(String prenom_membre) {
        this.prenom_membre = prenom_membre;
    }

    public int getAge_membre() {
        return age_membre;
    }

    public void setAge_membre(int age_membre) {
        this.age_membre = age_membre;
    }

    public String getAdresse_membre() {
        return adresse_membre;
    }

    public void setAdresse_membre(String adresse_membre) {
        this.adresse_membre = adresse_membre;
    }

    public String getPhoto_membre() {
        return photo_membre;
    }

    public void setPhoto_membre(String photo_membre) {
        this.photo_membre = photo_membre;
    }

    public Set<Evenement> getEvenements() {
        return evenements;
    }

    public void setEvenements(Set<Evenement> evenements) {
        this.evenements = evenements;
    }
}