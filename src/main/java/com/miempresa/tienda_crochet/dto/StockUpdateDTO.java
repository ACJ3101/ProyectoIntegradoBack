package com.miempresa.tienda_crochet.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StockUpdateDTO {
    private Long productoId;
    private int cantidadVendida;
}
