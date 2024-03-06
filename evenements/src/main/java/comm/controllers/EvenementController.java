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


    public EvenementController(EvenementService evenementService){
        this.evenementService=evenementService;
    }


    @GetMapping()
    public List<EvenementsDto> getAllEvenements() {
        return evenementService.getAllEvenements();
    }

    @PostMapping(consumes = "application/json;charset=UTF-8")
    @ResponseBody
    public ResponseEntity<String> setOneEvenement(@RequestBody EvenementsDto evenement){
        return evenementService.setOneEvenement(evenement);
    }

    @GetMapping(value="/{id}")
    public EvenementsDto getOneEvenementById(@PathVariable int id){

        return evenementService.getOneEvenementById(id);
    }

    @PutMapping(value="/{id}")
    public ResponseEntity<String> setOneEvenementById(@PathVariable int id, @RequestBody EvenementsDto evenement){

        return evenementService.setOneEvenementById(id, evenement);
    }

    @DeleteMapping(value="/{id}")
    public ResponseEntity<String> deleteOneEvenementById(@PathVariable int id){

        return evenementService.deleteOneEvenementById(id);
    }

    @GetMapping(value="/{id}/membres")
    public Set<Membre> getListeMembreByIdEvenement(@PathVariable int id){

        return evenementService.getListeMembreByIdEvenement(id);
    }
}

