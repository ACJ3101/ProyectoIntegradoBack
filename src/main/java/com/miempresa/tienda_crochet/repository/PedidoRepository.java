package com.miempresa.tienda_crochet.repository;

import com.miempresa.tienda_crochet.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
