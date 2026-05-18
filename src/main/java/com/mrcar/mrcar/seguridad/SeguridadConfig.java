package com.mrcar.mrcar.seguridad;
import org.springframework.core.annotation.Order;

import com.mrcar.mrcar.repositorio.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.oauth2.jwt.*;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.List;

import com.nimbusds.jose.jwk.source.ImmutableSecret;

@Configuration
public class SeguridadConfig {

    @Value("${mrcar.jwt.secreto}")
    private String secreto;


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(UsuarioRepositorio usuarios) {
        return username -> usuarios.findByEmail(username)
                .map(u -> User.withUsername(u.getEmail())
                        .password(u.getPasswordHash())
                        .roles(u.getRol()) // "USER" -> ROLE_USER
                        .build())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

@Bean
public JwtDecoder jwtDecoder() {
    SecretKey key = new SecretKeySpec(secreto.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
    return NimbusJwtDecoder.withSecretKey(key).build();
}



 @Bean
public JwtEncoder jwtEncoder() {
    SecretKey key = new SecretKeySpec(secreto.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
    return new NimbusJwtEncoder(new ImmutableSecret<>(key));
}

@Bean
public JwtAuthenticationConverter jwtAuthenticationConverter() {
    JwtAuthenticationConverter conv = new JwtAuthenticationConverter();
    conv.setJwtGrantedAuthoritiesConverter(jwt -> {
        String rol = jwt.getClaimAsString("rol"); // "USER" o "ADMIN"
        if (rol == null || rol.isBlank()) return List.of();
        return List.of(new SimpleGrantedAuthority("ROLE_" + rol));
    });
    return conv;
}

@Bean
@Order(1)
public SecurityFilterChain authChain(HttpSecurity http) throws Exception {
    http
        .securityMatcher("/auth/**") // SOLO /auth/**
        .csrf(csrf -> csrf.disable())
        .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .formLogin(form -> form.disable())
        .httpBasic(basic -> basic.disable())
        .authorizeHttpRequests(auth -> auth
            .anyRequest().permitAll()
        );

    return http.build();
}



@Bean
@Order(2)
public SecurityFilterChain apiChain(HttpSecurity http) throws Exception {
    http
        .csrf(csrf -> csrf.disable())
        .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .formLogin(form -> form.disable())
        .httpBasic(basic -> basic.disable())
        .authorizeHttpRequests(auth -> auth
            .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()

            .requestMatchers(HttpMethod.GET, "/coches/**").permitAll()
            .requestMatchers("/piezas").permitAll()
            .requestMatchers("/piezas/**").permitAll()
            .requestMatchers(HttpMethod.GET, "/img/**").permitAll()


            .requestMatchers("/deseos/**").authenticated()
            .requestMatchers("/admin/**").hasRole("ADMIN")
            .anyRequest().permitAll()
        )
        .oauth2ResourceServer(oauth2 -> oauth2
            .jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter()))
        );

    return http.build();

}
}

