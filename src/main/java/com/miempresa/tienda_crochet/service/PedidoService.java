package com.miempresa.tienda_crochet.service;

import com.miempresa.tienda_crochet.dto.PedidoDTO;
import com.miempresa.tienda_crochet.model.Pedido;
import com.miempresa.tienda_crochet.model.Usuario;
import com.miempresa.tienda_crochet.repository.PedidoRepository;
import com.miempresa.tienda_crochet.repository.UsuarioRepository;

import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final UsuarioRepository usuarioRepository;

    public PedidoService(PedidoRepository pedidoRepository, UsuarioRepository usuarioRepository) {
        this.pedidoRepository = pedidoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public List<Pedido> listarTodos() {
        return pedidoRepository.findAll();
    }

    public Optional<Pedido> obtenerPorId(Long id) {
        return pedidoRepository.findById(id);
    }

    public Pedido guardar(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    public void eliminar(Long id) {
        pedidoRepository.deleteById(id);
    }
    
    public Pedido crearDesdeDTO(PedidoDTO dto) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(dto.getUsuarioId());
        if (usuarioOpt.isEmpty()) return null;

        Pedido pedido = new Pedido();
        pedido.setCliente(usuarioOpt.get());
        pedido.setFecha(new Date());
        pedido.setEstado(dto.getEstado());
        pedido.setTotal(dto.getTotal());
        pedido.setProductoIds(dto.getProductoIds());

        return pedidoRepository.save(pedido);
    }
    
    public List<Pedido> listarPorUsuarioId(Long usuarioId) {
        return pedidoRepository.findByClienteId(usuarioId);
    }

}
