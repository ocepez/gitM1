package com1.dtos;

import com1.entities.Evenement;
import com1.entities.Membre;

public class RequestBodyInscriptionEvenement {

    private int id_membre;
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
