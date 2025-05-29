package com.miempresa.tienda_crochet.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Getter
@Setter
@ToString(exclude = {"productos", "comentariosBlog", "comentariosProducto", "publicaciones", "pedidos"})
@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String email;
    private String contraseña;
    private String nick;
    private String apellidos;

    private Date fechaRegistro;
    private String direccion;

    // 🔄 Productos creados por el usuario
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Producto> productos;

    // 🔄 Comentarios en publicaciones del blog
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<ComentarioBlog> comentariosBlog;

    // 🔄 Comentarios en productos
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<ComentarioProducto> comentariosProducto;

    // 🔄 Publicaciones del blog creadas por el usuario
    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<PublicacionBlog> publicaciones;

    // 🔄 Pedidos realizados por el usuario
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Pedido> pedidos;

    // 🔗 Rol del usuario
    @ManyToOne
    @JoinColumn(name = "rol_id")
    private Rol rol;
}
