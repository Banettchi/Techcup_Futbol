package edu.eci.dosw.tech_cup.service;

import edu.eci.dosw.tech_cup.entity.Tournament;
import edu.eci.dosw.tech_cup.mapper.TournamentMapper;
import edu.eci.dosw.tech_cup.model.TournamentModel;
import edu.eci.dosw.tech_cup.model.enums.TournamentStatus;
import edu.eci.dosw.tech_cup.repository.TournamentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TournamentService {

    private final TournamentRepository tournamentRepository;
    private final TournamentMapper tournamentMapper;

    public TournamentService(TournamentRepository tournamentRepository, TournamentMapper tournamentMapper) {
        this.tournamentRepository = tournamentRepository;
        this.tournamentMapper = tournamentMapper;
    }

    public List<TournamentModel> findAll() {
        return tournamentRepository.findAll()
                .stream()
                .map(tournamentMapper::toModel)
                .collect(Collectors.toList());
    }

    public TournamentModel findById(Long id) {
        return tournamentMapper.toModel(
                tournamentRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Torneo no encontrado")));
    }

    public TournamentModel create(TournamentModel tournament) {
        tournament.setStatus(TournamentStatus.DRAFT);
        return tournamentMapper.toModel(tournamentRepository.save(tournamentMapper.toEntity(tournament)));
    }

    public TournamentModel update(Long id, TournamentModel updated) {
        Tournament tournament = tournamentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Torneo no encontrado"));
        if (tournament.getStatus() == TournamentStatus.FINISHED) {
            throw new RuntimeException("No se puede modificar un torneo finalizado");
        }
        updated.setId(id);
        updated.setStatus(tournament.getStatus());
        return tournamentMapper.toModel(tournamentRepository.save(tournamentMapper.toEntity(updated)));
    }

    public void delete(Long id) {
        Tournament tournament = tournamentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Torneo no encontrado"));
        if (tournament.getStatus() != TournamentStatus.DRAFT) {
            throw new RuntimeException("Solo se pueden eliminar torneos en estado DRAFT");
        }
        tournamentRepository.deleteById(id);
    }
}