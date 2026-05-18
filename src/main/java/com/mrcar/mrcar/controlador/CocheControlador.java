package com.mrcar.mrcar.controlador;

import com.mrcar.mrcar.modelo.Coche;
import com.mrcar.mrcar.repositorio.CocheRepositorio;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/coches")
public class CocheControlador {

    private final CocheRepositorio cocheRepositorio;

    public CocheControlador(CocheRepositorio cocheRepositorio) {
        this.cocheRepositorio = cocheRepositorio;
    }

    @GetMapping
    public List<Coche> listar() {
        return cocheRepositorio.findAll();
    }

    @GetMapping("/{id}")
    public Coche detalle(@PathVariable Long id) {
        return cocheRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Coche no encontrado"));
    }
}
