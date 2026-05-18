package com.mrcar.mrcar.repositorio;

import com.mrcar.mrcar.modelo.Coche;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CocheRepositorio extends JpaRepository<Coche, Long> {
    Optional<Coche> findByMarcaIgnoreCaseAndModeloIgnoreCaseAndAnioAndMotor(
            String marca,
            String modelo,
            Integer anio,
            String motor
    );
}
