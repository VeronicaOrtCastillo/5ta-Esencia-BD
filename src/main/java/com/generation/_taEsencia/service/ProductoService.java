package com.generation._taEsencia.service;

import com.generation._taEsencia.model.Producto;
import com.generation._taEsencia.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> leerProductos() {
        return productoRepository.findAll();
    }

    public Producto crearProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    public Optional<Producto> buscarPorId(Long id_producto) {
        return productoRepository.findById(id_producto);
    }

    public void borrarProducto(Long id_producto) {
        productoRepository.deleteById(id_producto);
    }
}