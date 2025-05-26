package com.miempresa.tienda_crochet.dto;

import com.miempresa.tienda_crochet.model.Rol;
import lombok.Data;

@Data
public class UsuarioDTO {
    private Long id;
    private String nombre;
    private String apellidos;
    private String nick;
    private String email;
    private String direccion;
    private RolDTO rol;
}
