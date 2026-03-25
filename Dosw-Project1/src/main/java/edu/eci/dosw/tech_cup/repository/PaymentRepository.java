package edu.eci.dosw.tech_cup.repository;

import edu.eci.dosw.tech_cup.entity.Payment;
import edu.eci.dosw.tech_cup.model.enums.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    Optional<Payment> findByTeamId(Long teamId);

    List<Payment> findByStatus(PaymentStatus status);
}