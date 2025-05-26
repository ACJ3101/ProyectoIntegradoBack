package com.miempresa.tienda_crochet.dto;

import lombok.Data;

@Data
public class ComentarioCreateDTO {
    private String comentario;
    private int calificacion;
    private Long usuarioId;
    private Long productoId;
}
