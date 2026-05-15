package com.generation._taEsencia.repository;

import com.generation._taEsencia.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByCorreo(String correo);

    Optional<Usuario> findByTelefono(String telefono);

    boolean existsByCorreo(String correo);

    boolean existsByTelefono(String telefono);
}