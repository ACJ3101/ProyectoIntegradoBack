package com.miempresa.tienda_crochet.dto;

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
    private boolean publicado;
    private Long categoriaId;
    private Long usuarioId;
    private String usuarioNick; // 👈 nuevo campo
    private Double calidad;

}
