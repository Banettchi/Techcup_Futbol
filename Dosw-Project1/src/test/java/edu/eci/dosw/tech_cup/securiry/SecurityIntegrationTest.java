package edu.eci.dosw.tech_cup.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.eci.dosw.tech_cup.entity.User;
import edu.eci.dosw.tech_cup.model.enums.ParticipantType;
import edu.eci.dosw.tech_cup.model.enums.Role;
import edu.eci.dosw.tech_cup.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class SecurityIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ObjectMapper objectMapper;

    private static final String TEST_EMAIL = "test@mail.com";
    private static final String TEST_PASSWORD = "password123";

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();

        User user = new User();
        user.setName("Test User");
        user.setEmail(TEST_EMAIL);
        user.setPassword(passwordEncoder.encode(TEST_PASSWORD));
        // loadUserByUsername usa el enum Role, así que debe estar seteado
        user.setRole(Role.PLAYER);
        user.setParticipantType(ParticipantType.STUDENT);
        user.setAvailable(true);
        userRepository.save(user);
    }

    // -------------------------------------------------------
    // TEST 1: Login exitoso → retorna token JWT
    // -------------------------------------------------------
    @Test
    void loginExitoso_debeRetornarTokenJWT() throws Exception {
        Map<String, String> credentials = Map.of(
                "username", TEST_EMAIL,
                "password", TEST_PASSWORD
        );

        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(credentials)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").exists())
                .andExpect(jsonPath("$.token").isNotEmpty());
    }

    // -------------------------------------------------------
    // TEST 2: Login fallido → contraseña incorrecta
    // -------------------------------------------------------
    @Test
    void loginFallido_contrasenaIncorrecta_debeFallar() throws Exception {
        Map<String, String> credentials = Map.of(
                "username", TEST_EMAIL,
                "password", "claveEquivocada"
        );

        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(credentials)))
                .andExpect(status().is4xxClientError());
    }

    // -------------------------------------------------------
    // TEST 3: Acceder a /api/users con token válido → éxito
    // -------------------------------------------------------
    @Test
    void accesoConTokenValido_debeRetornarUsuarios() throws Exception {
        // Paso 1: obtener token haciendo login
        Map<String, String> credentials = Map.of(
                "username", TEST_EMAIL,
                "password", TEST_PASSWORD
        );

        MvcResult loginResult = mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(credentials)))
                .andExpect(status().isOk())
                .andReturn();

        // Paso 2: extraer el token del JSON de respuesta
        String responseBody = loginResult.getResponse().getContentAsString();
        String token = objectMapper.readTree(responseBody).get("token").asText();

        // Paso 3: usar el token para acceder al endpoint protegido
        mockMvc.perform(get("/api/users")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk());
    }

    // -------------------------------------------------------
    // TEST 4: Acceder a /api/users sin token → debe rechazar
    // -------------------------------------------------------
    @Test
    void accesoSinToken_debeRechazarPeticion() throws Exception {
        mockMvc.perform(get("/api/users"))
                .andExpect(status().is4xxClientError());
    }
}