package com.generation._taEsencia.repository;

import com.generation._taEsencia.model.Carrito;
import com.generation._taEsencia.model.DetalleCarrito;
import com.generation._taEsencia.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DetalleCarritoRepository extends JpaRepository<DetalleCarrito, Long> {

    List<DetalleCarrito> findByCarrito(Carrito carrito);

    Optional<DetalleCarrito> findByCarritoAndProducto(Carrito carrito, Producto producto);

    List<DetalleCarrito> findByProducto(Producto producto);
}