package com.dtos;

import javax.persistence.ForeignKey;

public class RequestBodyInscriptionEvenement {

    //@ForeignKey(name = "id_evenement")
    private int id_membre;
 // @ForeignKey(name = "id_evenement")
    private int id_evenement;

    public int getId_membre() {
        return id_membre;
    }

    public void setId_membre(int id_membre) {
        this.id_membre = id_membre;
    }

    public int getId_evenement() {
        return id_evenement;
    }

    public void setId_evenement(int id_evenement) {
        this.id_evenement = id_evenement;
    }
}
