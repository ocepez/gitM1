package com.projetsi.lieux.LieuxDAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * DAO abstrait et générique pour tout type de données.
 *
 * @author Maxime A.
 * @param <D> La classe paramétrant le DAO.
 */
public abstract class DAO<D> {

   static private EntityManager em = null;

   /**
     * Retourne à partir du support de persistance un objet en fonction de son identifiant.
     * @param id L'identifiant de l'objet.
     * @return L'instance de l'objet.
     * @throws DAOException En cas de problème.
    */
    public abstract D find(int id) throws DAOException;

    /**
     * Rend persistant un objet qui n'avait pas encore de représentation sur le support de persistance.
     * @param data L'objet à rendre persistant.
     * @throws DAOException En cas de problème.
     */
    public abstract void create(D data) throws DAOException;

    /**
     * Met à jour le contenu correspondant à l'objet sur le support persistant.
     *
     * @param data L'objet modifié dont le contenu est à mettre à jour.
     * @throws DAOException En cas de problème.
     */
    public abstract void update(D data) throws DAOException;

    /**
     * Efface du support persistant le contenu équivalent à l'objet.
     *
     * @param data l'objet à supprimer.
     * @throws DAOException en cas de problème.
     */
    public abstract void delete(D data) throws DAOException;
    
    /**
     * Construit le DAO pour la classe paramétrée.
     *
     * @throws DAOException En cas de problème.
     */
    public DAO() throws DAOException {}

    static public EntityManager getEntityManager() {
     if (em == null) {
      EntityManagerFactory emf = Persistence.createEntityManagerFactory("LieuxPU");
      em = emf.createEntityManager();
     }
     return em;
    }

    public void closeEntityManager() {
     if (em != null) {
      em.close();
      em = null;
     }
    }
}
