package com.generation._taEsencia.service;

import com.generation._taEsencia.model.Carrito;
import com.generation._taEsencia.repository.CarritoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarritoService {

    @Autowired
    private CarritoRepository carritoRepository;

    // Obtener todos los carritos
    public List<Carrito> leerCarrito() {
        return carritoRepository.findAll();
    }

    // Crear carrito
    public Carrito crearCarrito(Carrito carrito) {
        return carritoRepository.save(carrito);
    }

    // Buscar carrito por ID
    public Optional<Carrito> buscarPorId(Long id_carrito) {
        return carritoRepository.findById(id_carrito);
    }

    // Eliminar carrito
    public void borrarCarrito(Long id_carrito) {
        carritoRepository.deleteById(id_carrito);
    }
}