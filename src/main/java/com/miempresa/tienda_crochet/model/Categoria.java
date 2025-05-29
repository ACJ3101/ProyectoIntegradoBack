package com.miempresa.tienda_crochet.model;

import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString(exclude = "productos")
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Producto> productos;

    public Categoria(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
}
