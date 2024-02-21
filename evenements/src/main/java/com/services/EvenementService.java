package com.services;

import com.entities.Evenement;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EvenementService {

    public List<Evenement> getAllEvenements();
    public ResponseEntity<String> setOneEvenement();
    public Evenement getOneEvenementById(int id);
    public ResponseEntity<String> setOneEvenementById(int id);
    public ResponseEntity<String> deleteOneEvenementById(int id);



}
