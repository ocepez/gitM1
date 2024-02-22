package com1.services;

import com1.entities.Evenement;

import com1.entities.Membre;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Set;

public interface EvenementService {

    public List<Evenement> getAllEvenements();
    public ResponseEntity<String> setOneEvenement(Evenement evenement);
    public Evenement getOneEvenementById(int id);
    public ResponseEntity<String> setOneEvenementById(int id, Evenement evenement);
    public ResponseEntity<String> deleteOneEvenementById(int id);
    public Set<Membre> getListeMembreByIdEvenement(int id);



    }
