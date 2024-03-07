package comm.controllers;

import comm.dtos.EvenementsDto;
import comm.entities.Evenement;
import comm.entities.Membre;
import comm.services.EvenementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

/**
 * Controller permettant la gestion des évènements
 * @author O. Pezennec
 */
@RestController
@RequestMapping("/evenements")
@Description("Gestion des événements")
public class EvenementController {

    @Autowired
    private EvenementService evenementService;

    /**
     * Controller de la classe EvenementController
     * @param evenementService
     */

    public EvenementController(EvenementService evenementService){
        this.evenementService=evenementService;
    }

    /**
     * Récupère la liste de tous les événements
     * @return une liste d'événements
     */

    @GetMapping()
    public List<EvenementsDto> getAllEvenements() {
        return evenementService.getAllEvenements();
    }

    /**
     * Insère un événement
     * @param evenement le body à insérer dans la base de données
     * @return un ResponseEntity
     */
    @PostMapping(consumes = "application/json;charset=UTF-8")
    @ResponseBody
    public ResponseEntity<String> setOneEvenement(@RequestBody EvenementsDto evenement){
        return evenementService.setOneEvenement(evenement);
    }

    /**
     * Récupère un événement par id
     * @param id l'id d'un événement
     * @return un evenement
     */
    @GetMapping(value="/{id}")
    public EvenementsDto getOneEvenementById(@PathVariable int id){

        return evenementService.getOneEvenementById(id);
    }

    /**
     * Modifie un événement
     * @param id l'id de l'événement
     * @param evenement le body à modifier
     * @return un ResponseEntity
     *
     */
    @PutMapping(value="/{id}")
    public ResponseEntity<String> setOneEvenementById(@PathVariable int id, @RequestBody EvenementsDto evenement){

        return evenementService.setOneEvenementById(id, evenement);
    }

    /**
     * Supprime un événement en fonction de son id
     * @param id l'id de l'événement à supprimer
     * @return un ResponseEntity
     */
    @DeleteMapping(value="/{id}")
    public ResponseEntity<String> deleteOneEvenementById(@PathVariable int id){

        return evenementService.deleteOneEvenementById(id);
    }

    /**
     * Récupère la liste des membres à un événement
     * @param id l'id d'un événement
     * @return la liste de membres
     */
    @GetMapping(value="/{id}/membres")
    public Set<Membre> getListeMembreByIdEvenement(@PathVariable int id){

        return evenementService.getListeMembreByIdEvenement(id);
    }
}

