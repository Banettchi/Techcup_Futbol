package edu.eci.dosw.tech_cup.entity;

import edu.eci.dosw.tech_cup.model.enums.TeamStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "teams")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, length = 100)
    private String name;

    @Column(name = "badge_url")
    private String badgeUrl;

    @Column(name = "uniform_color", length = 50)
    private String uniformColor;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private TeamStatus status = TeamStatus.PENDING_REGISTRATION;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "captain_id")
    private User captain;

    @OneToMany(mappedBy = "team", fetch = FetchType.LAZY)
    private List<User> players = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tournament_id")
    private Tournament tournament;

    @OneToOne(mappedBy = "team", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Payment payment;

    public Team() {
    }

    public Team(String name, String badgeUrl, String uniformColor, User captain) {
        this.name = name;
        this.badgeUrl = badgeUrl;
        this.uniformColor = uniformColor;
        this.captain = captain;
        this.status = TeamStatus.PENDING_REGISTRATION;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getBadgeUrl() { return badgeUrl; }
    public String getUniformColor() { return uniformColor; }
    public TeamStatus getStatus() { return status; }
    public User getCaptain() { return captain; }
    public List<User> getPlayers() { return players; }
    public Tournament getTournament() { return tournament; }
    public Payment getPayment() { return payment; }

    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setBadgeUrl(String badgeUrl) { this.badgeUrl = badgeUrl; }
    public void setUniformColor(String uniformColor) { this.uniformColor = uniformColor; }
    public void setStatus(TeamStatus status) { this.status = status; }
    public void setCaptain(User captain) { this.captain = captain; }
    public void setPlayers(List<User> players) { this.players = players; }
    public void setTournament(Tournament tournament) { this.tournament = tournament; }
    public void setPayment(Payment payment) { this.payment = payment; }
}