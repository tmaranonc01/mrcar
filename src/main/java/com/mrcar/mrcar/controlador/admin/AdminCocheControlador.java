package com.mrcar.mrcar.controlador.admin;

import com.mrcar.mrcar.modelo.Coche;
import com.mrcar.mrcar.repositorio.CocheRepositorio;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/coches")
public class AdminCocheControlador {

    private final CocheRepositorio cocheRepositorio;

    public AdminCocheControlador(CocheRepositorio cocheRepositorio) {
        this.cocheRepositorio = cocheRepositorio;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Coche crear(@Valid @RequestBody Coche coche) {
        return cocheRepositorio.save(coche);
    }

    @PutMapping("/{id}")
    public Coche actualizar(@PathVariable Long id, @Valid @RequestBody Coche datos) {
        Coche coche = cocheRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Coche no encontrado"));

        coche.setMarca(datos.getMarca());
        coche.setModelo(datos.getModelo());
        coche.setAnio(datos.getAnio());
        coche.setMotor(datos.getMotor());

        return cocheRepositorio.save(coche);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void borrar(@PathVariable Long id) {
        cocheRepositorio.deleteById(id);
    }
}
