package com.ubo.commentairesDAO;

/**
 * DAO abstrait et générique pour tout type de données.
 *
 * @param <D> la classe paramètrant le DAO
 * @author Eric
 */
public abstract class DAO_JPA<D> {

    /**
     * Construit le DAO pour la classe paramétrée.
     *
     * @throws DAOException en cas de problème
     */
    public DAO_JPA() throws DAOException {
    }

    /**
     * Retourne à partir du support de persistance un objet en fonction de son identifiant.
     *
     * @param id identifiant de l'objet
     * @return l'instance de l'objet
     * @throws DAOException en cas de problème
     */
    public abstract D find(int id) throws DAOException;

    /**
     * Rend persistant un objet qui n'avait pas encore de réprésentation sur
     * le support de persistance.
     *
     * @param data l'objet à rendre persistant
     * @throws DAOException en cas de problème
     */
    public abstract void create(D data) throws DAOException;

    /**
     * Met à jour le contenu correspondant à l'objet sur le support persistant (l'objet
     * avait déjà  une représentation sur le support persistant).
     *
     * @param data l'objet modifié dont le contenu est à mettre à jour
     * @throws DAOException en cas de problème
     */
    public abstract void update(D data) throws DAOException;

    /**
     * Efface du support persistant le contenu équivalent à l'objet.
     *
     * @param data l'objet à supprimer
     * @throws DAOException en cas de problème
     */
    public abstract void delete(D data) throws DAOException;
}