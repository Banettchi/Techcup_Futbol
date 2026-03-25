package edu.eci.dosw.tech_cup.entity;

import edu.eci.dosw.tech_cup.model.enums.MatchPhase;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "matches")
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "match_date_time", nullable = false)
    private LocalDateTime matchDateTime;

    @Column(nullable = false, length = 100)
    private String field;

    @Column(name = "home_score")
    private int homeScore;

    @Column(name = "away_score")
    private int awayScore;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private MatchPhase phase;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "home_team_id", nullable = false)
    private Team homeTeam;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "away_team_id", nullable = false)
    private Team awayTeam;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tournament_id", nullable = false)
    private Tournament tournament;

    public Match() {
    }

    public Match(LocalDateTime matchDateTime, String field, MatchPhase phase,
                 Team homeTeam, Team awayTeam, Tournament tournament) {
        this.matchDateTime = matchDateTime;
        this.field = field;
        this.phase = phase;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.tournament = tournament;
    }

    public Long getId() { return id; }
    public LocalDateTime getMatchDateTime() { return matchDateTime; }
    public String getField() { return field; }
    public int getHomeScore() { return homeScore; }
    public int getAwayScore() { return awayScore; }
    public MatchPhase getPhase() { return phase; }
    public Team getHomeTeam() { return homeTeam; }
    public Team getAwayTeam() { return awayTeam; }
    public Tournament getTournament() { return tournament; }

    public void setId(Long id) { this.id = id; }
    public void setMatchDateTime(LocalDateTime matchDateTime) { this.matchDateTime = matchDateTime; }
    public void setField(String field) { this.field = field; }
    public void setHomeScore(int homeScore) { this.homeScore = homeScore; }
    public void setAwayScore(int awayScore) { this.awayScore = awayScore; }
    public void setPhase(MatchPhase phase) { this.phase = phase; }
    public void setHomeTeam(Team homeTeam) { this.homeTeam = homeTeam; }
    public void setAwayTeam(Team awayTeam) { this.awayTeam = awayTeam; }
    public void setTournament(Tournament tournament) { this.tournament = tournament; }
}