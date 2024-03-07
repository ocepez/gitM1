package com.projetsi.lieux.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.projetsi.lieux.LieuxDAO.DAOException;
import com.projetsi.lieux.entity.Lieu;
import com.projetsi.lieux.service.LieuService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;

import jakarta.servlet.*;
import jakarta.servlet.http.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

/**
 * Servlet pour la gestion des opérations CRUD sur l'entité Lieu.
 *
 * @author Maxime A.
 */
@WebServlet(name = "lieuxServlet", value = "/lieux/*")
public class LieuServlet extends HttpServlet {

    private LieuService lieuService;

    /**
     * Convertit l'objet en format JSON et l'envoie dans la réponse HTTP.
     *
     * @param obj L'objet à convertir en JSON.
     * @throws IOException En cas d'erreur.
     */
    private void convertToJSON(HttpServletResponse response, Object obj) throws IOException {
        response.setContentType("application/json");
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(obj);
        response.getWriter().write(json);
    }

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            lieuService = new LieuService();
        } catch (DAOException e) {
            throw new RuntimeException(e);
        }
        getServletContext().setAttribute("lieuService", lieuService);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupère les informations de chemin à partir de l'URL de la requête.
        String pathInfo = request.getPathInfo();
        ObjectMapper objectMapper = new ObjectMapper();

        if (pathInfo == null || pathInfo.equals("/")) {
            // Récupération de tous les lieux.
            List<Lieu> lieux;

            try {
                lieux = lieuService.getAllLieux();
            } catch (DAOException e) {
                throw new RuntimeException(e);
            }

            convertToJSON(response, lieux);
            response.setStatus(HttpServletResponse.SC_OK);
        } else if (pathInfo.matches("/\\d+")) {
            String[] pathParts = pathInfo.split("/");

            try {
                int idLieu = Integer.parseInt(pathParts[1]);
                Lieu lieu = lieuService.getLieu(idLieu);

                if (lieu != null) {
                    convertToJSON(response, lieu);
                } else {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            } catch (DAOException e) {
                throw new RuntimeException(e);
            }
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupère les informations de chemin à partir de l'URL de la requête.
        ObjectMapper objectMapper = new ObjectMapper();
        Lieu lieu = objectMapper.readValue(request.getReader(), Lieu.class);

        try {
            // Création du lieu.
            lieuService.createLieu(lieu);
            convertToJSON(response, lieu);
            response.setStatus(HttpServletResponse.SC_CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();

        // Vérifier si l'URL est correcte
        if (pathInfo == null || !pathInfo.matches("/\\d+")) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("URL incorrecte.");
            return;
        }

        String[] pathParts = pathInfo.split("/");

        try {
            // Récupérer l'ID du lieu depuis l'URL
            int idLieu = Integer.parseInt(pathParts[1]);
            Lieu lieu = lieuService.getLieu(idLieu);

            // Vérifier si le lieu existe
            if (lieu == null) {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                return;
            }

            try (BufferedReader reader = request.getReader()) {
                // Vérifier si le corps de la requête contient des données
                if (reader != null) {
                    // Mapper les données JSON reçues sur un objet Lieu
                    ObjectMapper objectMapper = new ObjectMapper();
                    Lieu nouveauLieu = objectMapper.readValue(reader, Lieu.class);

                    // Mettre à jour les propriétés du lieu avec les nouvelles valeurs
                    lieu.setNom(nouveauLieu.getNom());
                    lieu.setAdresse(nouveauLieu.getAdresse());
                    lieu.setCapacite(nouveauLieu.getCapacite());

                    // Effectuer la mise à jour dans la base de données
                    try {
                        lieuService.updateLieu(lieu);

                        // Convertir le lieu mis à jour en JSON
                        String lieuJson = objectMapper.writeValueAsString(lieu);

                        // Écrire le JSON dans le corps de la réponse
                        response.setContentType("application/json");
                        response.getWriter().write(lieuJson);
                        response.setStatus(HttpServletResponse.SC_OK);
                    } catch (DAOException daoException) {
                        daoException.printStackTrace();
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    }
                } else {
                    // Aucune donnée dans le corps de la requête
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                }
            } catch (IOException e) {
                e.printStackTrace();
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        } catch (NumberFormatException e) {
            // Erreur lors de la conversion de l'ID en entier
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        } catch (Exception e) {
            // Autre exception non prévue
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }

        // Vider le tampon de réponse
        response.getWriter().flush();
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupère les informations de chemin à partir de l'URL de la requête.
        String pathInfo = request.getPathInfo();

        if (pathInfo != null && pathInfo.matches("/\\d+")) {
            String[] pathParts = pathInfo.split("/");
            try {
                int idLieu = Integer.parseInt(pathParts[1]);
                Lieu lieu = lieuService.getLieu(idLieu);

                if (lieu != null) {
                    lieuService.deleteLieu(lieu);
                    response.setStatus(HttpServletResponse.SC_NO_CONTENT);
                } else {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            } catch (DAOException e) {
                throw new RuntimeException(e);
            }
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}