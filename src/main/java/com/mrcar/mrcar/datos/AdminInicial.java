package com.mrcar.mrcar.datos;

import com.mrcar.mrcar.modelo.Usuario;
import com.mrcar.mrcar.repositorio.UsuarioRepositorio;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AdminInicial {

    @Bean
    CommandLineRunner crearAdminInicial(UsuarioRepositorio usuarios, PasswordEncoder encoder) {
        return args -> {
            String emailAdmin = "admin@mrcar.com";
            String passAdmin = "admin";

            if (!usuarios.existsByEmail(emailAdmin)) {
                Usuario u = new Usuario();
                u.setEmail(emailAdmin);
                u.setPasswordHash(encoder.encode(passAdmin));

                // IMPORTANTE: tu sistema espera "ADMIN" (sin ROLE_)
                u.setRol("ADMIN");

                usuarios.save(u);

                System.out.println("✅ Admin creado: " + emailAdmin + " / " + passAdmin);
            } else {
                System.out.println("ℹ️ Admin ya existe: " + emailAdmin);
            }
        };
    }
}
