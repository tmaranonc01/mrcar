package com.mrcar.mrcar.modelo;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(
    name = "deseos",
    uniqueConstraints = @UniqueConstraint(columnNames = {"usuario_id", "pieza_id"})
)
public class Deseo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "pieza_id", nullable = false)
    private Pieza pieza;

    @Column(nullable = false)
    private Instant creadoEn = Instant.now();

    public Deseo() {}

    public Deseo(Usuario usuario, Pieza pieza) {
        this.usuario = usuario;
        this.pieza = pieza;
        this.creadoEn = Instant.now();
    }

    public Long getId() { return id; }
    public Usuario getUsuario() { return usuario; }
    public Pieza getPieza() { return pieza; }
    public Instant getCreadoEn() { return creadoEn; }

    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
    public void setPieza(Pieza pieza) { this.pieza = pieza; }
}
