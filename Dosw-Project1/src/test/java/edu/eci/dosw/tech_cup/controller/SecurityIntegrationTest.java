package edu.eci.dosw.tech_cup.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class SecurityIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    // Prueba 1: Login exitoso → debe retornar token
    @Test
    public void loginExitoso_retornaToken() throws Exception {
        String body = """
                {
                    "username": "admin@mail.escuelaing.edu.co",
                    "password": "admin123"
                }
                """;

        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").exists());
    }

    // Prueba 2: Login fallido → debe retornar 401
    @Test
    public void loginFallido_retorna401() throws Exception {
        String body = """
                {
                    "username": "admin@mail.escuelaing.edu.co",
                    "password": "contraseñaMal"
                }
                """;

        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isForbidden());
    }

    // Prueba 3: GET con token válido → debe retornar 200
    @Test
    public void getTournamentsConToken_retorna200() throws Exception {
        // Primero obtenemos el token
        String body = """
                {
                    "username": "admin@mail.escuelaing.edu.co",
                    "password": "admin123"
                }
                """;

        MvcResult result = mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andReturn();

        String response = result.getResponse().getContentAsString();
        String token = response.replace("{\"token\":\"", "").replace("\"}", "");

        // Usamos el token para acceder al endpoint protegido
        mockMvc.perform(get("/api/tournaments")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk());
    }

    // Prueba 4: GET sin token → debe retornar 403
    @Test
    public void getTournamentsSinToken_retorna403() throws Exception {
        mockMvc.perform(get("/api/tournaments"))
                .andExpect(status().isForbidden());
    }
}