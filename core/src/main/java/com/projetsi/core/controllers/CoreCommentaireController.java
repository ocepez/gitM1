package com.projetsi.core.controllers;

import com.projetsi.core.services.CoreCommentaireService;
import com.projetsi.core.entities.Commentaire;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Contrôleur principal de l'API Core pour la gestion des Commentaires.
 *
 * @author Maxime A.
 */
@RestController
@RequestMapping("/cors/commentaires")
public class CoreCommentaireController {

    private final CoreCommentaireService coreCommentaireService;

    /**
     * Constructeur du contrôleur CoreCommentaireController.
     *
     * @param coreCommentaireService Le service CoreCommentaireService utilisé.
     */
    public CoreCommentaireController(CoreCommentaireService coreCommentaireService) {
        this.coreCommentaireService = coreCommentaireService;
    }

    /**
     * Crée un nouveau Commentaire.
     *
     * @param Commentaire Le Commentaire à créer.
     * @return Le Commentaire créé.
     */
    @PostMapping
    public Commentaire createCommentaire(@RequestBody Commentaire Commentaire) {
        return coreCommentaireService.createCommentaire(Commentaire);
    }

    /**
     * Met à jour un Commentaire existant.
     *
     * @param id L'identifiant du Commentaire à mettre à jour.
     * @return Le Commentaire mis à jour.
     */
    @GetMapping("/Membre/{id}")
    public Commentaire getCommentaireByMembre(@PathVariable int id) throws Exception {
        Commentaire CommentaireExist = coreCommentaireService.getCommentaireById(id);
        if (CommentaireExist != null) {
            CommentaireExist.setContenu_commentaire(Commentair););
            CommentaireExist.setAdresse(Commentaire.getAdresse());
            CommentaireExist.setCapacite(Commentaire.getCapacite());

            return coreCommentaireService.updateCommentaire(id, CommentaireExist);
        } else {
            throw  new Exception("Commentaire non trouvé avec l'ID : " + id);
        }
    }

    /**
     * Supprime un Commentaire.
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
