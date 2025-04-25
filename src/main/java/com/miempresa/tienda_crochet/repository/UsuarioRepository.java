package com.miempresa.tienda_crochet.repository;

import com.miempresa.tienda_crochet.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
