package com1.entities;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Lieu {

    @Id
    @GeneratedValue
    private int id_lieu;
    private String nom_lieu;
    private String adresse_lieu;
    private int capacite_lieu;
}