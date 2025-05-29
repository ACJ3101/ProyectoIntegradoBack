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
@ToString(exclude = "comentarios") // Evita recursión infinita en logs
@Entity
public class PublicacionBlog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String contenido;
    private Date fecha;
    private String categoria;

    // ✍️ Autor de la publicación (relación con Usuario)
    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario autor;

    // 💬 Comentarios asociados a la publicación
    @OneToMany(mappedBy = "publicacion", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<ComentarioBlog> comentarios;
}
