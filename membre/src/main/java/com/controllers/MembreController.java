package com.controllers;

import com.dtos.MembreDto;
import com.services.impl.MembreServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Contrôleur REST pour la gestion des membres.
 *
 * @author Xavier F.
 */
@RestController
@RequestMapping("/membres")
public class MembreController {

    private final MembreServiceImpl membreService;

    /**
     * Constructeur du contrôleur avec injection de dépendance du service de gestion des membres.
     *
     * @param membreService Le service de gestion des membres à injecter.
     */
    public MembreController(MembreServiceImpl membreService) {
        this.membreService = membreService;
    }

    /**
     * Endpoint pour récupérer la liste de tous les membres.
     *
     * @return La liste des membres sous forme de {@code List<MembreDto>}.
     */
    @GetMapping
    public List<MembreDto> getMembers() {
        return membreService.getAllMembres();
    }

    /**
     * Endpoint pour récupérer un membre par son identifiant.
     *
     * @param id L'identifiant du membre à récupérer.
     * @return Le membre correspondant sous forme de {@code MembreDto}.
     */
    @GetMapping("/{id}")
    public MembreDto getMembre(@PathVariable Integer id) {
        return membreService.getMembreById(id);
    }

    /**
     * Endpoint pour supprimer un membre par son identifiant.
     *
     * @param id L'identifiant du membre à supprimer.
     * @return {@code true} si la suppression a réussi, sinon {@code false}.
     */
    @DeleteMapping("/{id}")
    public Boolean deleteMembre(@PathVariable Integer id) {
        return membreService.deleteMembre(id);
    }

    /**
     * Endpoint pour enregistrer un nouveau membre.
     *
     * @param membreDto Le DTO du membre à enregistrer.
     * @return Le membre enregistré sous forme de {@code MembreDto}.
     */
    @PostMapping
    public MembreDto saveMembre(final @RequestBody MembreDto membreDto) {
        System.out.println(membreDto);
        return membreService.saveMembre(membreDto);
    }

    /**
     * Endpoint pour mettre à jour les informations d'un membre existant.
     *
     * @param id        L'identifiant du membre à mettre à jour.
     * @param membreDTO Le DTO contenant les nouvelles informations du membre.
     * @return {@code true} si la mise à jour a réussi, sinon {@code false}.
     */
    @PutMapping("/{id}")
    public boolean updateMembre(@PathVariable Integer id, @RequestBody MembreDto membreDTO) {
        System.out.print(membreDTO);
        System.out.print(id);
        membreService.updateMembre(id, membreDTO);

        return true;
    }
}
