package com.miempresa.tienda_crochet.controller;

import com.miempresa.tienda_crochet.dto.ComentarioCreateDTO;
import com.miempresa.tienda_crochet.dto.ComentarioResponseDTO;
import com.miempresa.tienda_crochet.mapper.ComentarioMapper;
import com.miempresa.tienda_crochet.model.ComentarioProducto;
import com.miempresa.tienda_crochet.model.Producto;
import com.miempresa.tienda_crochet.model.Usuario;
import com.miempresa.tienda_crochet.service.ComentarioProductoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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
    public ResponseEntity<List<ComentarioResponseDTO>> listarComentarios() {
        List<ComentarioResponseDTO> comentariosDTO = comentarioProductoService.listarTodos().stream()
            .map(ComentarioMapper::toDto)
            .toList();

        return ResponseEntity.ok(comentariosDTO);
    }


    @GetMapping("/producto/{productoId}")
    public ResponseEntity<List<ComentarioResponseDTO>> listarPorProducto(@PathVariable Long productoId) {
        List<ComentarioResponseDTO> comentariosDTO = comentarioProductoService.obtenerPorProductoId(productoId).stream()
            .map(ComentarioMapper::toDto)
            .toList();

        return ResponseEntity.ok(comentariosDTO);
    }


    @PostMapping
    public ResponseEntity<ComentarioProducto> crearComentario(@RequestBody ComentarioCreateDTO dto) {
        ComentarioProducto comentario = new ComentarioProducto();
        comentario.setComentario(dto.getComentario());
        comentario.setCalificacion(dto.getCalificacion());
        comentario.setFecha(new Date());

        // Asociar usuario
        Usuario usuario = new Usuario();
        usuario.setId(dto.getUsuarioId());
        comentario.setUsuario(usuario);

        // Asociar producto
        Producto producto = new Producto();
        producto.setId(dto.getProductoId());
        comentario.setProducto(producto);

        ComentarioProducto guardado = comentarioProductoService.guardar(comentario);
        return ResponseEntity.status(201).body(guardado);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        comentarioProductoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
