package com.miempresa.tienda_crochet.service;

import com.miempresa.tienda_crochet.model.ComentarioProducto;
import com.miempresa.tienda_crochet.repository.ComentarioProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ComentarioProductoService {

    private final ComentarioProductoRepository comentarioProductoRepository;

    public ComentarioProductoService(ComentarioProductoRepository comentarioProductoRepository) {
        this.comentarioProductoRepository = comentarioProductoRepository;
    }

    public List<ComentarioProducto> listarTodos() {
        return comentarioProductoRepository.findAll();
    }

    public Optional<ComentarioProducto> obtenerPorId(Long id) {
        return comentarioProductoRepository.findById(id);
    }

    public ComentarioProducto guardar(ComentarioProducto comentarioProducto) {
        return comentarioProductoRepository.save(comentarioProducto);
    }

    public void eliminar(Long id) {
        comentarioProductoRepository.deleteById(id);
    }
    
    public List<ComentarioProducto> obtenerPorProductoId(Long productoId) {
        return comentarioProductoRepository.findByProductoId(productoId);
    }

}
