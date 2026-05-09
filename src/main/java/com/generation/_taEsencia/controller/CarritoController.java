package com.generation._taEsencia.controller;

import com.generation._taEsencia.model.Carrito;
import com.generation._taEsencia.service.CarritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carrito")
@CrossOrigin(origins = "*")
public class CarritoController {

    @Autowired
    private CarritoService carritoService;

    // Obtener todos los carritos
    @GetMapping
    public List<Carrito> getAll() {
        return carritoService.leerCarrito();
    }

    // Crear carrito
    @PostMapping
    public Carrito save(@RequestBody Carrito carrito) {
        return carritoService.crearCarrito(carrito);
    }

    // Obtener carrito por ID
    @GetMapping("/{id_carrito}")
    public Carrito getById(@PathVariable Long id_carrito) {
        return carritoService.buscarPorId(id_carrito)
                .orElseThrow(() ->
                        new RuntimeException("Carrito no encontrado con id: " + id_carrito));
    }

    // Eliminar carrito
    @DeleteMapping("/{id_carrito}")
    public void delete(@PathVariable Long id_carrito) {
        carritoService.borrarCarrito(id_carrito);
    }

    // Actualizar carrito
    @PutMapping("/{id_carrito}")
    public Carrito update(@PathVariable Long id_carrito,
                          @RequestBody Carrito detalles) {

        return carritoService.buscarPorId(id_carrito).map(carrito -> {

            carrito.setUsuario(detalles.getUsuario());
            carrito.setFecha_creacion(detalles.getFecha_creacion());

            return carritoService.crearCarrito(carrito);

        }).orElseThrow(() ->
                new RuntimeException("Carrito no encontrado con id: " + id_carrito));
    }
}