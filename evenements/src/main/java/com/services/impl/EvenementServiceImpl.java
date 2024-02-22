package com.services.impl;
import com.dtos.EvenementsDto;
import com.entities.Evenement;
import com.entities.Membre;
import com.repositories.EvenementRepository;
import com.services.EvenementService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service("EvenementService")
public class EvenementServiceImpl implements EvenementService {

    private final EvenementRepository evenementRepository;

    public EvenementServiceImpl(EvenementRepository evenementRepository) {
        this.evenementRepository = evenementRepository;
    }
    private Evenement EvenementToEntity(EvenementsDto evenementsDto){
        Evenement evenement = new Evenement();
      //  evenement.
        evenement.setDate_evenement(evenementsDto.getDate_evenement());
        evenement.setDuree_evenement(evenementsDto.getDuree_evenement());
        evenement.setDesc_evenement(evenementsDto.getDesc_evenement());
        evenement.setImage_evenement(evenementsDto.getImage_evenement());
        evenement.setId_lieu(evenementsDto.getId_lieu());

        return evenement;
    }
    private EvenementsDto EvenementEntityToDto(Evenement evenement){
        EvenementsDto evenementsDto = new EvenementsDto();
        evenementsDto.setDate_evenement(evenement.getDate_evenement());
        evenementsDto.setDesc_evenement(evenement.getDesc_evenement());
        evenementsDto.setNom_evenement(evenement.getNom_evenement());
         evenementsDto.setDuree_evenement(evenement.getDuree_evenement());
        evenementsDto.setImage_evenement(evenementsDto.getImage_evenement());
        return evenementsDto;
    }
    @Override
    public List<Evenement> getAllEvenements() {
        List<Evenement> evenements = new ArrayList<>();
        Iterable<Evenement> evenements1 = evenementRepository.findAll();
        evenements1.forEach(evenements::add);
        return evenements;
    }

    @Override
    public ResponseEntity<String> setOneEvenement(Evenement evenement) {
        evenementRepository.save(evenement);
        return new ResponseEntity<String>("L'evenement a été enregistré", HttpStatus.OK);
    }

    @Override
    public Evenement getOneEvenementById(int id) {
        Optional<Evenement> tt  = evenementRepository.findById(id);
        return tt.get();
    }

    @Override
    public ResponseEntity<String> setOneEvenementById(int id, Evenement evenement) {
        Evenement evenement1= evenementRepository.getById(id);
        evenement1.setImage_evenement(evenement.getImage_evenement());
        evenement1.setDesc_evenement(evenement.getDesc_evenement());
        evenement1.setDate_evenement(evenement.getDate_evenement());
        evenement1.setDuree_evenement(evenement.getDuree_evenement());
        evenement1.setNom_evenement(evenement.getNom_evenement());
        evenementRepository.save(evenement1);
        return new ResponseEntity<String>("Mise à Jour Evenement", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> deleteOneEvenementById(int id) {
        evenementRepository.deleteById(id);
        return new ResponseEntity<>("Suppression de l'evenement", HttpStatus.OK);
    }

    @Override
    public Set<Membre> getListeMembreByIdEvenement(int id) {
        Evenement evenement = evenementRepository.getById(id);
        return evenement.getMembres();
    }
}
