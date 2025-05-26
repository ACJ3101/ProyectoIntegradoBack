package com.miempresa.tienda_crochet.mapper;

import com.miempresa.tienda_crochet.dto.ComentarioResponseDTO;
import com.miempresa.tienda_crochet.model.ComentarioProducto;

public class ComentarioMapper {

    public static ComentarioResponseDTO toDto(ComentarioProducto comentario) {
        ComentarioResponseDTO dto = new ComentarioResponseDTO();
        dto.setId(comentario.getId());
        dto.setComentario(comentario.getComentario());
        dto.setCalificacion(comentario.getCalificacion());

        dto.setUsuarioId(comentario.getUsuario().getId());
        dto.setUsuarioNick(comentario.getUsuario().getNick());

        dto.setProductoId(comentario.getProducto().getId());

        return dto;
    }
}
