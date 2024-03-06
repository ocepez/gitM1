package com.projetsi.core.controllers;

import com.projetsi.core.entities.Membre;
import com.projetsi.core.services.CoreMembreService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Contrôleur principal de l'API Core pour la gestion des Membres.
 *
 * @author Oceane P.
 */
@RestController
@RequestMapping("/core/membres")
public class CoreMembreController {

    private final CoreMembreService coreMembreService;

    /**
     * Constructeur du contrôleur CoreMembreController.
     *
     * @param coreMembreService Le service CoreMembreService utilisé.
     */
    public CoreMembreController(CoreMembreService coreMembreService) {
        this.coreMembreService = coreMembreService;
    }

    /**
     * Crée un nouveau Membre.
     *
     * @param Membre Le Membre à créer.
     * @return Le Membre créé.
     */
    @PostMapping
    public ResponseEntity<String> connexion(@RequestBody Membre Membre) {

        try {
            coreMembreService.createMembre(Membre);
            return new ResponseEntity<>("Membre crée avec succès", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Erreur lors de la création du Membre  ", HttpStatus.NOT_FOUND);
        }

    }
    /**
     * Crée un nouveau Membre.
     *
     * @param Membre Le Membre à créer.
     * @return Le Membre créé.
     */
    @PostMapping
    public ResponseEntity<String> createMembre(@RequestBody Membre Membre) {

        try {
            coreMembreService.createMembre(Membre);
            return new ResponseEntity<>("Membre crée avec succès", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Erreur lors de la création du Membre  ", HttpStatus.NOT_FOUND);
        }

    }

    /**
     * Récupère la liste d'un Membre d'un membre
     *
     * @param id L'identifiant du membre.
     * @return Le Membre mis à jour.
     */
    @GetMapping("/membre/{id}")
    public List<Membre>  getMembreById(@PathVariable int id) throws Exception {
        List<Membre> MembreExist = coreMembreService.getMembreById(id);
        if (MembreExist != null) {

            return MembreExist;
        } else {
            throw  new Exception("La liste est indisponible avec l'id d'événement donné : " + id);
        }
    }

    /**
     * Récupère la liste d'un Membre d'un événement
     *
     * @param id L'identifiant du Membre à mettre à jour.
     * @return Le Membre mis à jour.
     */
    @GetMapping("/evenement/{id}")
    public List<Membre>  getMembreByEvenement(@PathVariable int id) throws Exception {
        List<Membre> MembreExist = coreMembreService.getListEvenementsByMembre(id);
        if (MembreExist != null) {

            return MembreExist;
        } else {
            throw  new Exception("La liste est indisponible avec l'id donné : " + id);
        }
    }
    /**
     * Supprime un Membre.
     *
     * @param id L'identifiant du Membre à supprimer.
     * @return Un message de retour sur l'opération de suppression.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMembre(@PathVariable int id) {
        try {
            coreMembreService.deleteMembre(id);
            return new ResponseEntity<>("Membre supprimé avec succès", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Erreur lors de la suppression du Membre d'identifiant : " + id, HttpStatus.NOT_FOUND);
        }
    }
}
