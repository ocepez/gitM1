package com.ubo.commentairesDAO;

import com.ubo.entity.Commentaire;
import com.ubo.entity.Lieu;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

public class DAO_JPA_Commentaires extends DAO_JPA<Commentaire> {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("LieuxPU");
    private EntityManager em;

    /**
     * Récupère la liste de tous les lieux à partir de la base de données.
     *
     * @return Une liste de Lieu contenant tous les lieux.
     * @throws DAOException En cas d'erreur lors de la récupération des lieux.
     */
    public List<Commentaire> getAll() throws DAOException {
        List<Commentaire> com = new ArrayList<>();
        try {
            // Utiliser EntityManager pour créer une requête JPA
            TypedQuery<Commentaire> query = em.createQuery("SELECT l FROM com l", Commentaire.class);
            com = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("Erreur lors de la récupération de tous les lieux");
        }
        return com;
    }


    @Override
    public Commentaire find(int id) throws DAOException {
        Commentaire com = DAO.getEntityManager().find(Commentaire.class, id);
        if (com != null) {
            return com;
        } else {
            throw new DAOException("Le lieu d'identifiant " + id + " n'existe pas");
        }
    }

    @Override
    public void create(Commentaire data) throws DAOException {
        EntityTransaction t = null;
        try {
      //      t = DAO.getEntityManager().getTransaction();
            t.begin();
            DAO.getEntityManager().persist(data);
            t.commit();
        } catch (Exception e) {
            if (t != null) t.rollback();
        }
    }

    @Override
    public void update(Commentaire data) throws DAOException {
        EntityTransaction t = null;
        try {
     //       t = DAO.getEntityManager().getTransaction();
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
    public void delete(Commentaire data) throws DAOException {

    }



    /**
     * Constructeur qui initialise l'EntityManager.
     */
    public DAO_JPA_Commentaires() throws DAOException {
        super();
        this.em =emf.createEntityManager();

        EntityTransaction t = null;
        try {
            t = (EntityTransaction) DAO.getEntityManager().getTransaction();
            t.begin();
         //   DAO.getEntityManager().remove(data);
            t.commit();
        } catch (Exception e) {
            if (t != null) t.rollback();
        }
    }
}
