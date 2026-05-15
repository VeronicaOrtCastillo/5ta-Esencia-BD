package com.generation._taEsencia.service;

import com.generation._taEsencia.model.Carrito;
import com.generation._taEsencia.model.DetalleCarrito;
import com.generation._taEsencia.model.Producto;
import com.generation._taEsencia.model.Usuario;
import com.generation._taEsencia.repository.CarritoRepository;
import com.generation._taEsencia.repository.DetalleCarritoRepository;
import com.generation._taEsencia.repository.ProductoRepository;
import com.generation._taEsencia.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CarritoService {

    private final CarritoRepository carritoRepository;
    private final DetalleCarritoRepository detalleCarritoRepository;
    private final ProductoRepository productoRepository;
    private final UsuarioRepository usuarioRepository;

    public CarritoService(CarritoRepository carritoRepository,
                          DetalleCarritoRepository detalleCarritoRepository,
                          ProductoRepository productoRepository,
                          UsuarioRepository usuarioRepository) {
        this.carritoRepository = carritoRepository;
        this.detalleCarritoRepository = detalleCarritoRepository;
        this.productoRepository = productoRepository;
        this.usuarioRepository = usuarioRepository;
    }

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

    // Buscar o crear carrito del usuario logueado
    public Carrito obtenerOCrearCarritoPorCorreo(String correo) {

        Usuario usuario = usuarioRepository.findByCorreo(correo)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con correo: " + correo));

        return carritoRepository.findByUsuario(usuario)
                .orElseGet(() -> {
                    Carrito nuevoCarrito = new Carrito();
                    nuevoCarrito.setUsuario(usuario);
                    nuevoCarrito.setFecha_creacion(LocalDateTime.now());
                    return carritoRepository.save(nuevoCarrito);
                });
    }

    // Agregar producto al carrito del usuario logueado
    public DetalleCarrito agregarProductoAlCarrito(String correo, Long id_producto) {

        Carrito carrito = obtenerOCrearCarritoPorCorreo(correo);

        Producto producto = productoRepository.findById(id_producto)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + id_producto));

        Optional<DetalleCarrito> detalleExistente =
                detalleCarritoRepository.findByCarritoAndProducto(carrito, producto);

        if (detalleExistente.isPresent()) {

            DetalleCarrito detalle = detalleExistente.get();
            detalle.setCantidad(detalle.getCantidad() + 1);

            return detalleCarritoRepository.save(detalle);

        } else {

            DetalleCarrito nuevoDetalle = new DetalleCarrito();
            nuevoDetalle.setCarrito(carrito);
            nuevoDetalle.setProducto(producto);
            nuevoDetalle.setCantidad(1);
            nuevoDetalle.setPrecio_unitario(producto.getPrecio());

            return detalleCarritoRepository.save(nuevoDetalle);
        }
    }

    // Ver productos del carrito del usuario logueado
    public List<DetalleCarrito> verMiCarrito(String correo) {

        Carrito carrito = obtenerOCrearCarritoPorCorreo(correo);

        return detalleCarritoRepository.findByCarrito(carrito);
    }

    // Aumentar cantidad de un producto en el carrito
    public DetalleCarrito aumentarCantidad(String correo, Long id_producto) {

        Carrito carrito = obtenerOCrearCarritoPorCorreo(correo);

        Producto producto = productoRepository.findById(id_producto)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + id_producto));

        DetalleCarrito detalle = detalleCarritoRepository.findByCarritoAndProducto(carrito, producto)
                .orElseThrow(() -> new RuntimeException("El producto no está en el carrito"));

        detalle.setCantidad(detalle.getCantidad() + 1);

        return detalleCarritoRepository.save(detalle);
    }

    // Disminuir cantidad de un producto en el carrito
    public DetalleCarrito disminuirCantidad(String correo, Long id_producto) {

        Carrito carrito = obtenerOCrearCarritoPorCorreo(correo);

        Producto producto = productoRepository.findById(id_producto)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + id_producto));

        DetalleCarrito detalle = detalleCarritoRepository.findByCarritoAndProducto(carrito, producto)
                .orElseThrow(() -> new RuntimeException("El producto no está en el carrito"));

        if (detalle.getCantidad() > 1) {
            detalle.setCantidad(detalle.getCantidad() - 1);
            return detalleCarritoRepository.save(detalle);
        } else {
            detalleCarritoRepository.delete(detalle);
            return null;
        }
    }

    // Eliminar producto del carrito
    public void eliminarProductoDelCarrito(String correo, Long id_producto) {

        Carrito carrito = obtenerOCrearCarritoPorCorreo(correo);

        Producto producto = productoRepository.findById(id_producto)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + id_producto));

        DetalleCarrito detalle = detalleCarritoRepository.findByCarritoAndProducto(carrito, producto)
                .orElseThrow(() -> new RuntimeException("El producto no está en el carrito"));

        detalleCarritoRepository.delete(detalle);
    }
}