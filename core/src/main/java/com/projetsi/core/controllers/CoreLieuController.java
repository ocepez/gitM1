package com.projetsi.core.controllers;

import com.projetsi.core.services.CoreLieuService;
import com.projetsi.core.entities.Lieu;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Contrôleur principal de l'API Core pour la gestion des lieux.
 *
 * @author Maxime A.
 */
@RestController
@RequestMapping("/cors/lieux")
public class CoreLieuController {

    private final CoreLieuService coreLieuService;

    /**
     * Constructeur du contrôleur CoreLieuController.
     *
     * @param coreLieuService Le service CoreLieuService utilisé.
     */
    public CoreLieuController(CoreLieuService coreLieuService) {
        this.coreLieuService = coreLieuService;
    }

    /**
     * Obtient la liste de tous les lieux.
     *
     * @return La liste de tous les lieux.
     */
    @GetMapping
    public List<Lieu> getAllLieux() {
        return coreLieuService.getAllLieux();
    }

    /**
     * Obtient un lieu par son identifiant.
     *
     * @param id L'identifiant du lieu.
     * @return Le lieu correspondant à l'identifiant.
     */
    @GetMapping("/{id}")
    public Lieu getLieu(@PathVariable int id) {
        return coreLieuService.getLieuById(id);
    }

    /**
     * Crée un nouveau lieu.
     *
     * @param lieu Le lieu à créer.
     * @return Le lieu créé.
     */
    @PostMapping
    public Lieu createLieu(@RequestBody Lieu lieu) {
        return coreLieuService.createLieu(lieu);
    }

    /**
     * Met à jour un lieu existant.
     *
     * @param id L'identifiant du lieu à mettre à jour.
     * @param lieu Les nouvelles données du lieu.
     * @return Le lieu mis à jour.
     */
    @PutMapping("/{id}")
    public Lieu updateLieu(@PathVariable int id, @RequestBody Lieu lieu) throws Exception {
        Lieu lieuExist = coreLieuService.getLieuById(id);
        if (lieuExist != null) {
            lieuExist.setNom(lieu.getNom());
            lieuExist.setAdresse(lieu.getAdresse());
            lieuExist.setCapacite(lieu.getCapacite());

            return coreLieuService.updateLieu(id, lieuExist);
        } else {
            throw  new Exception("Lieu non trouvé avec l'ID : " + id);
        }
    }

    /**
     * Supprime un lieu.
     *
     * @param id L'identifiant du lieu à supprimer.
     * @return Un message de retour sur l'opération de suppression.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteLieu(@PathVariable int id) {
        try {
            coreLieuService.deleteLieu(id);
            return new ResponseEntity<>("Lieu supprimé avec succès", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Erreur lors de la suppression du lieu d'identifiant : " + id, HttpStatus.NOT_FOUND);
        }
    }
}
