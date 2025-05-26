package com.miempresa.tienda_crochet.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductoUpdateDTO {
    private String nombre;
    private String descripcion;
    private double precio;
    private int stock;
    private String imagenUrl;
    private boolean publicado;
    private Long categoriaId;
}
