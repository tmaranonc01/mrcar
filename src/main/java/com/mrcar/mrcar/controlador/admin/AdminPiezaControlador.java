package com.mrcar.mrcar.controlador.admin;

import com.mrcar.mrcar.modelo.Coche;
import com.mrcar.mrcar.modelo.Pieza;
import com.mrcar.mrcar.repositorio.CocheRepositorio;
import com.mrcar.mrcar.repositorio.PiezaRepositorio;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/piezas")
public class AdminPiezaControlador {

    private final PiezaRepositorio piezaRepositorio;
    private final CocheRepositorio cocheRepositorio;

    public AdminPiezaControlador(PiezaRepositorio piezaRepositorio, CocheRepositorio cocheRepositorio) {
        this.piezaRepositorio = piezaRepositorio;
        this.cocheRepositorio = cocheRepositorio;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Pieza crear(@Valid @RequestBody Pieza pieza) {
        Long cocheId = pieza.getCoche().getId();
        Coche coche = cocheRepositorio.findById(cocheId)
                .orElseThrow(() -> new RuntimeException("Coche no encontrado"));

        pieza.setCoche(coche);
        return piezaRepositorio.save(pieza);
    }

    @PutMapping("/{id}")
    public Pieza actualizar(@PathVariable Long id, @Valid @RequestBody Pieza datos) {
        Pieza pieza = piezaRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Pieza no encontrada"));

        Long cocheId = datos.getCoche().getId();
        Coche coche = cocheRepositorio.findById(cocheId)
                .orElseThrow(() -> new RuntimeException("Coche no encontrado"));

        pieza.setNombre(datos.getNombre());
        pieza.setDescripcion(datos.getDescripcion());
        pieza.setPrecio(datos.getPrecio());
        pieza.setEstado(datos.getEstado());
        pieza.setStock(datos.getStock());
        pieza.setCoche(coche);

        return piezaRepositorio.save(pieza);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void borrar(@PathVariable Long id) {
        piezaRepositorio.deleteById(id);
    }
}
