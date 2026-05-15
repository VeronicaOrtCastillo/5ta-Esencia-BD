package com.generation._taEsencia.controller;

import com.generation._taEsencia.model.Producto;
import com.generation._taEsencia.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "*")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    public List<Producto> getAllProductos() {
        return productoService.leerProductos();
    }

    @PostMapping
    public Producto saveProducto(@RequestBody Producto producto) {
        return productoService.crearProducto(producto);
    }

    // Cambiamos a id_producto para ser específicos
    @GetMapping("/{id_producto}")
    public Producto getProductoById(@PathVariable Long id_producto) {
        return productoService.buscarPorId(id_producto)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + id_producto));
    }

    @DeleteMapping("/{id_producto}")
    public void deleteProducto(@PathVariable Long id_producto) {
        productoService.borrarProducto(id_producto);
    }

    @PutMapping("/{id_producto}")
    public Producto updateProducto(@PathVariable Long id_producto, @RequestBody Producto productoDetalles) {
        return productoService.buscarPorId(id_producto).map(producto -> {

            producto.setId_producto(id_producto);
            producto.setNombre(productoDetalles.getNombre());
            producto.setDescripcion(productoDetalles.getDescripcion());
            producto.setPrecio(productoDetalles.getPrecio());
            producto.setStock(productoDetalles.getStock());
            producto.setImagen(productoDetalles.getImagen());
            producto.setCategoria(productoDetalles.getCategoria());

            return productoService.crearProducto(producto);
        }).orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + id_producto));
    }

}