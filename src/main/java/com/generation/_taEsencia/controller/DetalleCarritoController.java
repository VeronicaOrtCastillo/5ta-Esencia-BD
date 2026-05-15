package com.generation._taEsencia.controller;

import com.generation._taEsencia.model.DetalleCarrito;
import com.generation._taEsencia.service.DetalleCarritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/detalles-carrito")
@CrossOrigin(origins = "*")
public class DetalleCarritoController {

    @Autowired
    private DetalleCarritoService detalleCarritoService;

    // Obtener todos los detalles del carrito
    @GetMapping
    public List<DetalleCarrito> getAll() {
        return detalleCarritoService.leerDetalles();
    }

    // Crear un nuevo detalle de carrito
    @PostMapping
    public DetalleCarrito save(@RequestBody DetalleCarrito detalleCarrito) {
        return detalleCarritoService.crearDetalle(detalleCarrito);
    }

    // Obtener detalle de carrito por ID
    @GetMapping("/{id_detalle_carrito}")
    public DetalleCarrito getById(@PathVariable Long id_detalle_carrito) {
        return detalleCarritoService.buscarPorId(id_detalle_carrito)
                .orElseThrow(() -> new RuntimeException(
                        "Detalle de carrito no encontrado con id: " + id_detalle_carrito
                ));
    }

    // Eliminar detalle de carrito por ID
    @DeleteMapping("/{id_detalle_carrito}")
    public void delete(@PathVariable Long id_detalle_carrito) {
        detalleCarritoService.borrarDetalle(id_detalle_carrito);
    }

    // Actualizar detalle de carrito
    @PutMapping("/{id_detalle_carrito}")
    public DetalleCarrito update(@PathVariable Long id_detalle_carrito,
                                 @RequestBody DetalleCarrito detallesNuevos) {

        return detalleCarritoService.buscarPorId(id_detalle_carrito).map(detalle -> {

            detalle.setId_detalle_carrito(id_detalle_carrito);
            detalle.setCarrito(detallesNuevos.getCarrito());
            detalle.setProducto(detallesNuevos.getProducto());
            detalle.setCantidad(detallesNuevos.getCantidad());
            detalle.setPrecio_unitario(detallesNuevos.getPrecio_unitario());

            return detalleCarritoService.crearDetalle(detalle);

        }).orElseThrow(() -> new RuntimeException(
                "Detalle de carrito no encontrado con id: " + id_detalle_carrito
        ));
    }
}