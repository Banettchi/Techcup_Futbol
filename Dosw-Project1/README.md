# TECHCUP FÚTBOL — Football Tournament Management System

Web platform for centralized management of the semester football tournament for the Software Engineering, Artificial Intelligence Engineering, Cybersecurity Engineering, and Statistical Engineering programs at Escuela Colombiana de Ingeniería Julio Garavito.

---

## Team Members

| Member | GitHub | Contact |
|--------|--------|---------|
| Juan Angel Salas Gomez | @Juanangels1403 | juan.salas@mail.escuelaing.edu.co |
| Maria Juliana Rodriguez Caicedo | @JuliRodC | maria.rodriguez@mail.escuelaing.edu.co |
| Kevyn Daniel Forero Gonzalez | @kevyn1005 | kevyn.forero@mail.escuelaing.edu.co |
| Diego Alejandro Montes Bonilla | @Banettchi | diego.montes@mail.escuelaing.edu.co |

---

## Project Structure

```
Dosw-Project1/
├── pom.xml
└── src/
    ├── main/
    │   ├── java/edu/eci/dosw/tech_cup/
    │   │   ├── TechCupApplication.java
    │   │   ├── config/
    │   │   │   └── SwaggerConfig.java
    │   │   ├── controller/
    │   │   │   ├── AuthController.java
    │   │   │   ├── TournamentController.java
    │   │   │   └── UserController.java
    │   │   ├── dto/
    │   │   │   └── auth/
    │   │   │       ├── AuthResponse.java
    │   │   │       └── LoginRequest.java
    │   │   ├── entity/
    │   │   │   ├── Match.java
    │   │   │   ├── Payment.java
    │   │   │   ├── Team.java
    │   │   │   ├── Tournament.java
    │   │   │   └── User.java
    │   │   ├── mapper/
    │   │   │   ├── MatchMapper.java
    │   │   │   ├── PaymentMapper.java
    │   │   │   ├── TeamMapper.java
    │   │   │   ├── TournamentMapper.java
    │   │   │   └── UserMapper.java
    │   │   ├── model/
    │   │   │   ├── enums/
    │   │   │   │   ├── MatchPhase.java
    │   │   │   │   ├── ParticipantType.java
    │   │   │   │   ├── PaymentStatus.java
    │   │   │   │   ├── Role.java
    │   │   │   │   ├── TeamStatus.java
    │   │   │   │   └── TournamentStatus.java
    │   │   │   ├── Match.java
    │   │   │   ├── Payment.java
    │   │   │   ├── Team.java
    │   │   │   ├── Tournament.java
    │   │   │   └── User.java
    │   │   ├── repository/
    │   │   │   ├── MatchRepository.java
    │   │   │   ├── PaymentRepository.java
    │   │   │   ├── TeamRepository.java
    │   │   │   ├── TournamentRepository.java
    │   │   │   └── UserRepository.java
    │   │   └── service/
    │   │       ├── AuthService.java
    │   │       ├── TournamentService.java
    │   │       └── UserService.java
    │   └── resources/
    │       └── application.properties
    └── test/java/edu/eci/dosw/tech_cup/
        ├── repository/
        │   └── UserRepositoryTest.java
        └── service/
            ├── AuthServiceTest.java
            ├── TournamentServiceTest.java
            └── UserServiceTest.java
└── docs/
    ├── uml/
    ├── images/
    ├── requirements/
    └── planning/
```

---

## Technologies

| Layer | Technology | Version |
|-------|------------|---------|
| Backend | Spring Boot | 3.2.3 |
| Language | Java | 21 |
| Build | Apache Maven | 3.x |
| Persistence | Spring Data JPA / Hibernate | 3.2.3 / 6.4.4 |
| Mapping | MapStruct | 1.6.3 |
| Database | PostgreSQL | latest |
| Container | Docker | - |
| API Docs | SpringDoc OpenAPI | 2.6.0 |
| Testing | JUnit Jupiter + Mockito | 5.x |

---

## Requirements

- Java 21
- Apache Maven 3.9+
- Docker (to run PostgreSQL)

---

## Compilation and Execution

```bash
# Compile the project
mvn clean compile

# Run tests
mvn test

# Start the application
mvn spring-boot:run
```

Swagger UI available at:
```
http://localhost:8080/swagger-ui/index.html
```

---

## Database Setup (Docker)

Start a PostgreSQL container with:

```bash
docker run --name postgres-techcup \
  -e POSTGRES_DB=techcup_db \
  -e POSTGRES_USER=postgres \
  -e POSTGRES_PASSWORD=postgres \
  -p 5432:5432 \
  -d postgres
```

The `application.properties` is configured to connect to this container:

```properties
spring.application.name=tech-cup
server.port=8080

spring.datasource.url=jdbc:postgresql://localhost:5432/techcup_db
spring.datasource.username=postgres
spring.datasource.password=postgres
spring.datasource.driver-class-name=org.postgresql.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect

logging.file.name=logs/tech-cup.log
```

> Make sure the Docker container is running (green dot in Docker Desktop) before starting the application.

---

## Architecture

