package com.generation._taEsencia.config;

import com.generation._taEsencia.security.CustomUserDetailsService;
import com.generation._taEsencia.security.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final CustomUserDetailsService customUserDetailsService;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter,
                          CustomUserDetailsService customUserDetailsService) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.customUserDetailsService = customUserDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))

                .authorizeHttpRequests(auth -> auth

                        // Preflight CORS
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                        // Auth público
                        .requestMatchers("/auth/**").permitAll()

                        // Productos públicos para consulta
                        .requestMatchers(HttpMethod.GET, "/api/productos").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/productos/**").permitAll()

                        // Categorías públicas para consulta
                        .requestMatchers(HttpMethod.GET, "/api/categorias").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/categorias/**").permitAll()

                        // Contacto público para enviar mensajes
                        .requestMatchers(HttpMethod.POST, "/api/contactos").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/contactos/**").permitAll()

                        // Productos: solo administrador
                        .requestMatchers(HttpMethod.POST, "/api/productos").hasAuthority("ROLE_administrador")
                        .requestMatchers(HttpMethod.POST, "/api/productos/**").hasAuthority("ROLE_administrador")

                        .requestMatchers(HttpMethod.PUT, "/api/productos").hasAuthority("ROLE_administrador")
                        .requestMatchers(HttpMethod.PUT, "/api/productos/**").hasAuthority("ROLE_administrador")

                        .requestMatchers(HttpMethod.DELETE, "/api/productos").hasAuthority("ROLE_administrador")
                        .requestMatchers(HttpMethod.DELETE, "/api/productos/**").hasAuthority("ROLE_administrador")

                        // Categorías: solo administrador
                        .requestMatchers(HttpMethod.POST, "/api/categorias").hasAuthority("ROLE_administrador")
                        .requestMatchers(HttpMethod.POST, "/api/categorias/**").hasAuthority("ROLE_administrador")

                        .requestMatchers(HttpMethod.PUT, "/api/categorias").hasAuthority("ROLE_administrador")
                        .requestMatchers(HttpMethod.PUT, "/api/categorias/**").hasAuthority("ROLE_administrador")

                        .requestMatchers(HttpMethod.DELETE, "/api/categorias").hasAuthority("ROLE_administrador")
                        .requestMatchers(HttpMethod.DELETE, "/api/categorias/**").hasAuthority("ROLE_administrador")

                        // Usuarios: solo administrador
                        .requestMatchers("/api/usuarios").hasAuthority("ROLE_administrador")
                        .requestMatchers("/api/usuarios/**").hasAuthority("ROLE_administrador")

                        // Ver y eliminar contactos: solo administrador
                        .requestMatchers(HttpMethod.GET, "/api/contactos").hasAuthority("ROLE_administrador")
                        .requestMatchers(HttpMethod.GET, "/api/contactos/**").hasAuthority("ROLE_administrador")

                        .requestMatchers(HttpMethod.DELETE, "/api/contactos").hasAuthority("ROLE_administrador")
                        .requestMatchers(HttpMethod.DELETE, "/api/contactos/**").hasAuthority("ROLE_administrador")

                        // Carrito: cualquier usuario autenticado
                        .requestMatchers(HttpMethod.GET, "/api/carrito").authenticated()
                        .requestMatchers(HttpMethod.GET, "/api/carrito/**").authenticated()

                        .requestMatchers(HttpMethod.POST, "/api/carrito").authenticated()
                        .requestMatchers(HttpMethod.POST, "/api/carrito/**").authenticated()

                        .requestMatchers(HttpMethod.PUT, "/api/carrito").authenticated()
                        .requestMatchers(HttpMethod.PUT, "/api/carrito/**").authenticated()

                        .requestMatchers(HttpMethod.DELETE, "/api/carrito").authenticated()
                        .requestMatchers(HttpMethod.DELETE, "/api/carrito/**").authenticated()

                        // Detalles de carrito: cualquier usuario autenticado
                        .requestMatchers("/api/detalles-carrito").authenticated()
                        .requestMatchers("/api/detalles-carrito/**").authenticated()

                        // Pedidos: cualquier usuario autenticado
                        .requestMatchers("/api/pedidos").authenticated()
                        .requestMatchers("/api/pedidos/**").authenticated()

                        // Detalles de pedidos: cualquier usuario autenticado
                        .requestMatchers("/api/detalles-pedidos").authenticated()
                        .requestMatchers("/api/detalles-pedidos/**").authenticated()

                        // Cualquier otra petición requiere autenticación
                        .anyRequest().authenticated()
                )

                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )

                .authenticationProvider(authenticationProvider())

                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {

        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(customUserDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOrigins(List.of(
                "http://127.0.0.1:5501",
                "http://localhost:5501",
                "http://127.0.0.1:5500",
                "http://localhost:5500"
        ));

        configuration.setAllowedMethods(List.of(
                "GET",
                "POST",
                "PUT",
                "DELETE",
                "OPTIONS"
        ));

        configuration.setAllowedHeaders(List.of(
                "Authorization",
                "Content-Type"
        ));

        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }
}