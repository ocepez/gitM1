package com1.dtos;


import lombok.Data;

@Data
public class LieuxDto {

    private int id_lieu;
    private String nom_lieu;
    private String adresse_lieu;
    private int capacite_lieu;
}