This project migrates from in-memory storage to real PostgreSQL persistence, following a clean layered architecture:

| Layer | Package | Description |
|-------|---------|-------------|
| Entity | `entity/` | JPA-annotated classes mapped to database tables |
| Model | `model/` | Pure domain classes with business logic |
| Mapper | `mapper/` | MapStruct interfaces for Entity ↔ Model conversion |
| Repository | `repository/` | Spring Data JPA interfaces for database access |
| Service | `service/` | Business logic, uses repositories and mappers |
| Controller | `controller/` | REST endpoints, returns `ResponseEntity` |

Services and controllers work exclusively with **model** classes. The **entity** layer is only accessed through repositories. MapStruct handles the conversion between both layers at compile time.

---

## Domain Entities

The following entities were selected because they cover the three core functionalities the system requires: authentication, user management, and tournament management.

---

### Role

**Table:** `roles`

Represents the different user types in the system. Each role defines what a person can see and do within the platform. Supported roles: Student, Graduate, Professor, Administrative Staff, Family Member, Captain, Organizer, Referee, and Administrator.

**Key attributes:** unique identifier, role name.

---

### User

**Table:** `users`

Represents any registered person in the system. Stores personal data, credentials, and the role that determines their permissions.

This entity is the core of the authentication system: when someone logs in, the system looks up their email, validates the password, and loads the associated role to determine access.

**Key attributes:** unique identifier, full name, email, password (encrypted), availability status, participant type, and assigned role.

---

### Tournament

**Table:** `tournaments`

Represents the football tournament itself. Stores all the information the Organizer defines when creating it, and tracks its state throughout the semester.

The tournament state changes over time: starts as *Draft* during configuration, becomes *Active* when registration opens, moves to *In Progress* during the competition, and ends as *Finished*.

**Key attributes:** unique identifier, start date, end date, registration cost per team, maximum number of teams, current status, and responsible organizer.

---

### Team

**Table:** `teams`

Represents a competing team. A captain creates the team, invites players, and submits the registration payment.

**Key attributes:** unique identifier, name, badge URL, uniform color, status, captain, list of players, and associated tournament.

---

### Match

**Table:** `matches`

Represents a scheduled game between two teams within a tournament. Stores the result once played.

**Key attributes:** unique identifier, date and time, field, home score, away score, phase (group stage / quarterfinals / semifinals / final), home team, away team, and tournament.

---

### Payment

**Table:** `payments`

Represents the registration payment submitted by a captain. The organizer reviews and approves or rejects it.

**Key attributes:** unique identifier, receipt URL, registration date, status (Pending / In Review / Approved / Rejected), and associated team.

---

### Entity Relationships

| Relationship | Type | Description |
|---|---|---|
| `User` → `Tournament` | `@ManyToOne` | An organizer can manage multiple tournaments |
| `User` → `Team` (player) | `@ManyToOne` | A player belongs to one team |
| `User` → `Team` (captain) | `@ManyToOne` | A team has one captain |
| `Tournament` → `Team` | `@OneToMany` | A tournament has multiple teams |
| `Tournament` → `Match` | `@OneToMany` | A tournament has multiple matches |
| `Team` → `Match` (×2) | `@ManyToOne` | Each match has a home and away team |
| `Team` → `Payment` | `@OneToOne` | Each team has exactly one payment |

---

## Testing

### Service Tests (Mockito)

Service tests mock the repository layer to avoid requiring a real database:

```java
@BeforeEach
void setUp() {
    userRepository = Mockito.mock(UserRepository.class);
    userMapper = Mockito.mock(UserMapper.class);
    userService = new UserService(userRepository, userMapper);
}

@Test
void shouldThrowExceptionWhenUserNotFound() {
    when(userRepository.findById(999L)).thenReturn(Optional.empty());
    assertThrows(RuntimeException.class, () -> userService.findById(999L));
}
```

### Repository Tests (H2)

Repository tests use an in-memory H2 database with the `test` profile:

```java
@DataJpaTest
@ActiveProfiles("test")
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    void shouldSaveAndFindUser() {
        User user = new User();
        user.setEmail("test@test.com");
        userRepository.save(user);
        assertFalse(userRepository.findAll().isEmpty());
    }
}
```

Test profile configuration (`src/test/resources/application-test.properties`):

```properties
spring.datasource.url=jdbc:h2:mem:testdb
spring.jpa.hibernate.ddl-auto=create-drop
```

---

## Git Branching Strategy

Each feature was developed in isolation and merged via Pull Requests to `develop`:

| Branch | Content |
|--------|---------|
| `feature/erd` | Entity-Relationship diagram |
| `feature/database-config` | PostgreSQL configuration and Maven dependencies |
| `feature/entities-selection` | Domain entity selection and justification |
| `feature/entities` | JPA entity classes |
| `feature/relationships` | Entity relationships (`@ManyToOne`, `@OneToMany`, etc.) |
| `feature/repositories` | Spring Data JPA repository interfaces |
| `feature/mapstruct` | Entities, Models, and MapStruct mappers |
| `feature/services` | Repositories, Services, and Controllers |
| `feature/repository-tests` | JPA repository tests |