package com.miempresa.tienda_crochet.dto;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class PedidoResponseDTO {
    private Long id;
    private Date fecha;
    private String estado;
    private Double total;
    private String clienteNick;
    private Long clienteId; 
    private List<Long> productoIds;
}

