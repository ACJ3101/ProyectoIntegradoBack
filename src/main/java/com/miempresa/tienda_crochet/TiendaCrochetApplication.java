package com.miempresa.tienda_crochet;

import com.miempresa.tienda_crochet.model.Rol;
import com.miempresa.tienda_crochet.model.Usuario;
import com.miempresa.tienda_crochet.model.Categoria;
import com.miempresa.tienda_crochet.repository.RolRepository;
import com.miempresa.tienda_crochet.repository.UsuarioRepository;
import com.miempresa.tienda_crochet.repository.CategoriaRepository;

import java.util.Date;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class TiendaCrochetApplication {

	public static void main(String[] args) {
		SpringApplication.run(TiendaCrochetApplication.class, args);
	}

	@Bean
	CommandLineRunner initData(
	        RolRepository rolRepository,
	        CategoriaRepository categoriaRepository,
	        UsuarioRepository usuarioRepository,
	        PasswordEncoder passwordEncoder) {
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

	     // 🔐 Crear usuario administrador si no existe
	        if (usuarioRepository.findByEmail("admin@tienda.com").isEmpty()) {
	            Rol rolAdmin = rolRepository.findByNombre("ADMIN").orElse(null);

	            if (rolAdmin != null) {
	                Usuario admin = new Usuario();
	                admin.setNombre("Administrador");
	                admin.setApellidos("Del Sistema");
	                admin.setNick("admin");
	                admin.setEmail("admin@tienda.com");
	                admin.setDireccion("Oficina principal");
	                admin.setFechaRegistro(new Date());
	                admin.setRol(rolAdmin);
	                admin.setContraseña(passwordEncoder.encode("admin123")); // ✅ Contraseña segura

	                usuarioRepository.save(admin);
	                System.out.println("✅ Usuario administrador creado.");
	            } else {
	                System.out.println("❌ Rol ADMIN no encontrado.");
	            }
	        } else {
	            System.out.println("ℹ️ El usuario administrador ya existe.");
	        }

	};

}
}
