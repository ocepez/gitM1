package com.projetsi.lieux.LieuxDAO;

/**
 * Exception spécifique aux problèmes d'accés aux données via un DAO.
 *
 * @author Maxime A.
 */
public class DAOException extends java.lang.Exception {
    
	private static final long serialVersionUID = 309200220164832142L;

	public DAOException() {
        super();
    }
    
    public DAOException(String message) {
        super(message);
    }
}
