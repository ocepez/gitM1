package com.projetsi.lieux.LieuxDAO;

import com.projetsi.lieux.entity.Lieu;

/**
 * Fabrique abstraite de DAO pour le schéma sports.
 *
 * @author Maxime A.
 */
public abstract class LieuxDAOFactory {
    
    /**
     * @return Le DAO pour la classe/table Lieu.
     * @throws DAOException en cas de problème
     */
    public abstract DAO<Lieu> getDAOLieu() throws DAOException;

}
