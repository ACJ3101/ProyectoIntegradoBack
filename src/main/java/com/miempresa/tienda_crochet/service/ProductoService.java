package com.miempresa.tienda_crochet.service;

import com.miempresa.tienda_crochet.dto.ProductoDTO;
import com.miempresa.tienda_crochet.mapper.ProductoMapper;
import com.miempresa.tienda_crochet.model.Producto;
import com.miempresa.tienda_crochet.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;




@Service
public class ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public List<Producto> listarTodos() {
        return productoRepository.findAll();
    }

    public Optional<Producto> obtenerPorId(Long id) {
        return productoRepository.findById(id);
    }

    public Producto guardar(Producto producto) {
        return productoRepository.save(producto);
    }

    public void eliminar(Long id) {
        productoRepository.deleteById(id);
    }
    
    public List<Producto> obtenerPorCategoriaId(Long categoriaId) {
        return productoRepository.findByCategoriaId(categoriaId);
    }
    
    public List<Producto> obtenerPorUsuarioId(Long usuarioId) {
        return productoRepository.findByUsuarioId(usuarioId);
        
    }

}
