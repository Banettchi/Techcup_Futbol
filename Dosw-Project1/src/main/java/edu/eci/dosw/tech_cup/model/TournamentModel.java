package edu.eci.dosw.tech_cup.model;

import edu.eci.dosw.tech_cup.model.enums.TournamentStatus;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TournamentModel {

    private Long id;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private int teamLimit;
    private double teamCost;
    private TournamentStatus status;
    private List<TeamModel> teams;
    private List<MatchModel> matches;

    public TournamentModel() {
        this.teams = new ArrayList<>();
        this.matches = new ArrayList<>();
        this.status = TournamentStatus.DRAFT;
    }

    public TournamentModel(Long id, String name, LocalDate startDate, LocalDate endDate,
                      int teamLimit, double teamCost) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.teamLimit = teamLimit;
        this.teamCost = teamCost;
        this.status = TournamentStatus.DRAFT;
        this.teams = new ArrayList<>();
        this.matches = new ArrayList<>();
    }

    public void createTournament() {
        System.out.println("Tournament " + name + " created successfully.");
    }

    public void activateTournament() { this.status = TournamentStatus.ACTIVE; }
    public void startTournament() { this.status = TournamentStatus.IN_PROGRESS; }
    public void finishTournament() { this.status = TournamentStatus.FINISHED; }

    public boolean addTeam(TeamModel team) {
        if (teams.size() >= teamLimit) return false;
        teams.add(team);
        team.setTournament(this);
        return true;
    }

    public void scheduleMatch(MatchModel match) { matches.add(match); }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }

    public int getTeamLimit() { return teamLimit; }
    public void setTeamLimit(int teamLimit) { this.teamLimit = teamLimit; }

    public double getTeamCost() { return teamCost; }
    public void setTeamCost(double teamCost) { this.teamCost = teamCost; }

    public TournamentStatus getStatus() { return status; }
    public void setStatus(TournamentStatus status) { this.status = status; }

    public List<TeamModel> getTeams() { return teams; }
    public void setTeams(List<TeamModel> teams) { this.teams = teams; }

    public List<MatchModel> getMatches() { return matches; }
    public void setMatches(List<MatchModel> matches) { this.matches = matches; }
}
