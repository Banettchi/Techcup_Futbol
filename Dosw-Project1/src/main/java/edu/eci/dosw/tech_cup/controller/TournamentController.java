package edu.eci.dosw.tech_cup.controller;

import edu.eci.dosw.tech_cup.model.TournamentModel;
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
        try {
            return ResponseEntity.ok(tournamentService.findById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    @Operation(summary = "Crear torneo", description = "Se crea en estado DRAFT por defecto")
    public ResponseEntity<TournamentModel> create(@RequestBody TournamentModel tournament) {
        return ResponseEntity.status(HttpStatus.CREATED).body(tournamentService.create(tournament));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar torneo", description = "No aplica si está FINISHED")
    public ResponseEntity<Object> update(@PathVariable Long id,
                                         @RequestBody TournamentModel tournament) {
        try {
            return ResponseEntity.ok(tournamentService.update(id, tournament));
        } catch (RuntimeException e) {
            if (e.getMessage().contains("No se puede modificar")) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar torneo", description = "Solo si está en estado DRAFT")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        try {
            tournamentService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            if (e.getMessage().contains("DRAFT")) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
