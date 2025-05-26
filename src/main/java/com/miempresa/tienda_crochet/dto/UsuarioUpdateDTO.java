// UsuarioUpdateDTO.java
package com.miempresa.tienda_crochet.dto;

import lombok.Data;

@Data
public class UsuarioUpdateDTO {
    private String nombre;
    private String apellidos;
    private Long rolId;
    private String email;
    private String direccion;
    private String nick;
}
