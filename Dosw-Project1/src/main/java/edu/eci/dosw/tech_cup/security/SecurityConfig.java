package edu.eci.dosw.tech_cup.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * a. @EnableWebSecurity es una anotación que activa la configuración de seguridad web
 *    de Spring Security, permitiendo personalizar cómo se protegen los endpoints
 *    de la aplicación.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

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

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/**").permitAll()
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/swagger-ui.html").permitAll()
                        .anyRequest().authenticated()
                );
        return http.build();
    }
}
