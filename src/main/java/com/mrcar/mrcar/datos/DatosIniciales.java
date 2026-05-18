package com.mrcar.mrcar.datos;

import com.mrcar.mrcar.modelo.Coche;
import com.mrcar.mrcar.modelo.EstadoPieza;
import com.mrcar.mrcar.modelo.Pieza;
import com.mrcar.mrcar.repositorio.CocheRepositorio;
import com.mrcar.mrcar.repositorio.PiezaRepositorio;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration
public class DatosIniciales {

    @Bean
    CommandLineRunner cargarDatos(CocheRepositorio cocheRepo, PiezaRepositorio piezaRepo) {
        return args -> {
            Coche golf = obtenerOCrearCoche(cocheRepo, "Volkswagen", "Golf", 2017, "1.6 TDI");
            Coche ibiza = obtenerOCrearCoche(cocheRepo, "SEAT", "Ibiza", 2019, "1.0 TSI");
            Coche megane = obtenerOCrearCoche(cocheRepo, "Renault", "Megane", 2018, "1.5 dCi");
            Coche p308 = obtenerOCrearCoche(cocheRepo, "Peugeot", "308", 2020, "1.2 PureTech");
            Coche bmw320 = obtenerOCrearCoche(cocheRepo, "BMW", "Serie 3", 2016, "2.0d");
            Coche focus = obtenerOCrearCoche(cocheRepo, "Ford", "Focus", 2015, "1.5 EcoBoost");
            Coche a3 = obtenerOCrearCoche(cocheRepo, "Audi", "A3", 2018, "1.4 TFSI");
            Coche corsa = obtenerOCrearCoche(cocheRepo, "Opel", "Corsa", 2017, "1.3 CDTI");
            Coche claseC = obtenerOCrearCoche(cocheRepo, "Mercedes", "Clase C", 2019, "220d");
            Coche corolla = obtenerOCrearCoche(cocheRepo, "Toyota", "Corolla", 2021, "1.8 Hybrid");

            guardarOActualizarPieza(piezaRepo, new Pieza(
                    "Alternador",
                    "Alternador en buen estado, probado.",
                    new BigDecimal("89.90"),
                    EstadoPieza.USADA,
                    2,
                    golf
            ));

            guardarOActualizarPieza(piezaRepo, new Pieza(
                    "Faro delantero derecho",
                    "Faro sin roturas, con alguna marca ligera.",
                    new BigDecimal("59.50"),
                    EstadoPieza.USADA,
                    1,
                    ibiza
            ));

            guardarOActualizarPieza(piezaRepo, new Pieza(
                    "Retrovisor izquierdo electrico",
                    "Retrovisor completo con carcasa y mando electrico.",
                    new BigDecimal("74.00"),
                    EstadoPieza.REACONDICIONADA,
                    3,
                    megane
            ));

            guardarOActualizarPieza(piezaRepo, new Pieza(
                    "Turbo Garrett",
                    "Turbo revisado y equilibrado, listo para montar.",
                    new BigDecimal("320.00"),
                    EstadoPieza.REACONDICIONADA,
                    1,
                    p308
            ));

            guardarOActualizarPieza(piezaRepo, new Pieza(
                    "Caja de cambios manual 6V",
                    "Caja de cambios sin holguras ni ruidos anormales.",
                    new BigDecimal("540.00"),
                    EstadoPieza.USADA,
                    1,
                    bmw320
            ));

            guardarOActualizarPieza(piezaRepo, new Pieza(
                    "Compresor A/C",
                    "Compresor de aire acondicionado con polea en buen estado.",
                    new BigDecimal("145.00"),
                    EstadoPieza.USADA,
                    2,
                    focus
            ));

            guardarOActualizarPieza(piezaRepo, new Pieza(
                    "Bomba inyectora",
                    "Bomba inyectora comprobada en banco de pruebas.",
                    new BigDecimal("265.00"),
                    EstadoPieza.REACONDICIONADA,
                    1,
                    golf
            ));

            guardarOActualizarPieza(piezaRepo, new Pieza(
                    "Piloto trasero izquierdo",
                    "Piloto original, sin grietas y con portalamparas.",
                    new BigDecimal("48.90"),
                    EstadoPieza.USADA,
                    4,
                    ibiza
            ));

            guardarOActualizarPieza(piezaRepo, new Pieza(
                    "Radiador principal",
                    "Radiador limpio, sin fugas y con panal en buen estado.",
                    new BigDecimal("110.00"),
                    EstadoPieza.USADA,
                    2,
                    megane
            ));

            guardarOActualizarPieza(piezaRepo, new Pieza(
                    "Llanta aluminio 17\"",
                    "Llanta rectificada, lista para equilibrado.",
                    new BigDecimal("95.00"),
                    EstadoPieza.REACONDICIONADA,
                    4,
                    p308
            ));

            guardarOActualizarPieza(piezaRepo, new Pieza(
                    "Pantalla multimedia",
                    "Unidad multimedia con Bluetooth y navegador integrado.",
                    new BigDecimal("210.00"),
                    EstadoPieza.NUEVA,
                    2,
                    bmw320
            ));

            guardarOActualizarPieza(piezaRepo, new Pieza(
                    "Motor limpiaparabrisas",
                    "Motor delantero con mecanismo completo.",
                    new BigDecimal("67.50"),
                    EstadoPieza.USADA,
                    3,
                    focus
            ));

            guardarOActualizarPieza(piezaRepo, new Pieza(
                    "Bateria AGM 70Ah",
                    "Bateria con carga completa y prueba de arranque superada.",
                    new BigDecimal("119.00"),
                    EstadoPieza.NUEVA,
                    5,
                    a3
            ));

            guardarOActualizarPieza(piezaRepo, new Pieza(
                    "Paragolpes delantero",
                    "Paragolpes en color gris, pequenos roces superficiales.",
                    new BigDecimal("135.00"),
                    EstadoPieza.USADA,
                    1,
                    a3
            ));

            guardarOActualizarPieza(piezaRepo, new Pieza(
                    "Bomba de agua",
                    "Bomba revisada y sin juego en rodamiento.",
                    new BigDecimal("54.90"),
                    EstadoPieza.REACONDICIONADA,
                    3,
                    corsa
            ));

            guardarOActualizarPieza(piezaRepo, new Pieza(
                    "Modulo ABS",
                    "Modulo ABS con referencia verificada y test de comunicacion.",
                    new BigDecimal("190.00"),
                    EstadoPieza.REACONDICIONADA,
                    2,
                    corsa
            ));

            guardarOActualizarPieza(piezaRepo, new Pieza(
                    "Asiento conductor",
                    "Asiento de cuero con ajuste electrico, buen estado general.",
                    new BigDecimal("260.00"),
                    EstadoPieza.USADA,
                    1,
                    claseC
            ));

            guardarOActualizarPieza(piezaRepo, new Pieza(
                    "Puerta delantera derecha",
                    "Puerta completa con elevalunas y cerradura.",
                    new BigDecimal("330.00"),
                    EstadoPieza.USADA,
                    1,
                    claseC
            ));

            guardarOActualizarPieza(piezaRepo, new Pieza(
                    "Faros LED delanteros",
                    "Juego de faros LED originales con balastros incluidos.",
                    new BigDecimal("780.00"),
                    EstadoPieza.REACONDICIONADA,
                    1,
                    corolla
            ));

            guardarOActualizarPieza(piezaRepo, new Pieza(
                    "Inversor hibrido",
                    "Inversor revisado para sistema hibrido, funcionamiento estable.",
                    new BigDecimal("890.00"),
                    EstadoPieza.REACONDICIONADA,
                    1,
                    corolla
            ));

            guardarOActualizarPieza(piezaRepo, new Pieza(
                    "Volante multifuncion",
                    "Volante con mandos integrados y airbag no incluido.",
                    new BigDecimal("145.00"),
                    EstadoPieza.USADA,
                    2,
                    a3
            ));

            guardarOActualizarPieza(piezaRepo, new Pieza(
                    "Kit embrague completo",
                    "Kit de embrague con disco, maza y cojinete.",
                    new BigDecimal("215.00"),
                    EstadoPieza.NUEVA,
                    3,
                    focus
            ));
        };
    }

    private static Coche obtenerOCrearCoche(
            CocheRepositorio cocheRepo,
            String marca,
            String modelo,
            Integer anio,
            String motor
    ) {
        return cocheRepo.findByMarcaIgnoreCaseAndModeloIgnoreCaseAndAnioAndMotor(marca, modelo, anio, motor)
                .orElseGet(() -> cocheRepo.save(new Coche(marca, modelo, anio, motor)));
    }

    private static void guardarOActualizarPieza(PiezaRepositorio piezaRepo, Pieza semilla) {
        piezaRepo.findFirstByNombreIgnoreCaseAndCocheId(semilla.getNombre(), semilla.getCoche().getId())
                .ifPresentOrElse(existente -> {
                    existente.setDescripcion(semilla.getDescripcion());
                    existente.setPrecio(semilla.getPrecio());
                    existente.setEstado(semilla.getEstado());
                    existente.setStock(semilla.getStock());
                    piezaRepo.save(existente);
                }, () -> piezaRepo.save(semilla));
    }
}
