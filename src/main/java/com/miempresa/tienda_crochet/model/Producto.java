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
@ToString(exclude = {"comentarios"})
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

    // 📝 Comentarios sobre el producto (se eliminan en cascada si se borra el producto)
    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<ComentarioProducto> comentarios;

    // 👤 Usuario creador del producto
    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    // 🗂️ Categoría del producto
    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    // ⭐ Calidad calculada a partir de los comentarios
    @Transient
    private Double calidad;

    public Double getCalidad() {
        if (comentarios == null || comentarios.isEmpty()) {
            return null;
        }
        return comentarios.stream()
                          .mapToInt(ComentarioProducto::getCalificacion)
                          .average()
                          .orElse(0.0);
    }
}
