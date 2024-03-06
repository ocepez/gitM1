package com.projetsi.core.services;

import com.projetsi.core.entities.Membre;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

/**
 * Service pour intéragir avec l'API Membre.
 *
 * @author Oceane P.
 */
@Service
public class CoreMembreService {

    private final String MEMBRES_API_URL = "http://localhost:8030";

    private final RestTemplate restTemplate;

    /**
     * Constructeur du service CoreMembre.
     *
     * @param restTemplate Le RestTemplate utilisé pour les appels HTTP.
     */
    public CoreMembreService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    /**
     * Récupère la liste de tous les Membres d'un membre depuis l'API Membre.
     * @return La liste de tous les Membres obtenue depuis l'API Membre.
     */
    public void connexion() {
        String url = MEMBRES_API_URL + "/membres";
        Membre[] Membre = restTemplate.getForObject(url, Membre[].class);
       // return Arrays.asList(Membre);
    }
    /**
     * Récupère la liste de tous les Membres d'un membre depuis l'API Membre.
     * @return La liste de tous les Membres obtenue depuis l'API Membre.
     */
    public List<Membre> getAllMembre() {
        String url = MEMBRES_API_URL + "/membres";
        Membre[] Membre = restTemplate.getForObject(url, Membre[].class);
        return Arrays.asList(Membre);
    }
    /**
     * Crée un nouveau Membre.
     *
     * @param Membre Le nouveau Membre à créer.
     * @return Le Membre créé.
     */
    public void createMembre(Membre Membre) {
        String url = MEMBRES_API_URL + "/membres";
        restTemplate.postForObject(url, Membre, Membre.class);

    }


    /**
     * Récupère les informations d'un membre.
     * @param id l'identifiant d'un membre
     * @return La liste de tous les Membres obtenue depuis l'API Membre.
     */
    public List<Membre> getMembreById(int id) {
        String url = MEMBRES_API_URL + "/membres/"+id;
        Membre[] Membre = restTemplate.getForObject(url, Membre[].class);
        return Arrays.asList(Membre);
    }


    /**
     * Met à jour les informations d'un membre
     * @param id l'identifiant d'un membre
     * @return La liste de tous les Membres obtenue depuis l'API Membre.
     */
    public List<Membre> putMembreById(int id) {
        String url = MEMBRES_API_URL + "/membres/"+id;
        Membre[] Membre = restTemplate.getForObject(url, Membre[].class);
        return Arrays.asList(Membre);
    }


    /**
     * Suprimme un Membre existant.
     *
     * @param id L'identifiant du Membre à supprimer.
     * @throws Exception En cas d'erreur lors de la suppression.
     */
    public void deleteMembre(int id) throws Exception {
        String url = MEMBRES_API_URL + "/membres/" + id;
        try {
            restTemplate.delete(url);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Erreur lors de la suppression du Membre d'identifiant : " + id, e);
        }
    }
    /**
     * Récupère la liste de tous les Membre d'un événement depuis l'API Membre.
     * @param id l'id d'un événement
     * @return La liste de tous les Membre d'un événement obtenue depuis l'API Membre.
     */
    public List<Membre> getListEvenementsByMembre(int id) {
        String url = MEMBRES_API_URL + "/Membres/evenement/"+id;
        Membre[] Membre = restTemplate.getForObject(url, Membre[].class);
        return Arrays.asList(Membre);
    }



}
