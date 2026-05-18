package com.mrcar.mrcar.modelo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

@Entity
@Table(name = "piezas")
public class Pieza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String nombre;

    @Column(length = 4000)
    private String descripcion;

    @NotNull
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal precio;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoPieza estado;

    @NotNull
    @Column(nullable = false)
    private Integer stock;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "coche_id", nullable = false)
    private Coche coche;

    public Pieza() {}

    public Pieza(String nombre, String descripcion, BigDecimal precio, EstadoPieza estado, Integer stock, Coche coche) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.estado = estado;
        this.stock = stock;
        this.coche = coche;
    }

    public Long getId() { return id; }
    public String getNombre() { return nombre; }
    public String getDescripcion() { return descripcion; }
    public BigDecimal getPrecio() { return precio; }
    public EstadoPieza getEstado() { return estado; }
    public Integer getStock() { return stock; }
    public Coche getCoche() { return coche; }

    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public void setPrecio(BigDecimal precio) { this.precio = precio; }
    public void setEstado(EstadoPieza estado) { this.estado = estado; }
    public void setStock(Integer stock) { this.stock = stock; }
    public void setCoche(Coche coche) { this.coche = coche; }
}
