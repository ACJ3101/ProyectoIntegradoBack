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
@ToString
@Entity
public class PublicacionBlog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String contenido;
    private Date fecha;
    private String categoria;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario autor;
    
    @OneToMany(mappedBy = "publicacion")
    @JsonIgnore
    private List<ComentarioBlog> comentarios;

}
