package com.mrcar.mrcar.modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "coches")
public class Coche {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String marca;

    @NotBlank
    @Column(nullable = false)
    private String modelo;

    private Integer anio;

    private String motor;

    // Evita bucles JSON (Coche -> Piezas -> Coche -> ...)
    @JsonIgnore
    @OneToMany(mappedBy = "coche", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pieza> piezas = new ArrayList<>();

    public Coche() {}

    public Coche(String marca, String modelo, Integer anio, String motor) {
        this.marca = marca;
        this.modelo = modelo;
        this.anio = anio;
        this.motor = motor;
    }

    public Long getId() { return id; }
    public String getMarca() { return marca; }
    public String getModelo() { return modelo; }
    public Integer getAnio() { return anio; }
    public String getMotor() { return motor; }

    public void setMarca(String marca) { this.marca = marca; }
    public void setModelo(String modelo) { this.modelo = modelo; }
    public void setAnio(Integer anio) { this.anio = anio; }
    public void setMotor(String motor) { this.motor = motor; }
}
