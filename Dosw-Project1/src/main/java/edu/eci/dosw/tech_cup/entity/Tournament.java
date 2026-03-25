package edu.eci.dosw.tech_cup.entity;

import edu.eci.dosw.tech_cup.model.enums.TournamentStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tournaments")
public class Tournament {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, length = 100)
    private String name;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @Positive
    @Column(name = "team_limit", nullable = false)
    private int teamLimit;

    @Positive
    @Column(name = "team_cost", nullable = false)
    private double teamCost;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private TournamentStatus status = TournamentStatus.DRAFT;

    @OneToMany(mappedBy = "tournament", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Team> teams = new ArrayList<>();

    @OneToMany(mappedBy = "tournament", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Match> matches = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organizer_id")
    private User organizer;

    public Tournament() {
    }

    public Tournament(String name, LocalDate startDate, LocalDate endDate,
                      int teamLimit, double teamCost, User organizer) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.teamLimit = teamLimit;
        this.teamCost = teamCost;
        this.organizer = organizer;
        this.status = TournamentStatus.DRAFT;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public LocalDate getStartDate() { return startDate; }
    public LocalDate getEndDate() { return endDate; }
    public int getTeamLimit() { return teamLimit; }
    public double getTeamCost() { return teamCost; }
    public TournamentStatus getStatus() { return status; }
    public List<Team> getTeams() { return teams; }
    public List<Match> getMatches() { return matches; }
    public User getOrganizer() { return organizer; }

    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }
    public void setTeamLimit(int teamLimit) { this.teamLimit = teamLimit; }
    public void setTeamCost(double teamCost) { this.teamCost = teamCost; }
    public void setStatus(TournamentStatus status) { this.status = status; }
    public void setTeams(List<Team> teams) { this.teams = teams; }
    public void setMatches(List<Match> matches) { this.matches = matches; }
    public void setOrganizer(User organizer) { this.organizer = organizer; }
}