package comm.services.impl;
import comm.dtos.EvenementsDto;
import comm.entities.Evenement;
import comm.entities.Lieu;
import comm.entities.Membre;
import comm.exceptions.IllegalArgument;
import comm.exceptions.NotFound;
import comm.repositories.EvenementRepository;
import comm.repositories.LieuRepository;
import comm.repositories.MembreRepository;
import comm.services.EvenementService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.xml.crypto.Data;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.TemporalUnit;
import java.util.*;


/**
 * @author O. Pezennec
 * @see comm.services.EvenementService
 */
@Service("EvenementService")
public class EvenementServiceImpl implements EvenementService {

    private final EvenementRepository evenementRepository;
    private final LieuRepository lieuRepository;
    private  final MembreRepository membreRepository;

    /**
     * Constructeur de EvenementServiceImpl
     * @param evenementRepository
     * @param lieuRepository
     * @param membreRepository
     */
    public EvenementServiceImpl(EvenementRepository evenementRepository, LieuRepository lieuRepository, MembreRepository membreRepository) {
        this.evenementRepository = evenementRepository;
        this.lieuRepository = lieuRepository;
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
     * Vérifie que des événements ne se chevauchent pas
     * @param evenement1
     * @return
     */
    private boolean exist(Evenement evenement1){
       List<Evenement> evenements1 = evenementRepository.findAll();
       for(Evenement evenement : evenements1){
           Date date = evenement.getDate_evenement();
           if(evenement.getId_lieu() == evenement1.getId_lieu())
           {
               if (
                       evenement.getDate_evenement().toInstant()
                               .atZone(ZoneId.systemDefault())
                               .toLocalDateTime()
                               .isBefore(evenement1.getDate_evenement()
                                       .toInstant()
                                       .atZone(ZoneId.systemDefault())
                                       .toLocalDateTime().plusHours(evenement1.getDuree_evenement()))
                       &&

                       evenement.getDate_evenement()
                               .toInstant()
                               .atZone(ZoneId.systemDefault())
                               .toLocalDateTime()
                               .plusHours(evenement1.getDuree_evenement())
                               .isBefore(evenement.getDate_evenement()
                                       .toInstant()
                                       .atZone(ZoneId.systemDefault())
                                       .toLocalDateTime()
                                       .plusHours(evenement.getDuree_evenement())
                               )
               )

               {
                   return true; // Les événements se chevauchent
               }
           }
       }
        return false;
    }

    /**
     * Verifie que la date insérée n'est pas avant la date d'aujourd'hui
     * @param dateDebut
     * @return
     */
    public static boolean verifierDateDebut(Date dateDebut) {
        Date dateActuelle = new Date();
        return !dateDebut.before(dateActuelle);
    }


    /**
     * Récupère toute la liste des événements
     * @return Une liste des evenements DTO
     */
    @Override
    public List<EvenementsDto> getAllEvenements() {
        List<EvenementsDto> evenements = new ArrayList<>();
        for(Evenement evenement : evenementRepository.findAll())
        {
            evenements.add(entityToDto(evenement));
        }
       return evenements;
    }

    /**
     * Insère un nouvel evenement
     * @param evenement l'événement à insérer
     * @return
     */
    @Override
    public ResponseEntity<String> setOneEvenement(EvenementsDto evenement) {
        Evenement evenement1 = dtoToEntity(evenement);
        if(exist(evenement1)){
            return new ResponseEntity<String>("L'evenement chevauche un autre evenement", HttpStatus.CONFLICT);
        }
        if(evenement1.getNb_max_evenement() <= 0){
            return new ResponseEntity<String>("L'evenement chevauche un autre evenement", HttpStatus.CONFLICT);
        }
        if(evenement1.getNb_max_evenement() > this.lieuRepository.getById(evenement1.getId_lieu()).getCapacite_lieu()){
            return new ResponseEntity<String>("L'evenement ne peut pas donner un nombre de places plus importantes que la capacité.", HttpStatus.CONFLICT);
        }
        if(!verifierDateDebut(evenement1.getDate_evenement())){
            return new ResponseEntity<String>("La date de l'evenement n'est pas avant la date du jour", HttpStatus.CONFLICT);
        }
        evenementRepository.save(evenement1);
        return new ResponseEntity<String>("L'evenement a été enregistré", HttpStatus.CREATED);
    }

    /**
     * Récupère un événement
     * @param id l'identifiant de l'événement à récupérer
     * @return
     */
    @Override
    public EvenementsDto getOneEvenementById(int id) {
        try {
           if(!evenementRepository.existsById(id)){
               throw new NotFound("L'événement que vous cherchez n'existe pas.");
           }
            Optional<Evenement> tt  = evenementRepository.findById(id);
            return entityToDto(tt.get()) ;

        }catch(IllegalArgumentException e)
        {
            throw new IllegalArgument("Vous avez donné un mauvais identifiant pour récupérer l'événement.");
        }

    }

    /**
     * Met à jour un événement
     * @param id l'identifiant de l'événement à modifier.
     * @param evenement les informations à remplacer.
     * @return
     */
    @Override
    public ResponseEntity<String> setOneEvenementById(int id, EvenementsDto evenement) {
        Evenement evenement1= evenementRepository.getById(id);

        if(exist(dtoToEntity(evenement))){
            evenementRepository.save(evenement1);
            return new ResponseEntity<String>("L'événement à mettre à jour chevauche d'autres événements.", HttpStatus.CONFLICT);
        }
        EvenementInscriptionServiceImpl evenementInscriptionService = new EvenementInscriptionServiceImpl(this.evenementRepository,this.membreRepository);
        if(evenement.getNb_max_evenement() <= evenementInscriptionService.getListInscriptionAEvenement(id).size()){
            return new ResponseEntity<>("La taille demandé est insuffisante pour le nombre d'inscriptions.", HttpStatus.CONFLICT);
        }
        Lieu lieu = lieuRepository.getById(evenement.getId_lieu());
        if(evenement.getNb_max_evenement() > lieu.getCapacite_lieu()){
            return new ResponseEntity<>("La taille demandé est supérieure à la capacité du lieu.", HttpStatus.CONFLICT);
        }
        if(evenement.getImage_evenement() != null && !"".equals(evenement.getDesc_evenement())){
            evenement1.setImage_evenement(evenement.getImage_evenement());
        }
        if(evenement.getDesc_evenement() != null && !"".equals(evenement.getDesc_evenement())){
            evenement1.setDesc_evenement(evenement.getDesc_evenement());
        }
        if(evenement.getDate_evenement() != null){
            evenement1.setDate_evenement(evenement.getDate_evenement());
        }
        if(evenement.getDuree_evenement() > 0){
            evenement1.setDuree_evenement(evenement.getDuree_evenement());
        }
        if(evenement.getNom_evenement() != null && !"".equals(evenement.getDesc_evenement())){
            evenement1.setNom_evenement(evenement.getNom_evenement());
        }
        //On augmente si la taille proposée est supérieur au nombre d'inscrits
        if(evenement.getNb_max_evenement() > evenementInscriptionService.getListInscriptionAEvenement(id).size()){
            evenement1.setNb_max_evenement(evenement.getNb_max_evenement());
        }
        evenementRepository.save(evenement1);
        return new ResponseEntity<String>("Mise à Jour Evenement", HttpStatus.OK);
    }


    /**
     * Supprime un événement
     * @param id
     * @return
     */
    @Override
    public ResponseEntity<String> deleteOneEvenementById(int id) {
        try {
            if(!evenementRepository.existsById(id)){
                throw new NotFound("L'événement à effacer n'existe pas.");
            }
        }catch(IllegalArgumentException e){
            throw new IllegalArgument("L'id de l'événement à effacer est invalide.");
        }
        evenementRepository.deleteById(id);
        return new ResponseEntity<>("L'événement à été supprimé.", HttpStatus.OK);
    }

    /**
     * Recupere une liste de membres par l'événement
     * @param id l'identifiant de l'événement.
     * @return
     */
    @Override
    public Set<Membre> getListeMembreByIdEvenement(int id) {
        try {
            if(!evenementRepository.existsById(id)){
                throw new NotFound("L'événement n'existe pas, aucune liste disponible.");
            }
        }catch(IllegalArgumentException e){
            throw new IllegalArgument("L'id de l'événement est invalide.");
        }
        try {
            Evenement evenement = evenementRepository.getById(id);
            return evenement.getMembres();
        }catch (EntityNotFoundException e){
            throw new EntityNotFoundException("Problème Entité.");
        }

    }
}
