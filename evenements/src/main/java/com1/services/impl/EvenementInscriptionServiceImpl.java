package com1.services.impl;

import com1.dtos.RequestBodyInscriptionEvenement;
import com1.entities.Evenement;
import com1.entities.Membre;
import com1.repositories.EvenementRepository;
import com1.repositories.MembreRepository;
import com1.services.EvenementInscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Set;


@Service("EvenementInscription")
public class EvenementInscriptionServiceImpl implements EvenementInscriptionService {

    @Autowired
    private final EvenementRepository evenementRepository;
    @Autowired
    private final MembreRepository membreRepository;

    public EvenementInscriptionServiceImpl(EvenementRepository evenementRepository, MembreRepository membreRepository) {
        this.evenementRepository = evenementRepository;
        this.membreRepository = membreRepository;
    }
    @Override
    public ResponseEntity<String> postInscriptionAEvenement(RequestBodyInscriptionEvenement inscriptionEvenement) {


        Membre membre = this.membreRepository.getById(inscriptionEvenement.getId_membre());
        Evenement evenement = this.evenementRepository.getById(inscriptionEvenement.getId_evenement());
        if(!membre.getEvenements().contains(evenement)){
            membre.getEvenements().add(evenement);
        }else {
            return new ResponseEntity<String>("Le membre a déjà été inscrit à l'événement", HttpStatus.MULTI_STATUS);

        }
        if(!evenement.getMembres().contains(membre)){
            evenement.getMembres().add(membre);
        }else {
            return new ResponseEntity<String>("L'événement contient déjà le membre", HttpStatus.MULTI_STATUS);
        }
        this.membreRepository.save(membre);
        this.evenementRepository.save(evenement);
        return new ResponseEntity<String>("Le membre a été inscrit à l'événement", HttpStatus.OK);
    }

    @Override
    public Set<Evenement> getListInscriptionAMembre(int id) {

        Membre membres = this.membreRepository.getById(id);
        return membres.getEvenements();
    }

    @Override
    public Set<Membre> getListInscriptionAEvenement(int id) {

        Evenement evenement = this.evenementRepository.getById(id);
        return evenement.getMembres();
    }

    @Override
    public ResponseEntity<String> deleteMembreAEvenement(int id_membre, int id_evenement) {

        Evenement evenement = evenementRepository.getById(id_evenement);
        Membre membre = membreRepository.getById(id_membre);
        if(membre.getEvenements().contains(evenement)){
            membre.getEvenements().remove(evenement);
        }else {
            return new ResponseEntity<String>("Le membre n'était pas inscrit à l'événement", HttpStatus.MULTI_STATUS);
        }
        if(evenement.getMembres().contains(membre)){
            evenement.getMembres().remove(membre);
        }else {
            return new ResponseEntity<String>("L'événement ne contenait pas le membre", HttpStatus.MULTI_STATUS);
        }
        return new ResponseEntity<String>("Le membre a été supprimé de l'événement", HttpStatus.OK);
    }
}
