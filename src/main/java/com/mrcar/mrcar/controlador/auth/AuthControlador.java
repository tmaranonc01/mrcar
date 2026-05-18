package com.mrcar.mrcar.controlador.auth;

import com.mrcar.mrcar.modelo.Usuario;
import com.mrcar.mrcar.repositorio.UsuarioRepositorio;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;

@RestController
@RequestMapping("/auth")
public class AuthControlador {

    private final UsuarioRepositorio usuarios;
    private final PasswordEncoder encoder;
    private final JwtEncoder jwtEncoder;

    @Value("${mrcar.jwt.expiracion-minutos}")
    private long expMin;

    public AuthControlador(UsuarioRepositorio usuarios, PasswordEncoder encoder, JwtEncoder jwtEncoder) {
    this.usuarios = usuarios;
    this.encoder = encoder;
    this.jwtEncoder = jwtEncoder;
}


    public static class RegistroRequest {
        @Email @NotBlank public String email;
        @NotBlank public String password;
    }

    public static class LoginRequest {
        @Email @NotBlank public String email;
        @NotBlank public String password;
    }

    public static class TokenResponse {
        public String token;
        public TokenResponse(String token) { this.token = token; }
    }

    @PostMapping("/registro")
    @ResponseStatus(HttpStatus.CREATED)
    public void registro(@Valid @RequestBody RegistroRequest req) {
        if (usuarios.existsByEmail(req.email)) {
            throw new RuntimeException("Ese email ya existe");
        }
        Usuario u = new Usuario();
        u.setEmail(req.email);
        u.setPasswordHash(encoder.encode(req.password));
        u.setRol("user");
        usuarios.save(u);
    }

    @PostMapping("/login")
public TokenResponse login(@Valid @RequestBody LoginRequest req) {

    Usuario u = usuarios.findByEmail(req.email)
            .orElseThrow(() -> new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED, "Credenciales incorrectas"));

    if (!encoder.matches(req.password, u.getPasswordHash())) {
        throw new ResponseStatusException(
                HttpStatus.UNAUTHORIZED, "Credenciales incorrectas");
    }

    Instant now = Instant.now();

    JwtClaimsSet claims = JwtClaimsSet.builder()
            .issuer("mrcar")
            .issuedAt(now)
            .expiresAt(now.plusSeconds(expMin * 60))
            .subject(u.getEmail())
            .claim("rol", u.getRol())
            .build();

    JwsHeader header = JwsHeader.with(MacAlgorithm.HS256).build();
String token = jwtEncoder.encode(JwtEncoderParameters.from(header, claims)).getTokenValue();

    return new TokenResponse(token);
}


}
