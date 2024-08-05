package com.example.demos.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {

    private final JwtFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(withDefaults())  // Active la configuration par défaut pour CORS (Cross-Origin Resource Sharing)
                .csrf(AbstractHttpConfigurer::disable)  // Désactive la protection CSRF (Cross-Site Request Forgery)
                .authorizeHttpRequests(req ->
                        req.requestMatchers(
                            "/presence/**",
                                        "/projects/**",
                                        "/roles/**",
                                        "/users/**",
                                        "/auth/**",  // Permet l'accès sans authentification aux URLs commençant par /auth/
                                        "/v2/api-docs",  // Permet l'accès sans authentification à la documentation API v2
                                        "v3/api-docs",  // Permet l'accès sans authentification à la documentation API v3
                                        "v3/api-docs/**",  // Permet l'accès sans authentification aux sous-URLs de v3/api-docs
                                        "/swagger-resources",  // Permet l'accès sans authentification aux ressources Swagger
                                        "/swagger-resources/**",  // Permet l'accès sans authentification aux sous-URLs de swagger-resources
                                        "/configuration/ui",  // Permet l'accès sans authentification à la configuration UI de Swagger
                                        "/configuration/security",  // Permet l'accès sans authentification à la configuration de sécurité de Swagger
                                        "/swagger-ui/**",  // Permet l'accès sans authentification à l'interface utilisateur de Swagger
                                        "/webjars/**",  // Permet l'accès sans authentification aux fichiers statiques Webjars
                                        "/swagger-ui.html"  // Permet l'accès sans authentification à la page d'accueil de Swagger
                                ).permitAll()  // Permet l'accès sans authentification aux URLs spécifiées
                                .anyRequest().authenticated()  // Toute autre requête doit être authentifiée
                )
                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))  // Configure la gestion de session pour être sans état (stateless)
                .authenticationProvider(authenticationProvider)  // Utilise l'AuthenticationProvider personnalisé pour l'authentification
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);  // Ajoute le filtre JWT avant le filtre UsernamePasswordAuthenticationFilter

        return http.build();  // Construit l'objet SecurityFilterChain
    }

}
