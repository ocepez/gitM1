package com.ubo.commentaires;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.mockito.Mockito.*;


class CommentaireServletTest {


    CommentaireServlet commentaireServlet = new CommentaireServlet();


    @Test
    void postCommentaireOK() throws IOException {
        // -- On mocke la reponse et la requete
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        // -- On va configurer les mocks pour le fct de l'endpoint
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        BufferedReader bufferedReader = new BufferedReader(new StringReader("{\n" +
                "  \"contenu_commentaire\": \"Contenu pour test unitaire\",\n" +
                "  \"id_evenement\": 123,\n" +
                "  \"id_membre\": 456\n" +
                "}\n"));
        // -- On "insère" les paramètres nécéssaires et configurés
        when(response.getWriter()).thenReturn(writer);
        when(request.getReader()).thenReturn(bufferedReader);
        // -- On lance évidemment la methode
        commentaireServlet.doPost(request, response);
        // -- On vérfie que la reponse renvoie bien des éléments attendus
        verify(response).setStatus(HttpServletResponse.SC_CREATED);
        verify(response).setContentType("application/json");

    }

    @Test
    void deleteMembreOK() throws ServletException, IOException {
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(request.getPathInfo()).thenReturn("/9");
        when(response.getWriter()).thenReturn(writer);
        commentaireServlet.doDelete(request, response);
        verify(response).setStatus(HttpServletResponse.SC_OK);
    }


    @Test
    public void getevenementOK() throws ServletException, IOException {
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getPathInfo()).thenReturn("/evenement/1");
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);
        commentaireServlet.doGet(request, response);
        verify(response).setContentType("application/json");
        ;
        verify(response).setStatus(200);
    }

    @Test
    public void getMembreOK() throws ServletException, IOException {
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getPathInfo()).thenReturn("/membre/1");
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);
        commentaireServlet.doGet(request, response);
        verify(response).setContentType("application/json");
        ;
        verify(response).setStatus(200);
    }
}