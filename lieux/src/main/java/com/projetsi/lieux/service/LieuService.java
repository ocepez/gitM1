package com.projetsi.lieux.service;

import com.projetsi.lieux.LieuxDAO.DAOException;
import com.projetsi.lieux.LieuxDAO.DAO_JPA_Lieu;
import com.projetsi.lieux.entity.Lieu;

import java.util.List;

/**
 * Service pour la gestion des opérations CRUD sur l'entité Lieu.
 *
 * @author Maxime A.
 */
public class LieuService {

    private DAO_JPA_Lieu lieuDAO;

    /**
     * Constructeur qui initialise la DAO.
     */
    public LieuService() throws DAOException {
        this.lieuDAO = new DAO_JPA_Lieu();
    }

    /**
     * Récupére tous les lieux.
     *
     * @return Une liste de lieux.
     */
    public List<Lieu> getAllLieux() throws DAOException {
        return lieuDAO.getAll();
    }

    /**
     *  Récupère un lieu et toutes ses informations.
     *
     * @return Le lien recherché;
     */
    public Lieu getLieu(int id) throws DAOException {
        return lieuDAO.find(id);
    }

    /**
     * Crée un nouveau lieu.
     *
     * @param lieu Le lieu à créer.
     */
    public void createLieu(Lieu lieu) throws DAOException {
        lieuDAO.create(lieu);
    }

    /**
     * Met à jour un lieu existant.
     *
     * @param lieu Le lieu à mettre à jour.
     */
    public void updateLieu(Lieu lieu) throws DAOException {
        lieuDAO.update(lieu);
    }

    /**
     * Supprime un lieu existant.
     *
     * @param lieu Le lieu à supprimer.
     */
    public void deleteLieu(Lieu lieu) throws DAOException {
        lieuDAO.delete(lieu);
    }
}
