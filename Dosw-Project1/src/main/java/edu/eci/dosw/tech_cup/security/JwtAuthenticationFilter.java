package edu.eci.dosw.tech_cup.security;

import edu.eci.dosw.tech_cup.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final Logger log = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * a. Bearer es un esquema de autenticación HTTP que indica que el token incluido
     *    en el header Authorization es suficiente para autenticar la petición.
     *    Sirve para transmitir el JWT entre cliente y servidor de forma estándar.
     *
     * b. WebAuthenticationDetailsSource sirve para construir los detalles de autenticación
     *    a partir de la petición HTTP (como IP y session ID), enriqueciendo el token
     *    de autenticación con información del contexto de la solicitud.
     *
     * c. SecurityContextHolder es el contenedor donde Spring Security almacena
     *    la información del usuario autenticado durante el ciclo de vida de una petición.
     *    Permite que otros componentes accedan al usuario actual en cualquier momento.
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");

        // 1. Validar si existe el header
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            log.debug("Petición sin token JWT: {} {}", request.getMethod(), request.getRequestURI());
            filterChain.doFilter(request, response);
            return;
        }

        // 2. Extraer token
        String token = authHeader.substring(7);
        log.debug("Token JWT recibido para: {} {}", request.getMethod(), request.getRequestURI());

        // 3. Extraer username
        String username = jwtService.extractUsername(token);
        log.debug("Username extraído del token: {}", username);

        // 4. Validar si no está autenticado aún
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            if (jwtService.extractUsername(token).equals(userDetails.getUsername())) {
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities()
                        );
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                // 5. Registrar usuario como autenticado
                SecurityContextHolder.getContext().setAuthentication(authToken);
                log.info("Usuario '{}' autenticado exitosamente via JWT para: {} {}", username, request.getMethod(), request.getRequestURI());
            }
        }

        filterChain.doFilter(request, response);
    }
}