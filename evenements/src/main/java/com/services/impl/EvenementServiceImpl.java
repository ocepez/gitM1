package com.services.impl;
import com.dtos.DogDto;
import com.dtos.EvenementsDto;
import com.entities.Dog;
import com.entities.Evenement;
import com.repositories.EvenementRepository;
import com.services.EvenementService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
    public ResponseEntity<String> setOneEvenement() {
        return null;
    }

    @Override
    public Evenement getOneEvenementById(int id) {
        return null;
    }

    @Override
    public ResponseEntity<String> setOneEvenementById(int id) {
        return null;
    }

    @Override
    public ResponseEntity<String> deleteOneEvenementById(int id) {
        return null;
    }
}
