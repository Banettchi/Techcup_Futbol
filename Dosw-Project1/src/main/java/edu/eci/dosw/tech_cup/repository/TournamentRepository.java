package edu.eci.dosw.tech_cup.repository;

import edu.eci.dosw.tech_cup.entity.Tournament;
import edu.eci.dosw.tech_cup.model.enums.TournamentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TournamentRepository extends JpaRepository<Tournament, Long> {

    List<Tournament> findByStatus(TournamentStatus status);

    List<Tournament> findByOrganizerId(Long organizerId);

    boolean existsByName(String name);
}