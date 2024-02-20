package com.ubo.evenements.service;

import com.ubo.evenements.controller.EvenementController;
import com.ubo.evenements.entity.Evenements;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

public interface EvenementService {

    public List<Evenements> getAllEvenements();
    public ResponseEntity<String> setOneEvenement(int id);
    public Evenements getOneEvenementById(int id);
    public ResponseEntity<String> setOneEvenementById(int id);
    public ResponseEntity<String> deleteOneEvenementById(int id);



}
