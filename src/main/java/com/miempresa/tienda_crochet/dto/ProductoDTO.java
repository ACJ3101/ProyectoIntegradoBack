package com.miempresa.tienda_crochet.dto;

import java.util.Date;

import lombok.Data;


@Data
public class ProductoDTO {
    private Long id;
    private String nombre;
    private String descripcion;
    private double precio;
    private int stock;
    private String imagenUrl;
    private String fechaCreacion;
    private Long categoriaId;
    private Long usuarioId; // Solo el ID del usuario
}
