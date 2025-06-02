// src/main/java/com/miempresa/tienda_crochet/dto/StockUpdateListDTO.java
package com.miempresa.tienda_crochet.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class StockUpdateListDTO {
    private List<StockUpdateDTO> productos;
}
