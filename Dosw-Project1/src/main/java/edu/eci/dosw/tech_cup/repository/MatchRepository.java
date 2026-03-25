package edu.eci.dosw.tech_cup.repository;

import edu.eci.dosw.tech_cup.entity.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {
}
