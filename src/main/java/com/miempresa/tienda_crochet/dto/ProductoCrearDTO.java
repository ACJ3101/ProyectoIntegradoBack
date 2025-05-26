package com.miempresa.tienda_crochet.dto;

import lombok.Data;

@Data
public class ProductoCrearDTO {
    private String nombre;
    private String descripcion;
    private Double precio;
    private Integer stock;
    private String imagenUrl;
    private Long categoriaId;
    private Long usuarioId;
}
