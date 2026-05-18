package com.mrcar.mrcar.controlador;

import com.mrcar.mrcar.modelo.Deseo;
import com.mrcar.mrcar.modelo.Pieza;
import com.mrcar.mrcar.modelo.Usuario;
import com.mrcar.mrcar.repositorio.DeseoRepositorio;
import com.mrcar.mrcar.repositorio.PiezaRepositorio;
import com.mrcar.mrcar.repositorio.UsuarioRepositorio;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/deseos")
public class DeseosControlador {

    private final DeseoRepositorio deseoRepo;
    private final UsuarioRepositorio usuarioRepo;
    private final PiezaRepositorio piezaRepo;

    public DeseosControlador(DeseoRepositorio deseoRepo, UsuarioRepositorio usuarioRepo, PiezaRepositorio piezaRepo) {
        this.deseoRepo = deseoRepo;
        this.usuarioRepo = usuarioRepo;
        this.piezaRepo = piezaRepo;
    }

    private String emailDesdeToken(Authentication auth) {
        Jwt jwt = (Jwt) auth.getPrincipal();
        return jwt.getSubject().trim().toLowerCase(); // sub = email
    }

    // GET /deseos  (requiere token)
    @GetMapping
    public List<Deseo> listar(Authentication auth) {
        String email = emailDesdeToken(auth);
        return deseoRepo.findByUsuarioEmailOrderByCreadoEnDesc(email);
    }

    // POST /deseos/{piezaId}
    @PostMapping("/{piezaId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void agregar(@PathVariable Long piezaId, Authentication auth) {
        String email = emailDesdeToken(auth);

        if (deseoRepo.existsByUsuarioEmailAndPiezaId(email, piezaId)) return;

        Usuario u = usuarioRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Pieza p = piezaRepo.findById(piezaId)
                .orElseThrow(() -> new RuntimeException("Pieza no encontrada"));

        deseoRepo.save(new Deseo(u, p));
    }

    // DELETE /deseos/{piezaId}
    @DeleteMapping("/{piezaId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void quitar(@PathVariable Long piezaId, Authentication auth) {
        String email = emailDesdeToken(auth);

        deseoRepo.findByUsuarioEmailAndPiezaId(email, piezaId)
                .ifPresent(deseoRepo::delete);
    }
}
