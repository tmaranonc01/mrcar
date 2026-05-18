package com.mrcar.mrcar.repositorio;

import com.mrcar.mrcar.modelo.Deseo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DeseoRepositorio extends JpaRepository<Deseo, Long> {
    List<Deseo> findByUsuarioEmailOrderByCreadoEnDesc(String email);
    Optional<Deseo> findByUsuarioEmailAndPiezaId(String email, Long piezaId);
    boolean existsByUsuarioEmailAndPiezaId(String email, Long piezaId);
    void deleteByUsuarioEmailAndPiezaId(String email, Long piezaId);
}
