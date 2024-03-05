package com.projetsi.core.services;

import com.projetsi.core.entities.Evenement;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

/**
 * Service pour intéragir avec l'API Evenement.
 *
 * @author Oceane P.
 */
@Service
public class CoreEvenementService {

    private final String EVENEMENT_API_URL = "http://localhost:8010";

    private final RestTemplate restTemplate;

    /**
     * Constructeur du service CoreEvenement.
     *
     * @param restTemplate Le RestTemplate utilisé pour les appels HTTP.
     */
    public CoreEvenementService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * Récupère la liste de tous les Evenement depuis l'API Evenement.
     *
     * @return La liste de tous les Evenement obtenue depuis l'API Evenement.
     */
    public List<Evenement> getAllEvenement() {
        String url = EVENEMENT_API_URL + "/evenements";
        Evenement[] Evenement = restTemplate.getForObject(url, Evenement[].class);
        return Arrays.asList(Evenement);
    }

    /**
     * Crée un nouveau Evenement.
     *
     * @param Evenement Le nouveau Evenement à créer.
     * @return Le Evenement créé.
     */
    public Evenement createEvenement(Evenement Evenement) {
        String url = EVENEMENT_API_URL + "/evenements";
        return restTemplate.postForObject(url, Evenement, Evenement.class);
    }

    /**
     * Récupère un Evenement par son ID.
     *
     * @param id L'identidfiant du Evenement.
     * @return Le Evenement correspondant à l'identifiant.
     */
    public Evenement getEvenementById(int id) {
        String url = EVENEMENT_API_URL + "/evenements/" + id;
        Evenement Evenement = restTemplate.getForObject(url, Evenement.class);
        return Evenement;
    }



    /**
     * Met à jour un Evenement existant.
     *
     * @param id L'identifiant du Evenement à modifier.
     * @param Evenement Les nouvelles données du Evenement.
     * @return Le Evenement mis à jour.
     */
    public Evenement updateEvenement(int id, Evenement Evenement) {
        String url = EVENEMENT_API_URL + "/evenements/" + id;
        return restTemplate.postForObject(url, Evenement, Evenement.class);
    }

    /**
     * Suprimme un Evenement existant.
     *
     * @param id L'identifiant du Evenement à supprimer.
     * @throws Exception En cas d'erreur lors de la suppression.
     */
    public void deleteEvenement(int id) throws Exception {
        String url = EVENEMENT_API_URL + "/evenements/" + id;

        try {
            restTemplate.delete(url);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Erreur lors de la suppression du Evenement d'identifiant : " + id, e);
        }
    }
    /**
     * Récupère un Evenement par son ID.
     *
     * @param id L'identidfiant du Evenement.
     * @return Le Evenement correspondant à l'identifiant.
     */
    public Evenement getListMembreByEvenement(int id) {
        String url = EVENEMENT_API_URL + "/evenements/" + id;
        Evenement evenement = restTemplate.getForObject(url, Evenement.class);
        return evenement;
    }

    /**
     * Inscription d'un membre à un événement
     *
     * @param id L'identidfiant du Evenement.
     * @return Le Evenement correspondant à l'identifiant.
     */
    public Evenement postInscription(int id) {
        String url = EVENEMENT_API_URL + "/evenements/" + id;
        Evenement evenement = restTemplate.getForObject(url, Evenement.class);
        return evenement;
    }

    /**
     * Récupère la liste des inscriptions d'un membre
     *
     * @param id L'identidfiant du Evenement.
     * @return Le Evenement correspondant à l'identifiant.
     */
    public Evenement getListInscriptionsByMembre(int id) {
        String url = EVENEMENT_API_URL + "/evenements/" + id;
        Evenement evenement = restTemplate.getForObject(url, Evenement.class);
        return evenement;
    }

    /**
     * Récupère la liste des événements d'un membre
     *
     * @param id L'identidfiant du Evenement.
     * @return Le Evenement correspondant à l'identifiant.
     */
    public Evenement getListInscriptionsByEvenement(int id) {
        String url = EVENEMENT_API_URL + "/evenements/" + id;
        Evenement evenement = restTemplate.getForObject(url, Evenement.class);
        return evenement;
    }

    /**
     * Récupère la liste des événements d'un membre
     *
     * @param id_evenement L'identidfiant de l'evenement.
     * @param  id_membre L'identifiant du membre.
     * @return Le Evenement correspondant à l'identifiant.
     */
    public Evenement deleteInscription(int id_membre, int id_evenement) {
        String url = EVENEMENT_API_URL + "/evenements/" + id_membre+"/"+ id_evenement;
        Evenement evenement = restTemplate.getForObject(url, Evenement.class);
        return evenement;
    }
}
