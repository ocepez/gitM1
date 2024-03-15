package comm.services;

import comm.dtos.RequestBodyInscriptionEvenement;
import comm.entities.Evenement;
import comm.entities.Membre;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Set;

public interface EvenementInscriptionService {

    /**
     * Inscrit un membre à un événement
     * @param inscriptionEvenement
     * @return
     */
    public ResponseEntity<String> postInscriptionAEvenement(RequestBodyInscriptionEvenement inscriptionEvenement);

    /**
     * Récupère la liste des inscriptions d'un membre
     * @param id l'id du membre
     * @return  la liste d'inscriptions d'un membre
     */
    public Set<Evenement> getListInscriptionAMembre(int id);

    /**
     * Récupère la liste des inscriptions d'un membre
     * @param id l'id du membre
     * @return  la liste d'inscrits à un événement
     */
    public Set<Membre> getListInscriptionAEvenement(int id);
    /**
     * Récupère la liste des inscriptions d'un membre
     * @param id_membre l'id du membre
     * @param id_evenement l'id d'un événement
     * @return  la liste d'inscrits à un événement
     */
    public ResponseEntity<String> deleteMembreAEvenement(int id_membre, int id_evenement);

}
