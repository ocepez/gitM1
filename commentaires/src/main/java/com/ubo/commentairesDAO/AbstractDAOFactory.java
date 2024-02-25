package com.ubo.commentairesDAO;

public class AbstractDAOFactory {

    /**
     * Renvoie la fabrique concrète de DAO en fonction du support de persistance.
     *
     * @param type Le support de persistance à utiliser.
     * @return La fabrique de DAO pour ce type de persistance.
     */
  /*  public static LieuxDAOFactory getDAOFactory(PersistenceKind type) {
        if (type.equals(PersistenceKind.JPA)) {
            return new Lieux_JPA_DAOFactory();
        } else {
            return null;
        }
    }*/
}