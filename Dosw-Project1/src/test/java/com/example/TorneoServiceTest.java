package com.techcup.service;

import com.techcup.model.Torneo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class TorneoServiceTest {

    private TorneoService torneoService;

    @BeforeEach
    void setUp() {
        torneoService = new TorneoService();
    }

    @Test
    void debeCrearTorneoConEstadoBorrador() {
        Torneo torneo = torneoService.crearTorneo(
                "TechCup 2025-1",
                LocalDate.of(2025, 3, 10),
                LocalDate.of(2025, 5, 30),
                8, 50000.0
        );

        assertNotNull(torneo);
        assertEquals("TechCup 2025-1", torneo.getNombre());
        assertEquals("BORRADOR", torneo.getEstado());
    }

    @Test
    void debeLanzarExcepcionSiFechaFinEsAnteriorAInicio() {
        assertThrows(IllegalArgumentException.class, () ->
                torneoService.crearTorneo(
                        "Torneo Inválido",
                        LocalDate.of(2025, 6, 1),
                        LocalDate.of(2025, 3, 1),
                        8, 50000.0
                )
        );
    }

    // HU-05: Activar torneo
    @Test
    void debeCambiarEstadoDeBorradorAActivo() {
        Torneo torneo = torneoService.crearTorneo(
                "TechCup 2025-1",
                LocalDate.of(2025, 3, 10),
                LocalDate.of(2025, 5, 30),
                8, 50000.0
        );

        torneoService.activarTorneo(torneo);

        assertEquals("ACTIVO", torneo.getEstado());
    }

    @Test
    void debeLanzarExcepcionSiTorneoNoEstaEnBorrador() {
        Torneo torneo = torneoService.crearTorneo(
                "TechCup 2025-1",
                LocalDate.of(2025, 3, 10),
                LocalDate.of(2025, 5, 30),
                8, 50000.0
        );
        torneoService.activarTorneo(torneo);

        assertThrows(IllegalArgumentException.class, () ->
                torneoService.activarTorneo(torneo)
        );
    }
}