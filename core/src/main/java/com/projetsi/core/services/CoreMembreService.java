package com.projetsi.core.services;

import com.projetsi.core.entities.Commentaire;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

/**
 * Service pour intéragir avec l'API Commentaire.
 *
 * @author Oceane P.
 */
@Service
public class CoreMembreService {

    private final String COMMENTAIRES_API_URL = "http://localhost:8030";

    private final RestTemplate restTemplate;

    /**
     * Constructeur du service CoreCommentaire.
     *
     * @param restTemplate Le RestTemplate utilisé pour les appels HTTP.
     */
    public CoreMembreService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * Récupère la liste de tous les Commentaires d'un membre depuis l'API Commentaire.
     * @param id l'identifiant d'un membre
     * @return La liste de tous les Commentaires obtenue depuis l'API Commentaire.
     */
    public List<Commentaire> getAllCommentaireByMembre(int id) {
        String url = COMMENTAIRES_API_URL + "/commentaires/membre/"+id;
        Commentaire[] Commentaire = restTemplate.getForObject(url, Commentaire[].class);
        return Arrays.asList(Commentaire);
    }

    /**
     * Récupère la liste de tous les Commentaire d'un événement depuis l'API Commentaire.
     * @param id l'id d'un événement
     * @return La liste de tous les Commentaire d'un événement obtenue depuis l'API Commentaire.
     */
    public List<Commentaire> getAllCommentaireByEvenement(int id) {
        String url = COMMENTAIRES_API_URL + "/commentaires/evenement/"+id;
        Commentaire[] Commentaire = restTemplate.getForObject(url, Commentaire[].class);
        return Arrays.asList(Commentaire);
    }

    /**
     * Crée un nouveau Commentaire.
     *
     * @param commentaire Le nouveau Commentaire à créer.
     * @return Le Commentaire créé.
     */
    public void createCommentaire(Commentaire commentaire) {
        String url = COMMENTAIRES_API_URL + "/commentaires";
        restTemplate.postForObject(url, commentaire, Commentaire.class);

    }

    /**
     * Suprimme un Commentaire existant.
     *
     * @param id L'identifiant du Commentaire à supprimer.
     * @throws Exception En cas d'erreur lors de la suppression.
     */
    public void deleteCommentaire(int id) throws Exception {
        String url = COMMENTAIRES_API_URL + "/commentaires/" + id;
        try {
            restTemplate.delete(url);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Erreur lors de la suppression du Commentaire d'identifiant : " + id, e);
        }
    }
}
