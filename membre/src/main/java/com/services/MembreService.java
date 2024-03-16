package com.services;

import com.dtos.MembreDto;

import java.util.List;

/**
 * Interface définissant les services liés à la gestion des membres.
 *
 * @author Xavier F.
 */
public interface MembreService {

    /**
     * Enregistre un nouveau membre.
     *
     * @param membreDto Le DTO du membre à enregistrer.
     * @return Le DTO du membre enregistré.
     */
    MembreDto saveMembre(MembreDto membreDto);

    /**
     * Récupère un membre par son identifiant.
     *
     * @param membreId L'identifiant du membre à récupérer.
     * @return Le DTO du membre correspondant à l'identifiant.
     */
    MembreDto getMembreById(Integer membreId);

    /**
     * Récupère la liste de tous les membres.
     *
     * @return La liste des DTO de tous les membres.
     */
    List<MembreDto> getAllMembres();

    /**
     * Supprime un membre par son identifiant.
     *
     * @param membreId L'identifiant du membre à supprimer.
     * @return True si la suppression est réussie, sinon False.
     */
    boolean deleteMembre(Integer membreId);
}
