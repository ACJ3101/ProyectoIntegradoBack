package com.miempresa.tienda_crochet.mapper;

import com.miempresa.tienda_crochet.dto.UsuarioDTO;
import com.miempresa.tienda_crochet.model.Usuario;

public class UsuarioMapper {

    public static UsuarioDTO toDto(Usuario usuario) {
        if (usuario == null) return null;

        UsuarioDTO dto = new UsuarioDTO();
        dto.setId(usuario.getId());
        dto.setNombre(usuario.getNombre());
        dto.setApellidos(usuario.getApellidos());
        dto.setNick(usuario.getNick());
        dto.setEmail(usuario.getEmail());
        dto.setDireccion(usuario.getDireccion());
        dto.setRol(RolMapper.toDto(usuario.getRol()));
        return dto;
    }
}
