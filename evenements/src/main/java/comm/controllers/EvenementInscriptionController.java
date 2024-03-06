package comm.controllers;


import comm.dtos.RequestBodyInscriptionEvenement;
import comm.entities.Evenement;
import comm.entities.Membre;
import comm.services.EvenementInscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;


/**
 * Controller permettant la gestion des inscriptions des membres à des événements
 * @author O. Pezennec
 */
@RestController
@RequestMapping("/evenements/inscription")
@Description("Gestion des inscriptions")
public class EvenementInscriptionController {


    /**
     * Service
     */
    @Autowired
    EvenementInscriptionService evenementInscriptionService;

    /**
     * Constructeur associant le service
     * @param evenementInscriptionService Service
     */
    public EvenementInscriptionController(EvenementInscriptionService evenementInscriptionService){
        this.evenementInscriptionService=evenementInscriptionService;
    }

    /**
     * Inscrit un membre à un événement
     * @param requestBodyInscriptionEvenement Envoie un Request body contenant l'id de l'événement et du membre
     * @return Une entité de reponse contenant un code de retour HTTP
     */
    @PostMapping
    @Description("Inscrit un membre à un événement")
    public ResponseEntity<String> postInscriptionAEvenement(@RequestBody RequestBodyInscriptionEvenement requestBodyInscriptionEvenement){

        return this.evenementInscriptionService.postInscriptionAEvenement(requestBodyInscriptionEvenement);
    }

    @GetMapping(value="/membre/{id}")
    @Description("Recupère la liste des inscriptions d'un membre")
    public Set<Evenement> getListInscriptionAMembre(@PathVariable int id){
        return evenementInscriptionService.getListInscriptionAMembre(id);
    }

    @GetMapping(value="/evenement/{id}")
    @Description("Recupère la liste des inscriptions à un événement")
    public Set<Membre> getListInscriptionAEvenement(@PathVariable int id){
        return evenementInscriptionService.getListInscriptionAEvenement(id);
    }

  @DeleteMapping(value = "/{id_membre}/{id_evenement}")
  @Description("Recupère la liste des inscriptions à un événement")
  public ResponseEntity<String> deleteMembreAEvenement(@PathVariable int id_membre, @PathVariable int id_evenement) {
        return evenementInscriptionService.deleteMembreAEvenement(id_membre,id_evenement);
    }

}
