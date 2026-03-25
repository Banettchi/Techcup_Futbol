package edu.eci.dosw.tech_cup.repository;

import edu.eci.dosw.tech_cup.entity.Match;
import edu.eci.dosw.tech_cup.model.enums.MatchPhase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {

    List<Match> findByTournamentId(Long tournamentId);

    List<Match> findByPhase(MatchPhase phase);

    List<Match> findByHomeTeamIdOrAwayTeamId(Long homeTeamId, Long awayTeamId);
}