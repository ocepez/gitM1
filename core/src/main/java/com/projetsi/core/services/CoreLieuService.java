package com.projetsi.core.services;

import com.projetsi.core.entities.Lieu;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

/**
 * Service pour intéragir avec l'API Lieux.
 *
 * @author Maxime A.
 */
@Service
public class CoreLieuService {

    private final String LIEUX_API_URL = "http://localhost:8020";

    private final RestTemplate restTemplate;

    /**
     * Constructeur du service CoreLieu.
     *
     * @param restTemplate Le RestTemplate utilisé pour les appels HTTP.
     */
    public CoreLieuService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * Récupère la liste de tous les lieux depuis l'API Lieux.
     *
     * @return La liste de tous les lieux obtenue depuis l'API Lieux.
     */
    public List<Lieu> getAllLieux() {
        String url = LIEUX_API_URL + "/lieux";
        Lieu[] lieux = restTemplate.getForObject(url, Lieu[].class);
        return Arrays.asList(lieux);
    }

    /**
     * Récupère un lieu par son ID.
     *
     * @param id L'identidfiant du lieu.
     * @return Le lieu correspondant à l'identifiant.
     */
    public Lieu getLieuById(int id) {
        String url = LIEUX_API_URL + "/lieux/" + id;
        Lieu lieu = restTemplate.getForObject(url, Lieu.class);
        return lieu;
    }

    /**
     * Crée un nouveau lieu.
     *
     * @param lieu Le nouveau lieu à créer.
     * @return Le lieu créé.
     */
    public Lieu createLieu(Lieu lieu) {
        String url = LIEUX_API_URL + "/lieux";
        return restTemplate.postForObject(url, lieu, Lieu.class);
    }

    /**
     * Met à jour un lieu existant.
     *
     * @param id L'identifiant du lieu à modifier.
     * @param lieu Les nouvelles données du lieu.
     * @return Le lieu mis à jour.
     */
    public Lieu updateLieu(int id, Lieu lieu) {
        String url = LIEUX_API_URL + "/lieux/" + id;
        return restTemplate.postForObject(url, lieu, Lieu.class);
    }

    /**
     * Suprimme un lieu existant.
     *
     * @param id L'identifiant du lieu à supprimer.
     * @throws Exception En cas d'erreur lors de la suppression.
     */
    public void deleteLieu(int id) throws Exception {
        String url = LIEUX_API_URL + "/lieux/" + id;

        try {
            restTemplate.delete(url);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Erreur lors de la suppression du lieu d'identifiant : " + id, e);
        }
    }
}
