package com.miempresa.tienda_crochet.dto;

import lombok.Data;

@Data
public class ComentarioBlogDTO {
    private String comentario;
    private int calificacion;
    private Long usuarioId;
    private Long publicacionId;
}
