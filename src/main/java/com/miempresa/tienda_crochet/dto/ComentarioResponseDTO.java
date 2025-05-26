package com.miempresa.tienda_crochet.dto;

import lombok.Data;

@Data
public class ComentarioResponseDTO {
    private Long id;
    private String comentario;
    private int calificacion;
    private Long usuarioId;
    private String usuarioNick;
    private Long productoId;
}
