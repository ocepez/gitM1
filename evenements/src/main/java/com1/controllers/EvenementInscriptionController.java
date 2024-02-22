package com1.controllers;


import com1.dtos.RequestBodyInscriptionEvenement;
import com1.entities.Evenement;
import com1.entities.Membre;
import com1.services.EvenementInscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/evenements/inscription")
public class EvenementInscriptionController {


    @Autowired
    EvenementInscriptionService evenementInscriptionService;


    public EvenementInscriptionController(EvenementInscriptionService evenementInscriptionService){
        this.evenementInscriptionService=evenementInscriptionService;
    }

    @PostMapping
    @Description("Inscrit un membre à un événement")
    public ResponseEntity<String> postInscriptionAEvenement(@RequestBody RequestBodyInscriptionEvenement requestBodyInscriptionEvenement){

        return this.evenementInscriptionService.postInscriptionAEvenement(requestBodyInscriptionEvenement);
    }

    @GetMapping(value="/membre/{id}")
    @Description("Recupère la liste des inscriptions d'un membre")
    public Set<Evenement> getListInscriptionAMembre(@PathVariable int id){

        return evenementInscriptionService.getListInscriptionAMembre(id);
    }

    @GetMapping(value="/evenement/{id}")
    @Description("Recupère la liste des inscriptions à un événement")
    public Set<Membre> getListInscriptionAEvenement(@PathVariable int id){

        return evenementInscriptionService.getListInscriptionAEvenement(id);
    }

  @GetMapping(value = "/{id_membre}/{id_evenement}")
  @Description("Recupère la liste des inscriptions à un événement")
  public ResponseEntity<String> deleteMembreAEvenement(@PathVariable int id_membre, @PathVariable int id_evenement) {

        return evenementInscriptionService.deleteMembreAEvenement(id_membre,id_evenement);
    }

}
