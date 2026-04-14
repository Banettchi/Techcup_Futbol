package edu.eci.dosw.tech_cup.service;

import edu.eci.dosw.tech_cup.entity.Tournament;
import edu.eci.dosw.tech_cup.mapper.TournamentMapper;
import edu.eci.dosw.tech_cup.model.TournamentModel;
import edu.eci.dosw.tech_cup.model.enums.TournamentStatus;
import edu.eci.dosw.tech_cup.repository.TournamentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TournamentService {

    private static final Logger log = LoggerFactory.getLogger(TournamentService.class);

    private final TournamentRepository tournamentRepository;
    private final TournamentMapper tournamentMapper;

    public TournamentService(TournamentRepository tournamentRepository, TournamentMapper tournamentMapper) {
        this.tournamentRepository = tournamentRepository;
        this.tournamentMapper = tournamentMapper;
    }

    public List<TournamentModel> findAll() {
        log.info("Consultando todos los torneos");
        List<TournamentModel> tournaments = tournamentRepository.findAll()
                .stream()
                .map(tournamentMapper::toModel)
                .collect(Collectors.toList());
        log.info("Se encontraron {} torneos", tournaments.size());
        return tournaments;
    }

    public TournamentModel findById(Long id) {
        log.debug("Buscando torneo con ID: {}", id);
        return tournamentMapper.toModel(
                tournamentRepository.findById(id)
                        .orElseThrow(() -> {
                            log.error("Torneo con ID {} no encontrado", id);
                            return new RuntimeException("Torneo no encontrado");
                        }));
    }

    public TournamentModel create(TournamentModel tournament) {
        log.info("Creando nuevo torneo: {}", tournament.getName());
        tournament.setStatus(TournamentStatus.DRAFT);
        TournamentModel created = tournamentMapper.toModel(tournamentRepository.save(tournamentMapper.toEntity(tournament)));
        log.info("Torneo creado exitosamente con ID: {} y estado: DRAFT", created.getId());
        return created;
    }

    public TournamentModel update(Long id, TournamentModel updated) {
        log.info("Actualizando torneo con ID: {}", id);
        Tournament tournament = tournamentRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Torneo con ID {} no encontrado para actualizar", id);
                    return new RuntimeException("Torneo no encontrado");
                });
        if (tournament.getStatus() == TournamentStatus.FINISHED) {
            log.warn("Intento de modificar torneo FINALIZADO con ID: {}", id);
            throw new RuntimeException("No se puede modificar un torneo finalizado");
        }
        updated.setId(id);
        updated.setStatus(tournament.getStatus());
        TournamentModel result = tournamentMapper.toModel(tournamentRepository.save(tournamentMapper.toEntity(updated)));
        log.info("Torneo con ID {} actualizado exitosamente", id);
        return result;
    }

    public void delete(Long id) {
        log.info("Eliminando torneo con ID: {}", id);
        Tournament tournament = tournamentRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Torneo con ID {} no encontrado para eliminar", id);
                    return new RuntimeException("Torneo no encontrado");
                });
        if (tournament.getStatus() != TournamentStatus.DRAFT) {
            log.warn("Intento de eliminar torneo con estado {} (solo DRAFT permitido), ID: {}", tournament.getStatus(), id);
            throw new RuntimeException("Solo se pueden eliminar torneos en estado DRAFT");
        }
        tournamentRepository.deleteById(id);
        log.info("Torneo con ID {} eliminado exitosamente", id);
    }
}
