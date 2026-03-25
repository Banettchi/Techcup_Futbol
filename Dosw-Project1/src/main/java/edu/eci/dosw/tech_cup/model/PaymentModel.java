package edu.eci.dosw.tech_cup.model;

import edu.eci.dosw.tech_cup.model.enums.PaymentStatus;
import java.time.LocalDate;

public class PaymentModel {

    private Long id;
    private String receiptUrl;
    private LocalDate registrationDate;
    private PaymentStatus status;
    private TeamModel team;

    public PaymentModel() { this.status = PaymentStatus.PENDING; }

    public PaymentModel(Long id, String receiptUrl, LocalDate registrationDate, TeamModel team) {
        this.id = id;
        this.receiptUrl = receiptUrl;
        this.registrationDate = registrationDate;
        this.team = team;
        this.status = PaymentStatus.PENDING;
    }

    public void registerPayment() { System.out.println("Payment registered successfully."); }
    public void approvePayment() { this.status = PaymentStatus.APPROVED; }
    public void rejectPayment() { this.status = PaymentStatus.REJECTED; }
    public void setInReview() { this.status = PaymentStatus.IN_REVIEW; }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getReceiptUrl() { return receiptUrl; }
    public void setReceiptUrl(String receiptUrl) { this.receiptUrl = receiptUrl; }

    public LocalDate getRegistrationDate() { return registrationDate; }
    public void setRegistrationDate(LocalDate registrationDate) { this.registrationDate = registrationDate; }

    public PaymentStatus getStatus() { return status; }
    public void setStatus(PaymentStatus status) { this.status = status; }

    public TeamModel getTeam() { return team; }
    public void setTeam(TeamModel team) { this.team = team; }
}
