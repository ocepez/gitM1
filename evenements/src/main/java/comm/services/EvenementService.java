package comm.services;

import comm.dtos.EvenementsDto;
import comm.entities.Evenement;

import comm.entities.Membre;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Set;

/**
 * @ O. Pezennec
 * Interface contenant les endpoints.
 */
public interface EvenementService {

    /**
     *
     * Retourne la liste des événements
     * @return Une liste d'événements
     */
    public List<EvenementsDto> getAllEvenements();
    /**
     * Insere un événement
     * @param evenement l'événement à insérer
     * @return Un objet ResponseEntity
     */
    public ResponseEntity<String> setOneEvenement(EvenementsDto evenement);
    /**
     * récupère un événement spécifique
     * @param id l'identifiant de l'événement à récupérer
     * @return l'evenement demandé
     */
    public EvenementsDto getOneEvenementById(int id);
    /**
     * Modifie un événement
     * @param id l'identifiant de l'événement à modifier.
     * @param evenement les informations à remplacer.
     * @return Un objet ResponseEntity
     */
    public ResponseEntity<String> setOneEvenementById(int id, EvenementsDto evenement);
    /**
     * efface un événement
     * @param id l'identifiant de l'événement à effacer.
     * @return Un objet ResponseEntity
     */


    public ResponseEntity<String> deleteOneEvenementById(int id);

    /**
     * Recupère la liste des membres d'un événement donné.
     * @param id l'identifiant de l'événement.
     * @return La liste des membres
     */
    public Set<Membre> getListeMembreByIdEvenement(int id);



    }
