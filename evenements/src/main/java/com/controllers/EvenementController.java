package com.controllers;

import com.entities.Evenement;
import com.repositories.ChatRepository;
import com.services.EvenementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/evenements")
public class EvenementController {

    @Autowired
    private EvenementService evenementService;

    private final ChatRepository chatRepository;

    public ChatServiceImpl(ChatRepository chatRepository){
        this.chatRepository=chatRepository;
    }


    @GetMapping()
    public List<Evenement> getAllEvenements() {

        return evenementService.getAllEvenements();
    }

    @PostMapping()
    public ResponseEntity<String> setOneEvenement(){
        return evenementService.setOneEvenement();
    }

    @GetMapping(value="/evenements/{id}")
    public Evenement getOneEvenementById(@PathVariable int id){

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

