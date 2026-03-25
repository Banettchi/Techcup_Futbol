package edu.eci.dosw.tech_cup.repository;

import edu.eci.dosw.tech_cup.entity.Team;
import edu.eci.dosw.tech_cup.model.enums.TeamStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {

    List<Team> findByTournamentId(Long tournamentId);

    List<Team> findByStatus(TeamStatus status);

    List<Team> findByCaptainId(Long captainId);

    boolean existsByNameAndTournamentId(String name, Long tournamentId);
}