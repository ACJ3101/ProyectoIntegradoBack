package com.miempresa.tienda_crochet.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString(exclude = {"usuario", "publicacion"}) // Evita recursión en logs
@Entity
public class ComentarioBlog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String comentario;
    private int calificacion;
    private Date fecha;

    // 🧑 Relación con el autor del comentario
    @ManyToOne(optional = false)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    // 📝 Relación con la publicación del blog
    @ManyToOne(optional = false)
    @JoinColumn(name = "publicacion_id")
    private PublicacionBlog publicacion;
}
