package com.generation._taEsencia.controller;

import com.generation._taEsencia.model.Carrito;
import com.generation._taEsencia.model.DetalleCarrito;
import com.generation._taEsencia.service.CarritoService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carrito")
@CrossOrigin(origins = "*")
public class CarritoController {

    private final CarritoService carritoService;

    public CarritoController(CarritoService carritoService) {
        this.carritoService = carritoService;
    }

    @GetMapping
    public List<Carrito> getAll() {
        return carritoService.leerCarrito();
    }

    @PostMapping
    public Carrito save(@RequestBody Carrito carrito) {
        return carritoService.crearCarrito(carrito);
    }

    @GetMapping("/{id_carrito}")
    public Carrito getById(@PathVariable Long id_carrito) {
        return carritoService.buscarPorId(id_carrito)
                .orElseThrow(() -> new RuntimeException("Carrito no encontrado con id: " + id_carrito));
    }

    @DeleteMapping("/{id_carrito}")
    public void delete(@PathVariable Long id_carrito) {
        carritoService.borrarCarrito(id_carrito);
    }

    // Agregar producto al carrito
    @PostMapping("/agregar/{id_producto}")
    public DetalleCarrito agregarProducto(
            @PathVariable Long id_producto,
            Authentication authentication
    ) {
        String correo = authentication.getName();
        return carritoService.agregarProductoAlCarrito(correo, id_producto);
    }

    // Ver carrito del usuario logueado
    @GetMapping("/mi-carrito")
    public List<DetalleCarrito> verMiCarrito(Authentication authentication) {
        String correo = authentication.getName();
        return carritoService.verMiCarrito(correo);
    }

    // Aumentar cantidad usando POST para evitar problema con PUT
    @PostMapping("/aumentar/{id_producto}")
    public DetalleCarrito aumentarCantidad(
            @PathVariable Long id_producto,
            Authentication authentication
    ) {
        System.out.println("ENTRÓ A /api/carrito/aumentar/" + id_producto);
        System.out.println("Usuario autenticado: " + authentication.getName());

        String correo = authentication.getName();
        return carritoService.aumentarCantidad(correo, id_producto);
    }

    // Disminuir cantidad usando POST para evitar problema con PUT
    @PostMapping("/disminuir/{id_producto}")
    public DetalleCarrito disminuirCantidad(
            @PathVariable Long id_producto,
            Authentication authentication
    ) {
        String correo = authentication.getName();
        return carritoService.disminuirCantidad(correo, id_producto);
    }

    // Eliminar producto usando POST para evitar problema con DELETE
    @PostMapping("/eliminar/{id_producto}")
    public void eliminarProducto(
            @PathVariable Long id_producto,
            Authentication authentication
    ) {
        String correo = authentication.getName();
        carritoService.eliminarProductoDelCarrito(correo, id_producto);
    }
}