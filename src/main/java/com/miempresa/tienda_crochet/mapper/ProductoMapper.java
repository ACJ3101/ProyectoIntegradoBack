package com.miempresa.tienda_crochet.mapper;

import com.miempresa.tienda_crochet.dto.ProductoDTO;
import com.miempresa.tienda_crochet.model.Producto;


public class ProductoMapper {

    public static ProductoDTO toDto(Producto producto) {
        ProductoDTO dto = new ProductoDTO();
        dto.setId(producto.getId());
        dto.setNombre(producto.getNombre());
        dto.setDescripcion(producto.getDescripcion());
        dto.setPrecio(producto.getPrecio());
        dto.setStock(producto.getStock());
        dto.setImagenUrl(producto.getImagenUrl());
        dto.setFechaCreacion(producto.getFechaCreacion().toString());
        dto.setCategoriaId(producto.getCategoria().getId());
        dto.setUsuarioId(producto.getUsuario().getId());
        return dto;
    }
}
