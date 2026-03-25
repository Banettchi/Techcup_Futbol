package edu.eci.dosw.tech_cup.entity;

import edu.eci.dosw.tech_cup.model.enums.PaymentStatus;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "receipt_url")
    private String receiptUrl;

    @Column(name = "registration_date", nullable = false)
    private LocalDate registrationDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private PaymentStatus status = PaymentStatus.PENDING;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id", nullable = false, unique = true)
    private Team team;

    public Payment() {
        this.status = PaymentStatus.PENDING;
    }

    public Payment(String receiptUrl, LocalDate registrationDate, Team team) {
        this.receiptUrl = receiptUrl;
        this.registrationDate = registrationDate;
        this.team = team;
        this.status = PaymentStatus.PENDING;
    }

    public Long getId() { return id; }
    public String getReceiptUrl() { return receiptUrl; }
    public LocalDate getRegistrationDate() { return registrationDate; }
    public PaymentStatus getStatus() { return status; }
    public Team getTeam() { return team; }

    public void setId(Long id) { this.id = id; }
    public void setReceiptUrl(String receiptUrl) { this.receiptUrl = receiptUrl; }
    public void setRegistrationDate(LocalDate registrationDate) { this.registrationDate = registrationDate; }
    public void setStatus(PaymentStatus status) { this.status = status; }
    public void setTeam(Team team) { this.team = team; }
}