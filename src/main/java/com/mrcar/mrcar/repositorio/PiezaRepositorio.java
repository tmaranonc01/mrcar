package com.mrcar.mrcar.repositorio;

import com.mrcar.mrcar.modelo.Pieza;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PiezaRepositorio extends JpaRepository<Pieza, Long> {

    List<Pieza> findByCocheId(Long cocheId);
    Optional<Pieza> findFirstByNombreIgnoreCaseAndCocheId(String nombre, Long cocheId);

    @Query("""
        SELECT p FROM Pieza p
        WHERE (:texto IS NULL OR LOWER(p.nombre) LIKE :texto)
          AND (:estado IS NULL OR p.estado = :estado)
          AND (:marca IS NULL OR LOWER(p.coche.marca) = :marca)
          AND (:modelo IS NULL OR LOWER(p.coche.modelo) = :modelo)
        """)
    List<Pieza> buscar(
            @Param("texto") String texto,
            @Param("estado") com.mrcar.mrcar.modelo.EstadoPieza estado,
            @Param("marca") String marca,
            @Param("modelo") String modelo
    );
}
