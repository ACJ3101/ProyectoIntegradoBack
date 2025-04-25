package com.miempresa.tienda_crochet.controller;

import com.miempresa.tienda_crochet.model.ComentarioProducto;
import com.miempresa.tienda_crochet.service.ComentarioProductoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comentarios")
@CrossOrigin("*")
public class ComentarioProductoController {

    private final ComentarioProductoService comentarioProductoService;

    public ComentarioProductoController(ComentarioProductoService comentarioProductoService) {
        this.comentarioProductoService = comentarioProductoService;
    }

    @GetMapping
    public List<ComentarioProducto> listar() {
        return comentarioProductoService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ComentarioProducto> obtener(@PathVariable Long id) {
        return comentarioProductoService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ComentarioProducto> crear(@RequestBody ComentarioProducto comentario) {
        return ResponseEntity.status(201).body(comentarioProductoService.guardar(comentario));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        comentarioProductoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
