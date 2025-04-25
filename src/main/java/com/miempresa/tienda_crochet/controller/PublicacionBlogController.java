package com.miempresa.tienda_crochet.controller;

import com.miempresa.tienda_crochet.model.PublicacionBlog;
import com.miempresa.tienda_crochet.service.PublicacionBlogService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/publicaciones")
@CrossOrigin("*")
public class PublicacionBlogController {

    private final PublicacionBlogService publicacionBlogService;

    public PublicacionBlogController(PublicacionBlogService publicacionBlogService) {
        this.publicacionBlogService = publicacionBlogService;
    }

    @GetMapping
    public List<PublicacionBlog> listar() {
        return publicacionBlogService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PublicacionBlog> obtener(@PathVariable Long id) {
        return publicacionBlogService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<PublicacionBlog> crear(@RequestBody PublicacionBlog publicacion) {
        return ResponseEntity.status(201).body(publicacionBlogService.guardar(publicacion));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        publicacionBlogService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
