package edu.eci.dosw.tech_cup.controller;

import edu.eci.dosw.tech_cup.model.AuthRequest;
import edu.eci.dosw.tech_cup.model.AuthResponse;
import edu.eci.dosw.tech_cup.service.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private static final Logger log = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * a. UsernamePasswordAuthenticationToken es una clase de Spring Security que
     *    representa una solicitud de autenticación con usuario y contraseña.
     *    Se usa para pasarle las credenciales al AuthenticationManager para que
     *    las valide contra la base de datos.
     */
    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {
        log.info("Intento de login JWT para usuario: {}", request.getUsername());
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        log.info("Autenticación exitosa para: {}", request.getUsername());

        UserDetails user = userDetailsService.loadUserByUsername(request.getUsername());
        String token = jwtService.generateToken(user);
        log.info("Token JWT generado exitosamente para: {}", request.getUsername());
        return new AuthResponse(token);
    }
}
