package com.projetsi.lieux.LieuxDAO;

import com.projetsi.lieux.entity.Lieu;

/**
 * Fabrique concrte de DAO pour le schéma relationnel sports avec une implémentation en JDBC.
 *
 * @author Maxime A.
 */
public class Lieux_JPA_DAOFactory extends LieuxDAOFactory {
    
	/**
	 * Le DAO concret en JDBC pour la table Lieu.
	 */
    private DAO_JPA_Lieu daoLieu = null;

    @Override
    public DAO<Lieu> getDAOLieu() throws DAOException {
        if (daoLieu == null) daoLieu = new DAO_JPA_Lieu();
        return daoLieu;
    }
}
