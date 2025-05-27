package com.miempresa.tienda_crochet.dto;

import lombok.Data;


@Data
public class PublicacionBlogDTO {
    private String titulo;
    private String contenido;
    private String categoria;
    private Long autorId;
}

