package edu.eci.dosw.tech_cup.service;

import edu.eci.dosw.tech_cup.dto.auth.AuthResponse;
import edu.eci.dosw.tech_cup.dto.auth.LoginRequest;
import edu.eci.dosw.tech_cup.entity.User;
import edu.eci.dosw.tech_cup.mapper.UserMapper;
import edu.eci.dosw.tech_cup.model.enums.Role;
import edu.eci.dosw.tech_cup.repository.RoleRepository;
import edu.eci.dosw.tech_cup.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class AuthService {

    private static final Logger log = LoggerFactory.getLogger(AuthService.class);

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;

    public AuthService(UserRepository userRepository, RoleRepository roleRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userMapper = userMapper;
    }

    @PostConstruct
    public void initData() {
        if (roleRepository.count() == 0) {
            edu.eci.dosw.tech_cup.entity.Role adminRole = new edu.eci.dosw.tech_cup.entity.Role("ADMIN");
            edu.eci.dosw.tech_cup.entity.Role playerRole = new edu.eci.dosw.tech_cup.entity.Role("PLAYER");
            roleRepository.save(adminRole);
            roleRepository.save(playerRole);
            log.info("Roles por defecto (ADMIN, PLAYER) creados en BD.");
        }

        if (userRepository.count() == 0) {
            User admin = new User();
            admin.setEmail("admin@mail.escuelaing.edu.co");
            admin.setPassword("admin123");
            admin.setRole(Role.ADMIN);
            admin.setName("Admin TechCup");

            User player = new User();
            player.setEmail("jugador@mail.escuelaing.edu.co");
            player.setPassword("jugador123");
            player.setRole(Role.PLAYER);
            player.setName("Jugador Demo");

            userRepository.save(admin);
            userRepository.save(player);
            log.info("Usuarios por defecto para pruebas insertados exitosamente en BD (H2/PostgreSQL).");
        }
    }

    public AuthResponse login(LoginRequest request) {
        log.debug("Intento de login para: {}", request.getEmail());
        return userRepository.findAll().stream()
                .filter(u -> u.getEmail().equals(request.getEmail())
                        && u.getPassword().equals(request.getPassword()))
                .findFirst()
                .map(u -> {
                    log.info("Login exitoso para: {}", request.getEmail());
                    return new AuthResponse(true, "Login exitoso", u.getRole().name());
                })
                .orElseGet(() -> {
                    log.warn("Login fallido para: {}", request.getEmail());
                    return new AuthResponse(false, "Credenciales inválidas", null);
                });
    }
}