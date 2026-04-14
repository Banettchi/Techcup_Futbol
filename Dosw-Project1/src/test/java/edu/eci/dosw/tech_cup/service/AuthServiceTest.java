package edu.eci.dosw.tech_cup.service;

import edu.eci.dosw.tech_cup.dto.auth.AuthResponse;
import edu.eci.dosw.tech_cup.dto.auth.LoginRequest;
import edu.eci.dosw.tech_cup.entity.User;
import edu.eci.dosw.tech_cup.mapper.UserMapper;
import edu.eci.dosw.tech_cup.model.enums.Role;
import edu.eci.dosw.tech_cup.repository.RoleRepository;
import edu.eci.dosw.tech_cup.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private AuthService authService;

    private User testUser;

    @BeforeEach
    void setUp() {
        testUser = new User();
        testUser.setEmail("test@mail.com");
        testUser.setPassword("password123"); // sin encode porque AuthService compara plano
        testUser.setRole(Role.PLAYER);
    }

    // TEST 1: Login exitoso con credenciales correctas
    @Test
    void login_credencialesCorrectas_retornaExito() {
        when(userRepository.findAll()).thenReturn(List.of(testUser));

        LoginRequest request = new LoginRequest();
        request.setEmail("test@mail.com");
        request.setPassword("password123");

        AuthResponse response = authService.login(request);

        assertTrue(response.isSuccess());
        assertEquals("Login exitoso", response.getMessage());
        assertEquals("PLAYER", response.getRole());
    }

    // TEST 2: Login fallido con contraseña incorrecta
    @Test
    void login_contrasenaIncorrecta_retornaFallo() {
        when(userRepository.findAll()).thenReturn(List.of(testUser));

        LoginRequest request = new LoginRequest();
        request.setEmail("test@mail.com");
        request.setPassword("claveWrong");

        AuthResponse response = authService.login(request);

        assertFalse(response.isSuccess());
        assertEquals("Credenciales inválidas", response.getMessage());
        assertNull(response.getRole());
    }

    // TEST 3: Login fallido con email que no existe
    @Test
    void login_emailNoExiste_retornaFallo() {
        when(userRepository.findAll()).thenReturn(List.of(testUser));

        LoginRequest request = new LoginRequest();
        request.setEmail("noexiste@mail.com");
        request.setPassword("password123");

        AuthResponse response = authService.login(request);

        assertFalse(response.isSuccess());
        assertNull(response.getRole());
    }
}