package com.generation._taEsencia.service;

import com.generation._taEsencia.model.DetalleCarrito;
import com.generation._taEsencia.repository.DetalleCarritoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DetalleCarritoService {

    @Autowired
    private DetalleCarritoRepository detalleCarritoRepository;

    // Crear
    public DetalleCarrito crearDetalle(DetalleCarrito detalle) {
        return detalleCarritoRepository.save(detalle);
    }

    // Leer todos
    public List<DetalleCarrito> leerDetalles() {
        return detalleCarritoRepository.findAll();
    }

    // Buscar por ID
    public Optional<DetalleCarrito> buscarPorId(Long id) {
        return detalleCarritoRepository.findById(id);
    }

    // Eliminar
    public void borrarDetalle(Long id) {
        detalleCarritoRepository.deleteById(id);
    }
}