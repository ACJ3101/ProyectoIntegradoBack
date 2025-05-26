package com.miempresa.tienda_crochet.mapper;

import com.miempresa.tienda_crochet.dto.RolDTO;
import com.miempresa.tienda_crochet.model.Rol;

public class RolMapper {
    public static RolDTO toDto(Rol rol) {
        if (rol == null) return null;

        RolDTO dto = new RolDTO();
        dto.setId(rol.getId());
        dto.setNombre(rol.getNombre());
        return dto;
    }
}
