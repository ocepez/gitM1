package com.ubo.evenements.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="lieu")
public class Lieux {

    @Id
    @GeneratedValue
    private int id_lieu;
    private String nom_lieu;
    private String adresse_lieu;
    private int capacite_lieu;
}