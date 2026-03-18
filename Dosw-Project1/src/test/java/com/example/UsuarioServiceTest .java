package com.techcup.service;

import com.techcup.model.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioServiceTest {

    private UsuarioService usuarioService;

    @BeforeEach
    void setUp() {
        usuarioService = new UsuarioService();
    }

    // HU-01: Registro de usuario con rol
    @Test
    void debeRegistrarUnUsuarioNuevoCorrectamente() {
        Usuario usuario = usuarioService.registrarUsuario("Juan", "juan@escuela.edu.co", "Pass123", "ESTUDIANTE");

        assertNotNull(usuario);
        assertEquals("Juan", usuario.getNombre());
        assertEquals("juan@escuela.edu.co", usuario.getCorreo());
        assertEquals("ESTUDIANTE", usuario.getRol());
        assertTrue(usuario.isActivo());
    }

    @Test
    void debeLanzarExcepcionSiCorreoYaExiste() {
        usuarioService.registrarUsuario("Juan", "juan@escuela.edu.co", "Pass123", "ESTUDIANTE");

        assertThrows(IllegalArgumentException.class, () ->
                usuarioService.registrarUsuario("Pedro", "juan@escuela.edu.co", "Pass456", "ESTUDIANTE")
        );
    }

    // HU-02: Autenticación de usuario
    @Test
    void debeAutenticarUsuarioConCredencialesValidas() {
        usuarioService.registrarUsuario("Juan", "juan@escuela.edu.co", "Pass123", "ESTUDIANTE");

        String token = usuarioService.autenticar("juan@escuela.edu.co", "Pass123");

        assertNotNull(token);
        assertFalse(token.isEmpty());
    }

    @Test
    void debeLanzarExcepcionSiContrasenaEsIncorrecta() {
        usuarioService.registrarUsuario("Juan", "juan@escuela.edu.co", "Pass123", "ESTUDIANTE");

        assertThrows(IllegalArgumentException.class, () ->
                usuarioService.autenticar("juan@escuela.edu.co", "ContrasenaWrong")
        );
    }

    // HU-03: Creación de perfil deportivo
    @Test
    void debeCrearPerfilDeportivoCorrectamente() {
        Usuario usuario = usuarioService.registrarUsuario("Juan", "juan@escuela.edu.co", "Pass123", "ESTUDIANTE");

        var perfil = usuarioService.crearPerfilDeportivo(usuario, "delantero", 9, true);

        assertNotNull(perfil);
        assertEquals("delantero", perfil.getPosicion());
        assertEquals(9, perfil.getNumeroDorsal());
        assertTrue(perfil.isDisponible());
    }
}
