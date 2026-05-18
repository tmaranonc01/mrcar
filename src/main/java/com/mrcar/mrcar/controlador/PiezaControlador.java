package com.mrcar.mrcar.controlador;


import com.mrcar.mrcar.modelo.Pieza;
import com.mrcar.mrcar.repositorio.PiezaRepositorio;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/piezas")
public class PiezaControlador {

    private final PiezaRepositorio piezaRepositorio;

    public PiezaControlador(PiezaRepositorio piezaRepositorio) {
        this.piezaRepositorio = piezaRepositorio;
    }

    @GetMapping
    public List<Pieza> listar(
            @RequestParam(required = false) Long cocheId,
            @RequestParam(required = false) String texto,
            @RequestParam(required = false) String marca,
            @RequestParam(required = false) String modelo,
            @RequestParam(required = false) com.mrcar.mrcar.modelo.EstadoPieza estado
    ) {
        if (cocheId != null) {
            return piezaRepositorio.findByCocheId(cocheId);
        }

        return piezaRepositorio.buscar(
                normalizeContains(texto),
                estado,
                normalizeEqual(marca),
                normalizeEqual(modelo)
        );
    }

    private static String normalizeContains(String value) {
        if (value == null || value.isBlank()) {
            return null;
        }
        return "%" + value.trim().toLowerCase(Locale.ROOT) + "%";
    }

    private static String normalizeEqual(String value) {
        if (value == null || value.isBlank()) {
            return null;
        }
        return value.trim().toLowerCase(Locale.ROOT);
    }


    @GetMapping("/{id}")
    public Pieza detalle(@PathVariable Long id) {
        return piezaRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Pieza no encontrada"));
    }
}
