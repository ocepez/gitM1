package comm.services.impl;

import comm.dtos.RequestBodyInscriptionEvenement;
import comm.entities.Evenement;
import comm.entities.Membre;
import comm.exceptions.IllegalArgument;
import comm.exceptions.NotFound;
import comm.repositories.EvenementRepository;
import comm.repositories.MembreRepository;
import comm.services.EvenementInscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.Iterator;
import java.util.List;
import java.util.Set;


@Service("EvenementInscription")
@Transactional

public class EvenementInscriptionServiceImpl implements EvenementInscriptionService {

    @Autowired
    private final EvenementRepository evenementRepository;
    @Autowired
    private final MembreRepository membreRepository;

    /**
     * Constructeur de la classe EvenementInscriptionServiceImpl
     * @param evenementRepository
     * @param membreRepository
     */
    public EvenementInscriptionServiceImpl(EvenementRepository evenementRepository, MembreRepository membreRepository) {
        this.evenementRepository = evenementRepository;
        this.membreRepository = membreRepository;
    }


    /**
     * Convertit d'un objet Dto à Entité
     * @param evenementsDto
     * @return evenement
     */
    private Evenement dtoToEntity(EvenementsDto evenementsDto){
        Evenement evenement = new Evenement();
        evenement.setId_evenement(evenementsDto.getId_evenement());
        evenement.setNom_evenement(evenementsDto.getNom_evenement());
        evenement.setDate_evenement(evenementsDto.getDate_evenement());
        evenement.setDuree_evenement(evenementsDto.getDuree_evenement());
        evenement.setDesc_evenement(evenementsDto.getDesc_evenement());
        evenement.setImage_evenement(evenementsDto.getImage_evenement());
        evenement.setId_lieu(evenementsDto.getId_lieu());
        evenement.setNb_max_evenement(evenementsDto.getNb_max_evenement());
        return evenement;
    }

    /**
     * Convertit d'un objet Entité à DTO
     * @param evenement
     * @return evenementsDto
     */
    private EvenementsDto entityToDto(Evenement evenement){
        EvenementsDto evenementsDto = new EvenementsDto();
        evenementsDto.setId_evenement(evenement.getId_evenement());
        evenementsDto.setDate_evenement(evenement.getDate_evenement());
        evenementsDto.setDesc_evenement(evenement.getDesc_evenement());
        evenementsDto.setNom_evenement(evenement.getNom_evenement());
        evenementsDto.setDuree_evenement(evenement.getDuree_evenement());
        evenementsDto.setImage_evenement(evenement.getImage_evenement());
        evenementsDto.setNb_max_evenement(evenement.getNb_max_evenement());
        evenementsDto.setId_lieu(evenement.getId_lieu());
        return evenementsDto;
    }

    /**
     * Insère un événement
     * @param inscriptionEvenement
     * @return
     */




    @Override
    public ResponseEntity<String> postInscriptionAEvenement(RequestBodyInscriptionEvenement inscriptionEvenement) {

        Evenement evenement = this.evenementRepository.getById(inscriptionEvenement.getId_evenement());
        Membre membre = this.membreRepository.getById(inscriptionEvenement.getId_membre());
        if (membre == null) {
            return new ResponseEntity<String>("Le mail donné n'existe pas dans la base de données", HttpStatus.NOT_FOUND);
        }

        if (evenement == null) {
            return new ResponseEntity<String>("L'événement spécifié n'existe pas", HttpStatus.NOT_FOUND);
        }

        if (membre.getEvenements().contains(evenement)) {
            return new ResponseEntity<String>("Le membre est déjà inscrit à l'événement", HttpStatus.CONFLICT);
        }

        if (evenement.getMembres().contains(membre)) {
            return new ResponseEntity<String>("L'événement contient déjà le membre", HttpStatus.CONFLICT);
        }

        membre.getEvenements().add(evenement);
        evenement.getMembres().add(membre);

        // Sauvegarde des entités si nécessaire
        this.evenementRepository.save(evenement);
        this.membreRepository.save(membre);
        return new ResponseEntity<String>("Le membre a été inscrit à l'événement", HttpStatus.OK);
    }

    /**
     * Récupère une liste d'inscription d'un membre
     * @param id l'id du membre
     * @return
     */
    @Override
    public Set<Evenement> getListInscriptionAMembre(int id) {

        try {
            if (!evenementRepository.existsById(id)) {
                throw new NotFound("L'événement n'existe pas, aucune liste d'inscription disponible.");
            }
        } catch (IllegalArgumentException e) {
            throw new IllegalArgument("L'id de l'événement est invalide.");
        }
        try {
            Membre membre = this.membreRepository.getById(id);
            return membre.getEvenements();
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("Problème Entité.");
        }

    }

    /**
     * Récupère la liste des inscriptions à un événement
     * @param id l'id du membre
     * @return Une liste de membres
     */
    @Override
    public Set<Membre> getListInscriptionAEvenement(int id) {
        try {
            if (!evenementRepository.existsById(id)) {
                throw new NotFound("L'événement n'existe pas, aucune liste disponible.");
            }
        } catch (IllegalArgumentException e) {
            throw new IllegalArgument("L'id de l'événement est invalide.");
        }
        try {
            Evenement evenement = evenementRepository.getById(id);
            return evenement.getMembres();
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("Problème Entité.");
        }
    }

    /**
     * Supprime l'inscription d'un membre à un événement
     * @param id_membre l'id du membre
     * @param id_evenement l'id d'un événement
     * @return
     */
    @Override
    public ResponseEntity<String> deleteMembreAEvenement(int id_membre, int id_evenement) {

        Evenement evenement = evenementRepository.getById(id_evenement);
        Membre membre = membreRepository.getById(id_membre);
        if (!membre.getEvenements().contains(evenement)) {
            return new ResponseEntity<String>("Le membre n'était pas inscrit à l'événement", HttpStatus.MULTI_STATUS);
        }
        if (!evenement.getMembres().contains(membre)) {
            return new ResponseEntity<String>("L'événement ne contenait pas le membre", HttpStatus.MULTI_STATUS);
        }
        membre.getEvenements().remove(evenement);
        evenement.getMembres().remove(membre);
        this.membreRepository.save(membre);
        this.evenementRepository.save(evenement);
        return new ResponseEntity<String>("Le membre a été supprimé de l'événement", HttpStatus.OK);
    }
}
