package com.miempresa.tienda_crochet.mapper;

import com.miempresa.tienda_crochet.dto.CategoriaDTO;
import com.miempresa.tienda_crochet.model.Categoria;

public class CategoriaMapper {
    public static CategoriaDTO toDto(Categoria categoria) {
        if (categoria == null) return null;

        CategoriaDTO dto = new CategoriaDTO();
        dto.setId(categoria.getId());
        dto.setNombre(categoria.getNombre());
        return dto;
    }
}
