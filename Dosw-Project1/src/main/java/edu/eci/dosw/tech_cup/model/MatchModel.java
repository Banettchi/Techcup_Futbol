package edu.eci.dosw.tech_cup.model;

import edu.eci.dosw.tech_cup.model.enums.MatchPhase;
import java.time.LocalDateTime;

public class MatchModel {

    private Long id;
    private LocalDateTime matchDateTime;
    private String field;
    private int homeScore;
    private int awayScore;
    private MatchPhase phase;
    private TeamModel homeTeam;
    private TeamModel awayTeam;
    private TournamentModel tournament;

    public MatchModel() {}

    public MatchModel(Long id, LocalDateTime matchDateTime, String field, MatchPhase phase,
                 TeamModel homeTeam, TeamModel awayTeam, TournamentModel tournament) {
        this.id = id;
        this.matchDateTime = matchDateTime;
        this.field = field;
        this.phase = phase;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.tournament = tournament;
    }

    public void scheduleMatch() { System.out.println("Match scheduled successfully."); }

    public void registerResult(int homeScore, int awayScore) {
        this.homeScore = homeScore;
        this.awayScore = awayScore;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDateTime getMatchDateTime() { return matchDateTime; }
    public void setMatchDateTime(LocalDateTime matchDateTime) { this.matchDateTime = matchDateTime; }

    public String getField() { return field; }
    public void setField(String field) { this.field = field; }

    public int getHomeScore() { return homeScore; }
    public void setHomeScore(int homeScore) { this.homeScore = homeScore; }

    public int getAwayScore() { return awayScore; }
    public void setAwayScore(int awayScore) { this.awayScore = awayScore; }

    public MatchPhase getPhase() { return phase; }
    public void setPhase(MatchPhase phase) { this.phase = phase; }

    public TeamModel getHomeTeam() { return homeTeam; }
    public void setHomeTeam(TeamModel homeTeam) { this.homeTeam = homeTeam; }

    public TeamModel getAwayTeam() { return awayTeam; }
    public void setAwayTeam(TeamModel awayTeam) { this.awayTeam = awayTeam; }

    public TournamentModel getTournament() { return tournament; }
    public void setTournament(TournamentModel tournament) { this.tournament = tournament; }
}
