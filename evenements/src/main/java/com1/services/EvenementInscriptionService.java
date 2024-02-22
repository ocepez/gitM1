package com1.services;

import com1.dtos.RequestBodyInscriptionEvenement;
import com1.entities.Evenement;
import com1.entities.Membre;
import org.springframework.http.ResponseEntity;

import java.util.Set;

public interface EvenementInscriptionService {

    public ResponseEntity<String> postInscriptionAEvenement(RequestBodyInscriptionEvenement inscriptionEvenement);

    public Set<Evenement> getListInscriptionAMembre(int id);

    public Set<Membre> getListInscriptionAEvenement(int id);

    public ResponseEntity<String> deleteMembreAEvenement(int id_membre, int id_evenement);

}
