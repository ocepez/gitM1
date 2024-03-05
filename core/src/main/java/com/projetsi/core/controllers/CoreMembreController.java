package com.projetsi.core.controllers;

import com.projetsi.core.entities.Commentaire;
import com.projetsi.core.services.CoreCommentaireService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Contrôleur principal de l'API Core pour la gestion des Commentaires.
 *
 * @author Oceane P.
 */
@RestController
@RequestMapping("/core/membres")
public class CoreMembreController {

    private final CoreCommentaireService coreCommentaireService;

    /**
     * Constructeur du contrôleur CoreCommentaireController.
     *
     * @param coreCommentaireService Le service CoreCommentaireService utilisé.
     */
    public CoreMembreController(CoreCommentaireService coreCommentaireService) {
        this.coreCommentaireService = coreCommentaireService;
    }

    /**
     * Crée un nouveau commentaire.
     *
     * @param Commentaire Le commentaire à créer.
     * @return Le Commentaire créé.
     */
    @PostMapping
    public ResponseEntity<String> createCommentaire(@RequestBody Commentaire Commentaire) {

        try {
            coreCommentaireService.createCommentaire(Commentaire);
            return new ResponseEntity<>("Commentaire crée avec succès", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Erreur lors de la création du commentaire  ", HttpStatus.NOT_FOUND);
        }

    }

    /**
     * Récupère la liste d'un commentaire d'un membre
     *
     * @param id L'identifiant du membre.
     * @return Le Commentaire mis à jour.
     */
    @GetMapping("/membre/{id}")
    public List<Commentaire>  getCommentaireByMembre(@PathVariable int id) throws Exception {
        List<Commentaire> CommentaireExist = coreCommentaireService.getAllCommentaireByEvenement(id);
        if (CommentaireExist != null) {

            return CommentaireExist;
        } else {
            throw  new Exception("La liste est indisponible avec l'id d'événement donné : " + id);
        }
    }

    /**
     * Récupère la liste d'un commentaire d'un événement
     *
     * @param id L'identifiant du Commentaire à mettre à jour.
     * @return Le Commentaire mis à jour.
     */
    @GetMapping("/evenement/{id}")
    public List<Commentaire>  getCommentaireByEvenement(@PathVariable int id) throws Exception {
        List<Commentaire> CommentaireExist = coreCommentaireService.getAllCommentaireByMembre(id);
        if (CommentaireExist != null) {

            return CommentaireExist;
        } else {
            throw  new Exception("La liste est indisponible avec l'id donné : " + id);
        }
    }
    /**
     * Supprime un commentaire.
     *
     * @param id L'identifiant du Commentaire à supprimer.
     * @return Un message de retour sur l'opération de suppression.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCommentaire(@PathVariable int id) {
        try {
            coreCommentaireService.deleteCommentaire(id);
            return new ResponseEntity<>("Commentaire supprimé avec succès", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Erreur lors de la suppression du Commentaire d'identifiant : " + id, HttpStatus.NOT_FOUND);
        }
    }
}
