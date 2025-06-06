package com.miempresa.tienda_crochet.repository;

import com.miempresa.tienda_crochet.model.Pedido;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
	
	List<Pedido> findByClienteId(Long usuarioId);
	
	
	
}
