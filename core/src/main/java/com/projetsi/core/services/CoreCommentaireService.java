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
public class CoreCommentaireService {

    private final String COMMENTAIRES_API_URL = "http://localhost:8000";

    private final RestTemplate restTemplate;

    /**
     * Constructeur du service CoreCommentaire.
     *
     * @param restTemplate Le RestTemplate utilisé pour les appels HTTP.
     */
    public CoreCommentaireService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * Récupère la liste de tous les Commentaire depuis l'API Commentaire.
     *
     * @return La liste de tous les Commentaire obtenue depuis l'API Commentaire.
     */
    public List<Commentaire> getAllCommentaire() {
        String url = Commentaire_API_URL + "/Commentaire";
        Commentaire[] Commentaire = restTemplate.getForObject(url, Commentaire[].class);
        return Arrays.asList(Commentaire);
    }

    /**
     * Récupère un Commentaire par son ID.
     *
     * @param id L'identidfiant du Commentaire.
     * @return Le Commentaire correspondant à l'identifiant.
     */
    public Commentaire getCommentaireById(int id) {
        String url = Commentaire_API_URL + "/Commentaire/" + id;
        Commentaire Commentaire = restTemplate.getForObject(url, Commentaire.class);
        return Commentaire;
    }

    /**
     * Crée un nouveau Commentaire.
     *
     * @param Commentaire Le nouveau Commentaire à créer.
     * @return Le Commentaire créé.
     */
    public Commentaire createCommentaire(Commentaire Commentaire) {
        String url = Commentaire_API_URL + "/Commentaires";
        return restTemplate.postForObject(url, Commentaire, Commentaire.class);
    }

    /**
     * Met à jour un Commentaire existant.
     *
     * @param id L'identifiant du Commentaire à modifier.
     * @param Commentaire Les nouvelles données du Commentaire.
     * @return Le Commentaire mis à jour.
     */
    public Commentaire updateCommentaire(int id, Commentaire commentaire) {
        String url = Commentaire_API_URL + "/Commentaires/" + id;
        return restTemplate.postForObject(url, commentaire, Commentaire.class);
    }

    /**
     * Suprimme un Commentaire existant.
     *
     * @param id L'identifiant du Commentaire à supprimer.
     * @throws Exception En cas d'erreur lors de la suppression.
     */
    public void deleteCommentaire(int id) throws Exception {
        String url = Commentaire_API_URL + "/Commentaire/" + id;

        try {
            restTemplate.delete(url);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Erreur lors de la suppression du Commentaire d'identifiant : " + id, e);
        }
    }
}
