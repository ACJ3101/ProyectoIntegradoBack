package com.miempresa.tienda_crochet.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class PedidoDTO {
    private Long usuarioId;
    private String estado;
    private Double total;
    private List<Long> productoIds; // Lista de IDs de productos
}
