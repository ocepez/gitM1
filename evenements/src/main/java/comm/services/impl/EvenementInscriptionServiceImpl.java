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

    public EvenementInscriptionServiceImpl(EvenementRepository evenementRepository, MembreRepository membreRepository) {
        this.evenementRepository = evenementRepository;
        this.membreRepository = membreRepository;
    }

    private Membre renvoieId(String email) {

        List<Membre> membreList = this.membreRepository.findAll();
        Iterator<Membre> membre = membreList.iterator();

        while (membre.hasNext()) {
            Membre membre1 = membre.next();
            if (membre1.getEmail_membre().equals(email)) {
                return membre1;
            }
        }

        return null;
    }

    @Override
    public ResponseEntity<String> postInscriptionAEvenement(RequestBodyInscriptionEvenement inscriptionEvenement) {

        //  Membre membre = renvoieId(inscriptionEvenement.);
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
