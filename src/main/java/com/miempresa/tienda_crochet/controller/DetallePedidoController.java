package com.miempresa.tienda_crochet.controller;

import com.miempresa.tienda_crochet.model.DetallePedido;
import com.miempresa.tienda_crochet.service.DetallePedidoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/detalles")
@CrossOrigin("*")
public class DetallePedidoController {

    private final DetallePedidoService detallePedidoService;

    public DetallePedidoController(DetallePedidoService detallePedidoService) {
        this.detallePedidoService = detallePedidoService;
    }

    @GetMapping
    public List<DetallePedido> listar() {
        return detallePedidoService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetallePedido> obtener(@PathVariable Long id) {
        return detallePedidoService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<DetallePedido> crear(@RequestBody DetallePedido detalle) {
        return ResponseEntity.status(201).body(detallePedidoService.guardar(detalle));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        detallePedidoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
