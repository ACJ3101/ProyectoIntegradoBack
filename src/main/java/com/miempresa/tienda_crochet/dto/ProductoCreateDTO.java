package com.miempresa.tienda_crochet.dto;

import lombok.Data;

@Data
public class ProductoCreateDTO {
    private String nombre;
    private String descripcion;
    private double precio;
    private int stock;
    private String imagenUrl;
    private boolean publicado;
    private Long usuarioId;
    private Long categoriaId;
}
