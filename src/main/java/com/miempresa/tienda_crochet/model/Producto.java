package com.miempresa.tienda_crochet.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString(exclude = {"comentarios", "detalles"})
@Entity
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String descripcion;
    private Double precio;
    private int stock;
    private String imagenUrl;
    private boolean publicado;
    private Date fechaCreacion;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @OneToMany(mappedBy = "producto")
    @JsonIgnore
    private List<ComentarioProducto> comentarios;

    @OneToMany(mappedBy = "producto")
    @JsonIgnore
    private List<DetallePedido> detalles;
    
    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

}
