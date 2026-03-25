package edu.eci.dosw.tech_cup.entity;

import edu.eci.dosw.tech_cup.model.enums.ParticipantType;
import edu.eci.dosw.tech_cup.model.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, length = 100)
    private String name;

    @Email
    @NotBlank
    @Column(nullable = false, unique = true, length = 150)
    private String email;

    @NotBlank
    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private Role role;

    @Enumerated(EnumType.STRING)
    @Column(name = "participant_type", nullable = false, length = 30)
    private ParticipantType participantType;

    @Column(name = "jersey_number", length = 10)
    private String jerseyNumber;

    @Column(length = 50)
    private String position;

    @Column(name = "photo_url")
    private String photoUrl;

    @Column(nullable = false)
    private boolean available = true;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    public User() {
    }

    public User(String name, String email, String password, Role role,
                ParticipantType participantType, String jerseyNumber,
                String position, String photoUrl, boolean available) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.participantType = participantType;
        this.jerseyNumber = jerseyNumber;
        this.position = position;
        this.photoUrl = photoUrl;
        this.available = available;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public Role getRole() { return role; }
    public ParticipantType getParticipantType() { return participantType; }
    public String getJerseyNumber() { return jerseyNumber; }
    public String getPosition() { return position; }
    public String getPhotoUrl() { return photoUrl; }
    public boolean isAvailable() { return available; }
    public Team getTeam() { return team; }

    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setPassword(String password) { this.password = password; }
    public void setRole(Role role) { this.role = role; }
    public void setParticipantType(ParticipantType participantType) { this.participantType = participantType; }
    public void setJerseyNumber(String jerseyNumber) { this.jerseyNumber = jerseyNumber; }
    public void setPosition(String position) { this.position = position; }
    public void setPhotoUrl(String photoUrl) { this.photoUrl = photoUrl; }
    public void setAvailable(boolean available) { this.available = available; }
    public void setTeam(Team team) { this.team = team; }
}