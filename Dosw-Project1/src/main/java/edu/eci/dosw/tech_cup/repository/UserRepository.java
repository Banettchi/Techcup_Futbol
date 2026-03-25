package edu.eci.dosw.tech_cup.repository;

import edu.eci.dosw.tech_cup.entity.User;
import edu.eci.dosw.tech_cup.model.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    List<User> findByRole(Role role);

    List<User> findByAvailableTrue();

    boolean existsByEmail(String email);
}