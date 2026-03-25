package edu.eci.dosw.tech_cup.repository;

import edu.eci.dosw.tech_cup.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
}
