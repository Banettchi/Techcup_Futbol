package edu.eci.dosw.tech_cup.service;

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
        log.debug("Obteniendo todos los torneos");
        return tournamentRepository.findAll()
                .stream()
                .map(tournamentMapper::toModel)
                .collect(Collectors.toList());
    }

    public TournamentModel findById(Long id) {
        log.debug("Buscando torneo con id: {}", id);
        return tournamentRepository.findById(id)
                .map(tournamentMapper::toModel)
                .orElse(null);
    }

    public TournamentModel create(TournamentModel tournament) {
        log.debug("Creando torneo: {}", tournament.getName());
        tournament.setStatus(TournamentStatus.DRAFT);
        TournamentModel saved = tournamentMapper.toModel(tournamentRepository.save(tournamentMapper.toEntity(tournament)));
        log.info("Torneo creado con id: {}", saved.getId());
        return saved;
    }

    public TournamentModel update(Long id, TournamentModel updated) {
        log.debug("Actualizando torneo con id: {}", id);
        return tournamentRepository.findById(id).map(existing -> {
            if (existing.getStatus() == TournamentStatus.FINISHED) {
                log.warn("No se puede modificar torneo finalizado con id: {}", id);
                return null;
            }
            updated.setId(id);
            updated.setStatus(existing.getStatus());
            TournamentModel saved = tournamentMapper.toModel(tournamentRepository.save(tournamentMapper.toEntity(updated)));
            log.info("Torneo actualizado con id: {}", id);
            return saved;
        }).orElse(null);
    }

    public boolean delete(Long id) {
        log.debug("Eliminando torneo con id: {}", id);
        return tournamentRepository.findById(id).map(existing -> {
            if (existing.getStatus() == TournamentStatus.DRAFT) {
                tournamentRepository.deleteById(id);
                log.info("Torneo eliminado con id: {}", id);
                return true;
            }
            log.warn("No se puede eliminar torneo que no está en DRAFT con id: {}", id);
            return false;
        }).orElse(false);
    }
}