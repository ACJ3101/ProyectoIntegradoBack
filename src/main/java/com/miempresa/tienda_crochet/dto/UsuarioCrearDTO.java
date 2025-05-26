package com.miempresa.tienda_crochet.dto;

import lombok.Data;

@Data
public class UsuarioCrearDTO {
    private String nombre;
    private String apellidos;
    private String nick;
    private String email;
    private String direccion;
    private String contraseña;
    private Long rolId;
}
