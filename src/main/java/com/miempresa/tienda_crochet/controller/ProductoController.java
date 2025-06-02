package com.miempresa.tienda_crochet.controller;

import com.miempresa.tienda_crochet.dto.ProductoCreateDTO;
import com.miempresa.tienda_crochet.dto.ProductoDTO;
import com.miempresa.tienda_crochet.dto.ProductoUpdateDTO;
import com.miempresa.tienda_crochet.dto.StockUpdateListDTO;
import com.miempresa.tienda_crochet.mapper.ProductoMapper;
import com.miempresa.tienda_crochet.model.Categoria;
import com.miempresa.tienda_crochet.model.Producto;
import com.miempresa.tienda_crochet.model.Usuario;
import com.miempresa.tienda_crochet.service.CategoriaService;
import com.miempresa.tienda_crochet.service.ProductoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin("*")
public class ProductoController {

    private final ProductoService productoService;
    private final CategoriaService categoriaService;

    public ProductoController(ProductoService productoService, CategoriaService categoriaService) {
        this.productoService = productoService;
        this.categoriaService = categoriaService;
    }
    @GetMapping
    public List<ProductoDTO> listarProductos() {
        return productoService.listarTodos()
                .stream()
                .map(ProductoMapper::toDto)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoDTO> obtenerPorId(@PathVariable Long id) {
        return productoService.obtenerPorId(id)
                .map(ProductoMapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Producto> crearProducto(@RequestBody ProductoCreateDTO dto) {
        Producto producto = new Producto();
        producto.setNombre(dto.getNombre());
        producto.setDescripcion(dto.getDescripcion());
        producto.setPrecio(dto.getPrecio());
        producto.setStock(dto.getStock());
        producto.setImagenUrl(dto.getImagenUrl());
        producto.setPublicado(dto.isPublicado());
        producto.setFechaCreacion(new Date());

        // Asignar Usuario
        Usuario usuario = new Usuario();
        usuario.setId(dto.getUsuarioId());
        producto.setUsuario(usuario);

        // Asignar Categoría
        Categoria categoria = new Categoria();
        categoria.setId(dto.getCategoriaId());
        producto.setCategoria(categoria);

        Producto guardado = productoService.guardar(producto);
        return ResponseEntity.status(201).body(guardado);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        productoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/categoria/{categoriaId}")
    public ResponseEntity<List<ProductoDTO>> obtenerPorCategoria(@PathVariable Long categoriaId) {
        List<Producto> productos = productoService.obtenerPorCategoriaId(categoriaId);
        List<ProductoDTO> productosDTO = productos.stream()
                .map(ProductoMapper::toDto)
                .toList();
        return ResponseEntity.ok(productosDTO);
    }
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<ProductoDTO>> obtenerProductosPorUsuario(@PathVariable Long usuarioId) {
        List<Producto> productos = productoService.obtenerPorUsuarioId(usuarioId);
        List<ProductoDTO> productosDTO = productos.stream()
                .map(ProductoMapper::toDto)
                .toList();
        return ResponseEntity.ok(productosDTO);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ProductoDTO> actualizarProducto(
            @PathVariable Long id,
            @RequestBody ProductoUpdateDTO dto) {

        Optional<Producto> productoOpt = productoService.obtenerPorId(id);
        if (productoOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Producto producto = productoOpt.get();
        // Actualizar campos...
        producto.setNombre(dto.getNombre());
        producto.setDescripcion(dto.getDescripcion());
        producto.setPrecio(dto.getPrecio());
        producto.setStock(dto.getStock());
        producto.setImagenUrl(dto.getImagenUrl());
        producto.setPublicado(dto.isPublicado());

        Categoria categoria = categoriaService.obtenerPorId(dto.getCategoriaId());
        producto.setCategoria(categoria);

        Producto actualizado = productoService.guardar(producto);
        return ResponseEntity.ok(ProductoMapper.toDto(actualizado));
    }
    
    @PostMapping("/actualizar-stock")
    public ResponseEntity<Void> actualizarStock(@RequestBody StockUpdateListDTO dto) {
        productoService.actualizarStock(dto.getProductos());
        return ResponseEntity.ok().build();
    }




}
