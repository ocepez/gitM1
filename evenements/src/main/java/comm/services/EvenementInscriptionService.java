package comm.services;

import comm.dtos.RequestBodyInscriptionEvenement;
import comm.entities.Evenement;
import comm.entities.Membre;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Set;

public interface EvenementInscriptionService {

    public ResponseEntity<String> postInscriptionAEvenement(RequestBodyInscriptionEvenement inscriptionEvenement);

    public Set<Evenement> getListInscriptionAMembre(int id);

    public Set<Membre> getListInscriptionAEvenement(int id);

    public ResponseEntity<String> deleteMembreAEvenement(int id_membre, int id_evenement);

}
