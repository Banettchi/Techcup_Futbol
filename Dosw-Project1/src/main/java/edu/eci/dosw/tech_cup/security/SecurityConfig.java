package edu.eci.dosw.tech_cup.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * a. @EnableWebSecurity es una anotación que activa la configuración de seguridad web
 *    de Spring Security, permitiendo personalizar cómo se protegen los endpoints
 *    de la aplicación.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtAuthenticationFilter jwtFilter;

    /**
     * a. AuthenticationManager es la interfaz principal de Spring Security encargada
     *    de procesar una solicitud de autenticación. Recibe las credenciales del usuario
     *    y determina si son válidas delegando en los proveedores de autenticación configurados.
     */
    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    /**
     * a. PasswordEncoder es una interfaz de Spring Security que define el contrato
     *    para codificar contraseñas, evitando almacenarlas en texto plano.
     *
     * b. BCryptPasswordEncoder es una implementación de PasswordEncoder que usa el
     *    algoritmo BCrypt para hashear contraseñas de forma segura, aplicando
     *    un salt aleatorio en cada codificación.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * i. http.csrf es la protección contra ataques Cross-Site Request Forgery.
     *    Se deshabilita porque la API REST usa JWT para autenticación sin estado (stateless),
     *    por lo que no necesita protección CSRF basada en sesiones.
     *
     * ii. Esa línea configura qué endpoints son públicos y cuáles requieren autenticación.
     *     /auth/** es permitido para todos (login), y cualquier otro endpoint requiere estar autenticado.
     *
     * iii. Esa línea registra el filtro JWT antes del filtro de autenticación por usuario/contraseña,
     *      para que cada petición sea validada con el token antes de cualquier otro proceso.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/**").permitAll()
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/swagger-ui.html").permitAll()
                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}