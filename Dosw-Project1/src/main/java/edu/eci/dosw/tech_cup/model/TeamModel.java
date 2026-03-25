package edu.eci.dosw.tech_cup.model;

import edu.eci.dosw.tech_cup.model.enums.Role;
import edu.eci.dosw.tech_cup.model.enums.TeamStatus;
import java.util.ArrayList;
import java.util.List;

public class TeamModel {

    private Long id;
    private String name;
    private String badgeUrl;
    private String uniformColor;
    private TeamStatus status;
    private UserModel captain;
    private List<UserModel> players;
    private TournamentModel tournament;
    private PaymentModel payment;

    public TeamModel() {
        this.players = new ArrayList<>();
        this.status = TeamStatus.PENDING_REGISTRATION;
    }

    public TeamModel(Long id, String name, String badgeUrl, String uniformColor, UserModel captain) {
        this.id = id;
        this.name = name;
        this.badgeUrl = badgeUrl;
        this.uniformColor = uniformColor;
        this.captain = captain;
        this.players = new ArrayList<>();
        this.status = TeamStatus.PENDING_REGISTRATION;
    }

    public void createTeam() {
        System.out.println("Team " + name + " created successfully.");
    }

    public boolean addPlayer(UserModel player) {
        if (player == null) return false;
        if (players.size() >= 12) { System.out.println("Maximum number of players reached."); return false; }
        if (player.getTeam() != null) { System.out.println("Player already belongs to another team."); return false; }
        players.add(player);
        player.joinTeam(this);
        return true;
    }

    public boolean removePlayer(UserModel player) {
        if (players.remove(player)) { player.leaveTeam(); return true; }
        return false;
    }

    public boolean validatePlayerCount() { return players.size() >= 7 && players.size() <= 12; }

    public void assignCaptain(UserModel captain) {
        this.captain = captain;
        if (captain != null) captain.setRole(Role.CAPTAIN);
    }

    public int getPlayerCount() { return players.size(); }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getBadgeUrl() { return badgeUrl; }
    public void setBadgeUrl(String badgeUrl) { this.badgeUrl = badgeUrl; }

    public String getUniformColor() { return uniformColor; }
    public void setUniformColor(String uniformColor) { this.uniformColor = uniformColor; }

    public TeamStatus getStatus() { return status; }
    public void setStatus(TeamStatus status) { this.status = status; }

    public UserModel getCaptain() { return captain; }
    public void setCaptain(UserModel captain) { this.captain = captain; }

    public List<UserModel> getPlayers() { return players; }
    public void setPlayers(List<UserModel> players) { this.players = players; }

    public TournamentModel getTournament() { return tournament; }
    public void setTournament(TournamentModel tournament) { this.tournament = tournament; }

    public PaymentModel getPayment() { return payment; }
    public void setPayment(PaymentModel payment) { this.payment = payment; }
}
