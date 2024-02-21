package com.dtos;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data

public class LieuxDto {

    private int id_lieu;
    private String nom_lieu;
    private String adresse_lieu;
    private int capacite_lieu;
}