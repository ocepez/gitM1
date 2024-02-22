package com.controllers;

import com.entities.Evenement;
import com.services.EvenementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;
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

    @PostMapping(consumes = "application/json;charset=UTF-8")
    @ResponseBody
    public ResponseEntity<String> setOneEvenement(@RequestBody Evenement evenement){
        return evenementService.setOneEvenement(evenement);
    }

    @GetMapping(value="/{id}")
    public Evenement getOneEvenementById(@PathVariable int id){

        return evenementService.getOneEvenementById(id);
    }

    @PutMapping(value="/{id}")
    public ResponseEntity<String> setOneEvenementById(@PathVariable int id, @RequestBody Evenement evenement){

        return evenementService.setOneEvenementById(id, evenement);
    }

    @DeleteMapping(value="/{id}")
    public ResponseEntity<String> deleteOneEvenementById(@PathVariable int id){

        return evenementService.deleteOneEvenementById(id);
    }

    @GetMapping(value="/{id}/membres")
    public ResponseEntity<String> getListeMembreByIdEvenement(@PathVariable int id){

        return evenementService.deleteOneEvenementById(id);
    }
}

