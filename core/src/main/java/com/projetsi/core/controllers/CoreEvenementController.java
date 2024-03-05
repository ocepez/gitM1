package com.projetsi.core.controllers;

import com.projetsi.core.services.CoreEvenementService;
import com.projetsi.core.entities.Evenement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Contrôleur principal de l'API Core pour la gestion des evenements.
 *
 * @author Oceane P.
 */
@RestController
@RequestMapping("/cors/evenements")
public class CoreEvenementController {

    private final CoreEvenementService coreEvenementService;

    /**
     * Constructeur du contrôleur CoreEvenementController.
     *
     * @param coreEvenementService Le service CoreEvenementService utilisé.
     */
    public CoreEvenementController(CoreEvenementService coreEvenementService) {
        this.coreEvenementService = coreEvenementService;
    }

    /**
     * Obtient la liste de tous les evenements.
     *
     * @return La liste de tous les evenements.
     */
    @GetMapping
    public List<Evenement> getAllevenements() {
        return coreEvenementService.getAllEvenement();
    }

    /**
     * Crée un nouveau Evenement.
     *
     * @param Evenement Le Evenement à créer.
     * @return Le Evenement créé.
     */
    @PostMapping
    public Evenement createEvenement(@RequestBody Evenement Evenement) {
        return coreEvenementService.createEvenement(Evenement);
    }

    /**
     * Obtient un Evenement par son identifiant.
     *
     * @param id L'identifiant du Evenement.
     * @return Le Evenement correspondant à l'identifiant.
     */
    @GetMapping("/{id}")
    public Evenement getEvenement(@PathVariable int id) {
        return coreEvenementService.getEvenementById(id);
    }


    /**
     * Met à jour un Evenement existant.
     *
     * @param id L'identifiant du Evenement à mettre à jour.
     * @param Evenement Les nouvelles données du Evenement.
     * @return Le Evenement mis à jour.
     */
    @PutMapping("/{id}")
    public Evenement updateEvenement(@PathVariable int id, @RequestBody Evenement Evenement) {
            return coreEvenementService.updateEvenement(id, Evenement);
    }

    /**
     * Supprime un Evenement.
     *
     * @param id L'identifiant du Evenement à supprimer.
     * @return Un message de retour sur l'opération de suppression.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEvenement(@PathVariable int id) {
        try {
            coreEvenementService.deleteEvenement(id);
            return new ResponseEntity<>("Evenement supprimé avec succès", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Erreur lors de la suppression du Evenement d'identifiant : " + id, HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Obtient un Evenement par son identifiant.
     *
     * @param id L'identifiant du Evenement.
     * @return Le Evenement correspondant à l'identifiant.
     */
    @GetMapping("/{id}/membres")
    public Evenement getListeByEvenement(@PathVariable int id) {
        return coreEvenementService.getListMembreByEvenement(id);
    }

    /**
     * Inscrit un membre à un événement
     * @param id
     * @return
     */
    @PostMapping("/inscriptions")
    public Evenement postInscription(@RequestBody int id) {
        return coreEvenementService.postInscription(id);
    }

    /**
     * Récupère la liste d'inscription d'un membre
     * @param id
     * @return
     */
    @GetMapping("/inscriptions/membre/{id}")
    public Evenement getInscriptionByEvenement(@RequestBody int id) {
        return coreEvenementService.getListInscriptionsByMembre(id);
    }


    /**
     * Récupère la liste d'inscription à un événement
     * @param id
     * @return
     */
    @PostMapping("/inscriptions/evenement/{id}")
    public Evenement getInscriptionByMembre(@RequestBody int id) {
        return coreEvenementService.getListInscriptionsByEvenement(id);
    }

    /**
     * Supprime une inscription pour un membre à un evenement
     * @param id_membre
     * @param  id_evenement
     * @return
     */
    @PostMapping("/inscriptions/{id_membre}/{id_evenement}")
    public Evenement getInscriptionByMembre(@RequestParam int id_membre, @RequestParam int id_evenement ) {
        return coreEvenementService.getEvenementById(id_evenement);
    }
}
