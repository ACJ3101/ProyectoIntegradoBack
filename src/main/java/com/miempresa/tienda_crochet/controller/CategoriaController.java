package com.miempresa.tienda_crochet.controller;

import com.miempresa.tienda_crochet.dto.CategoriaDTO;
import com.miempresa.tienda_crochet.mapper.CategoriaMapper;
import com.miempresa.tienda_crochet.model.Categoria;
import com.miempresa.tienda_crochet.service.CategoriaService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
@CrossOrigin("*")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping
    public List<CategoriaDTO> listar() {
        return categoriaService.listarTodas()
                .stream()
                .map(CategoriaMapper::toDto)
                .toList();
    }

    


    @PostMapping
    public Categoria crear(@RequestBody Categoria categoria) {
        return categoriaService.guardar(categoria);
    }
}
