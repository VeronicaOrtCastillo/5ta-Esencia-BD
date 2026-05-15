package com.generation._taEsencia.repository;

import com.generation._taEsencia.model.Carrito;
import com.generation._taEsencia.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarritoRepository extends JpaRepository<Carrito, Long> {

    Optional<Carrito> findByUsuario(Usuario usuario);
}