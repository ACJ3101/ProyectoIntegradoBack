package com.miempresa.tienda_crochet.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

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
    private String rol;
    private Date fechaRegistro;
    private String direccion;

    @OneToMany(mappedBy = "usuario")
    @jakarta.persistence.Transient // Opcional para evitar que JPA lo cargue si no lo necesitas aún
    @com.fasterxml.jackson.annotation.JsonIgnore
    private List<Producto> productos;
}