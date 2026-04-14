package edu.eci.dosw.tech_cup.service;

import edu.eci.dosw.tech_cup.entity.Tournament;
import edu.eci.dosw.tech_cup.mapper.TournamentMapper;
import edu.eci.dosw.tech_cup.model.TournamentModel;
import edu.eci.dosw.tech_cup.model.enums.TournamentStatus;
import edu.eci.dosw.tech_cup.repository.TournamentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TournamentServiceTest {

    @Mock
    private TournamentRepository tournamentRepository;

    @Mock
    private TournamentMapper tournamentMapper;

    @InjectMocks
    private TournamentService tournamentService;

    private Tournament tournament;
    private TournamentModel tournamentModel;

    @BeforeEach
    void setUp() {
        tournament = new Tournament();
        tournament.setId(1L);
        tournament.setStatus(TournamentStatus.DRAFT);

        tournamentModel = new TournamentModel();
        tournamentModel.setId(1L);
        tournamentModel.setStatus(TournamentStatus.DRAFT);
    }

    // TEST 1: findById exitoso
    @Test
    void findById_torneoExiste_retornaModelo() {
        when(tournamentRepository.findById(1L)).thenReturn(Optional.of(tournament));
        when(tournamentMapper.toModel(tournament)).thenReturn(tournamentModel);

        TournamentModel result = tournamentService.findById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    // TEST 2: findById torneo no existe lanza excepción
    @Test
    void findById_torneoNoExiste_lanzaExcepcion() {
        when(tournamentRepository.findById(99L)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> tournamentService.findById(99L));

        assertEquals("Torneo no encontrado", ex.getMessage());
    }

    // TEST 3: create asigna status DRAFT automáticamente
    @Test
    void create_nuevoTorneo_asignaStatusDraft() {
        when(tournamentMapper.toEntity(any())).thenReturn(tournament);
        when(tournamentRepository.save(tournament)).thenReturn(tournament);
        when(tournamentMapper.toModel(tournament)).thenReturn(tournamentModel);

        TournamentModel input = new TournamentModel();
        TournamentModel result = tournamentService.create(input);

        assertEquals(TournamentStatus.DRAFT, input.getStatus());
        assertNotNull(result);
    }

    // TEST 4: update de torneo FINISHED lanza excepción
    @Test
    void update_torneoFinalizado_lanzaExcepcion() {
        tournament.setStatus(TournamentStatus.FINISHED);
        when(tournamentRepository.findById(1L)).thenReturn(Optional.of(tournament));

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> tournamentService.update(1L, new TournamentModel()));

        assertEquals("No se puede modificar un torneo finalizado", ex.getMessage());
    }

    // TEST 5: delete de torneo en DRAFT elimina correctamente
    @Test
    void delete_torneoEnDraft_eliminaCorrectamente() {
        when(tournamentRepository.findById(1L)).thenReturn(Optional.of(tournament));

        assertDoesNotThrow(() -> tournamentService.delete(1L));
        verify(tournamentRepository, times(1)).deleteById(1L);
    }

    // TEST 6: delete de torneo que no está en DRAFT lanza excepción
    @Test
    void delete_torneoNoEnDraft_lanzaExcepcion() {
        tournament.setStatus(TournamentStatus.ACTIVE);
        when(tournamentRepository.findById(1L)).thenReturn(Optional.of(tournament));

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> tournamentService.delete(1L));

        assertEquals("Solo se pueden eliminar torneos en estado DRAFT", ex.getMessage());
    }
}