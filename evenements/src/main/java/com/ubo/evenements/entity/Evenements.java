package com.ubo.evenements.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.Date;

@Entity
@Data
@Table(name="evenement")
public class Evenements {

    @Id
    @GeneratedValue
    private int id_evenement;
    private String nom_evenement;
    private int duree_evenement;
    private Date date_evenement;
    private String desc_evenement;
    private String image_evenement;
    private int id_lieu;

}