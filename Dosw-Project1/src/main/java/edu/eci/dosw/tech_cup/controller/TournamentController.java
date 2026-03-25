package edu.eci.dosw.tech_cup.controller;

import edu.eci.dosw.tech_cup.model.TournamentModel;
import edu.eci.dosw.tech_cup.model.enums.TournamentStatus;
import edu.eci.dosw.tech_cup.service.TournamentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/api/tournaments")
@Tag(name = "Torneos", description = "Endpoints para la gestión de torneos")
public class TournamentController {

    private final TournamentService tournamentService;

    public TournamentController(TournamentService tournamentService) {
        this.tournamentService = tournamentService;
    }

    @GetMapping
    @Operation(summary = "Obtener todos los torneos")
    public ResponseEntity<List<TournamentModel>> getAll() {
        return ResponseEntity.ok(tournamentService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener torneo por ID")
    public ResponseEntity<TournamentModel> getById(@PathVariable Long id) {
        TournamentModel tournament = tournamentService.findById(id);
        if (tournament != null) {
            return ResponseEntity.ok(tournament);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping
    @Operation(summary = "Crear torneo", description = "Se crea en estado DRAFT por defecto")
    public ResponseEntity<TournamentModel> create(@RequestBody TournamentModel tournament) {
        TournamentModel created = tournamentService.create(tournament);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar torneo", description = "No aplica si está FINISHED")
    public ResponseEntity<Object> update(@PathVariable Long id,
                                         @RequestBody TournamentModel tournament) {
        TournamentModel existing = tournamentService.findById(id);
        if (existing == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        if (existing.getStatus() == TournamentStatus.FINISHED) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("No se puede modificar un torneo finalizado");
        }
        TournamentModel updated = tournamentService.update(id, tournament);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar torneo", description = "Solo si está en estado DRAFT")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        TournamentModel existing = tournamentService.findById(id);
        if (existing == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        if (existing.getStatus() != TournamentStatus.DRAFT) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Solo se pueden eliminar torneos en estado DRAFT");
        }
        boolean deleted = tournamentService.delete(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}
