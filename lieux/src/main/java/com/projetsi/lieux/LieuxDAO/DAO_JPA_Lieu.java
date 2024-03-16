package com.projetsi.lieux.LieuxDAO;

import com.projetsi.lieux.entity.Lieu;
import javax.persistence.*;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO pour la classe/table Lieu avec implémentation en JPA.
 *
 * @author Maxime A.
 */
public class DAO_JPA_Lieu extends DAO<Lieu> {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("LieuxPU");
    private EntityManager em;

    /**
     * Récupère la liste de tous les lieux à partir de la base de données.
     *
     * @return Une liste de Lieu contenant tous les lieux.
     * @throws DAOException En cas d'erreur lors de la récupération des lieux.
     */
    public List<Lieu> getAll() throws DAOException {
        List<Lieu> lieux = new ArrayList<>();
        try {
            // Utiliser EntityManager pour créer une requête JPA
            TypedQuery<Lieu> query = em.createQuery("SELECT l FROM Lieu l", Lieu.class);
            lieux = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("Erreur lors de la récupération de tous les lieux");
        }
        return lieux;
    }


    @Override
    public Lieu find(int id) throws DAOException {
        Lieu lieu = DAO.getEntityManager().find(Lieu.class, id);
        if (lieu != null) {
            return lieu;
        } else {
            throw new DAOException("Le lieu d'identifiant " + id + " n'existe pas");
        }
    }

    @Override
    public void create(Lieu data) throws DAOException {
        EntityTransaction t = null;
        try {
            t = DAO.getEntityManager().getTransaction();
            t.begin();
            DAO.getEntityManager().persist(data);
            t.commit();
        } catch (Exception e) {
            if (t != null) t.rollback();
        }
    }

    @Override
    public void update(Lieu data) throws DAOException {
        EntityTransaction t = null;
        try {
            t = DAO.getEntityManager().getTransaction();
            t.begin();
            DAO.getEntityManager().merge(data);
            // Forcer l'exécution des requêtes avant de valider la transaction.
            DAO.getEntityManager().flush();
            t.commit();
        } catch (Exception e) {
            if (t != null) t.rollback();
        }
    }

    @Override
    public void delete(Lieu data) throws DAOException {
        EntityTransaction t = null;
        try {
            t = DAO.getEntityManager().getTransaction();
            t.begin();
            DAO.getEntityManager().remove(data);
            t.commit();
        } catch (Exception e) {
            if (t != null) t.rollback();
        }
    }

    /**
     * Constructeur qui initialise l'EntityManager.
     */
    public DAO_JPA_Lieu() throws DAOException {
    	super();
        this.em =emf.createEntityManager();
    }
}
