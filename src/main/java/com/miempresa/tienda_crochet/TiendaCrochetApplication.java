package com.miempresa.tienda_crochet;

import com.miempresa.tienda_crochet.model.Rol;
import com.miempresa.tienda_crochet.model.Categoria;
import com.miempresa.tienda_crochet.repository.RolRepository;
import com.miempresa.tienda_crochet.repository.CategoriaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TiendaCrochetApplication {

	public static void main(String[] args) {
		SpringApplication.run(TiendaCrochetApplication.class, args);
	}

	@Bean
	CommandLineRunner initData(RolRepository rolRepository, CategoriaRepository categoriaRepository) {
		return args -> {
			// 🛡️ Inicializar roles si están vacíos
			if (rolRepository.findAll().isEmpty()) {
				rolRepository.save(new Rol(null, "CLIENTE"));
				rolRepository.save(new Rol(null, "VENDEDOR"));
				rolRepository.save(new Rol(null, "ADMIN"));
			}

			// 🧶 Inicializar categorías si están vacías
			if (categoriaRepository.findAll().isEmpty()) {
				categoriaRepository.save(new Categoria(null, "Accesorios"));
				categoriaRepository.save(new Categoria(null, "Hogar"));
				categoriaRepository.save(new Categoria(null, "Muñecos"));
				categoriaRepository.save(new Categoria(null, "Temporada"));
			}
		};
	}
}
