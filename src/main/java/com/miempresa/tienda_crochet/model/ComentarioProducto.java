package com.miempresa.tienda_crochet.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
@Entity
public class ComentarioProducto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String comentario;
    private int calificacion;
    private Date fecha;

    // 🧑‍💬 Autor del comentario (borrado en cascada si se borra el usuario)
    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    // 🛍️ Producto comentado (borrado en cascada si se borra el producto)
    @ManyToOne
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto;
}
