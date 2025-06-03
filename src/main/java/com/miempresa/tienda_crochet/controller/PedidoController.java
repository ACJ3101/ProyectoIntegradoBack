package com.miempresa.tienda_crochet.controller;

import com.miempresa.tienda_crochet.dto.PedidoDTO;
import com.miempresa.tienda_crochet.dto.PedidoResponseDTO;
import com.miempresa.tienda_crochet.model.Pedido;
import com.miempresa.tienda_crochet.model.Usuario;
import com.miempresa.tienda_crochet.repository.PedidoRepository;
import com.miempresa.tienda_crochet.repository.UsuarioRepository;
import com.miempresa.tienda_crochet.service.PedidoService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/pedidos")
@CrossOrigin("*")
public class PedidoController {

    private final PedidoService pedidoService;
    private final UsuarioRepository usuarioRepository;
    private final PedidoRepository pedidoRepository;

    public PedidoController(PedidoService pedidoService,
                            UsuarioRepository usuarioRepository,
                            PedidoRepository pedidoRepository) {
        this.pedidoService = pedidoService;
        this.usuarioRepository = usuarioRepository;
        this.pedidoRepository = pedidoRepository;
    }

    @GetMapping
    public ResponseEntity<List<PedidoResponseDTO>> listar() {
        List<PedidoResponseDTO> pedidos = pedidoService.listarTodos()
            .stream()
            .map(this::convertirAPedidoDTO)
            .collect(Collectors.toList());

        return ResponseEntity.ok(pedidos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoResponseDTO> obtener(@PathVariable Long id) {
        Optional<Pedido> pedidoOpt = pedidoService.obtenerPorId(id);
        return pedidoOpt.map(p -> ResponseEntity.ok(convertirAPedidoDTO(p)))
                        .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<PedidoResponseDTO> crearPedido(@RequestBody PedidoDTO dto) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(dto.getUsuarioId());

        if (usuarioOpt.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        Pedido pedido = new Pedido();
        pedido.setFecha(new Date());
        pedido.setCliente(usuarioOpt.get());
        pedido.setEstado(dto.getEstado());
        pedido.setTotal(dto.getTotal());
        pedido.setProductoIds(dto.getProductoIds());

        Pedido pedidoGuardado = pedidoRepository.save(pedido);
        return ResponseEntity.status(HttpStatus.CREATED).body(convertirAPedidoDTO(pedidoGuardado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        pedidoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<PedidoResponseDTO>> obtenerPedidosPorUsuario(@PathVariable Long usuarioId) {
        List<Pedido> pedidos = pedidoService.listarPorUsuarioId(usuarioId);
        List<PedidoResponseDTO> pedidosDTO = pedidos.stream()
                .map(this::convertirAPedidoDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(pedidosDTO);
    }

    // ✅ Método para transformar Pedido a PedidoResponseDTO incluyendo clienteId
    private PedidoResponseDTO convertirAPedidoDTO(Pedido pedido) {
        PedidoResponseDTO dto = new PedidoResponseDTO();
        dto.setId(pedido.getId());
        dto.setFecha(pedido.getFecha());
        dto.setEstado(pedido.getEstado());
        dto.setTotal(pedido.getTotal());
        dto.setClienteNick(pedido.getCliente().getNick());
        dto.setClienteId(pedido.getCliente().getId()); // 👈 Se añade el clienteId
        dto.setProductoIds(pedido.getProductoIds());
        return dto;
    }
}
