package com.ubo.evenements.controller;

import com.ubo.evenements.entity.Evenements;
import com.ubo.evenements.entity.Lieux;
import com.ubo.evenements.service.EvenementService;
import com.ubo.evenements.service.EvenementServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EvenementController {


    @Autowired
    private EvenementService evenementService;
    @GetMapping(value = "/evenements")
    public List<Evenements> getAllEvenements() {

        return evenementService.getAllEvenements();
    }

    @PostMapping(value="/evenements")
    public ResponseEntity<String> setOneEvenement(){
        return evenementService.setOneEvenement();
    }

    @GetMapping(value="/evenements/{id}")
    public Evenements getOneEvenementById(@PathVariable int id){

        return evenementService.getOneEvenementById(id);
    }

    @PutMapping(value="/evenements/{id}")
    public ResponseEntity<String> setOneEvenementById(@PathVariable int id){

        return null;
    }

    @DeleteMapping(value="/evenements/{id}")
    public ResponseEntity<String> deleteOneEvenementById(@PathVariable int id){

        return null;
    }
}

