package comm.controllers;

import comm.dtos.EvenementsDto;
import comm.dtos.RequestBodyInscriptionEvenement;
import comm.repositories.EvenementRepository;
import comm.repositories.LieuRepository;
import comm.repositories.MembreRepository;
import comm.services.EvenementService;
import comm.services.impl.EvenementServiceImpl;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import static java.time.LocalTime.now;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class EvenementControllerTest {


    @Mock
    EvenementRepository evenementRepository;

    @Mock
    LieuRepository lieuRepository;

    @Mock
    MembreRepository membreRepository;
    @InjectMocks
    EvenementService evenementService;


    @BeforeEach
    public void setUp() {
        evenementService = new EvenementServiceImpl(evenementRepository,lieuRepository,membreRepository);
    }
    @Test
    void getAllEvenements() {
        List<EvenementsDto> result = evenementService.getAllEvenements();
        assertNotNull(result);
    }

    @Test
    void setOneEvenement() {
        EvenementsDto evenementsDto = mock(EvenementsDto.class);
        LocalTime date = now();
        when(evenementsDto.getId_evenement()).thenReturn(19);
        when(evenementsDto.getNom_evenement()).thenReturn("Levy en scène");
        when(evenementsDto.getDesc_evenement()).thenReturn("Il va vous présenter une application révolutionnaire !");



    }

    @Test
    void getOneEvenementById() {
    }

    @Test
    void setOneEvenementById() {
    }

    @Test
    void deleteOneEvenementById() {
    }

    @Test
    void getListeMembreByIdEvenement() {
    }
}