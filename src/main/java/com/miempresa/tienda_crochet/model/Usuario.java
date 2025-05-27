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
@ToString(exclude = "productos") // Excluye productos para evitar recursión infinita
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

    
    @jakarta.persistence.Transient // Opcional para evitar que JPA lo cargue si no lo necesitas aún
    @com.fasterxml.jackson.annotation.JsonIgnore
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Producto> productos;

    
    @ManyToOne
    @JoinColumn(name = "rol_id")
    private Rol rol;
    
    @OneToMany(mappedBy = "usuario")
    @JsonIgnore
    private List<ComentarioBlog> comentariosBlog;


}