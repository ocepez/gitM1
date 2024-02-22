package com.services;

import com.dtos.RequestBodyInscriptionEvenement;
import com.entities.Evenement;
import com.entities.Membre;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Set;

public interface EvenementInscriptionService {

    public ResponseEntity<String> postInscriptionAEvenement(RequestBodyInscriptionEvenement inscriptionEvenement);

    public List<Evenement> getListInscriptionAMembre(int id);

    public List<Membre> getListInscriptionAEvenement(int id);

    public ResponseEntity<String> deleteMembreAEvenement(int id_membre, int id_evenement);

}
