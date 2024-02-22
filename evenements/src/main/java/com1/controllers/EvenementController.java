package com1.controllers;

import com1.entities.Evenement;
import com1.repositories.EvenementRepository;
import com1.services.EvenementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/evenements")
public class EvenementController {

    @Autowired
    private EvenementService evenementService;


    public EvenementController(EvenementService evenementService){
        this.evenementService=evenementService;
    }


    @GetMapping()
    public List<Evenement> getAllEvenements() {
        return evenementService.getAllEvenements();
    }

    @PostMapping()
    public ResponseEntity<String> setOneEvenement(@RequestBody Evenement evenement){

        return evenementService.setOneEvenement(evenement);
    }

    @GetMapping(value="/evenements/{id}")
    public Evenement getOneEvenementById(@PathVariable int id){

        return evenementService.getOneEvenementById(id);
    }

    @PutMapping(value="/evenements/{id}")
    public ResponseEntity<String> setOneEvenementById(@PathVariable int id, @RequestBody Evenement evenement){

        return evenementService.setOneEvenementById(id, evenement);
    }

    @DeleteMapping(value="/evenements/{id}")
    public ResponseEntity<String> deleteOneEvenementById(@PathVariable int id){

        return evenementService.deleteOneEvenementById(id);
    }

    @GetMapping(value="/evenements/{id}/membres")
    public ResponseEntity<String> getListeMembreByIdEvenement(@PathVariable int id){

        return evenementService.deleteOneEvenementById(id);
    }
}

