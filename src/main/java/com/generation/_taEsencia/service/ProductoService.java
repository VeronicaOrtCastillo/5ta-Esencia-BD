package com.generation._taEsencia.service;

import com.generation._taEsencia.model.DetalleCarrito;
import com.generation._taEsencia.model.Producto;
import com.generation._taEsencia.repository.DetalleCarritoRepository;
import com.generation._taEsencia.repository.ProductoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;
    private final DetalleCarritoRepository detalleCarritoRepository;

    public ProductoService(
            ProductoRepository productoRepository,
            DetalleCarritoRepository detalleCarritoRepository
    ) {
        this.productoRepository = productoRepository;
        this.detalleCarritoRepository = detalleCarritoRepository;
    }

    public List<Producto> leerProductos() {
        return productoRepository.findAll();
    }

    public Producto crearProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    public Optional<Producto> buscarPorId(Long id_producto) {
        return productoRepository.findById(id_producto);
    }

    public Producto actualizarProducto(Long id_producto, Producto productoActualizado) {
        Producto productoExistente = productoRepository.findById(id_producto)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + id_producto));

        productoExistente.setNombre(productoActualizado.getNombre());
        productoExistente.setDescripcion(productoActualizado.getDescripcion());
        productoExistente.setPrecio(productoActualizado.getPrecio());
        productoExistente.setStock(productoActualizado.getStock());
        productoExistente.setImagen(productoActualizado.getImagen());
        productoExistente.setCategoria(productoActualizado.getCategoria());

        return productoRepository.save(productoExistente);
    }

    @Transactional
    public void borrarProducto(Long id_producto) {
        Producto producto = productoRepository.findById(id_producto)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + id_producto));

        List<DetalleCarrito> detalles = detalleCarritoRepository.findByProducto(producto);

        if (!detalles.isEmpty()) {
            detalleCarritoRepository.deleteAll(detalles);
        }

        productoRepository.delete(producto);
    }
}