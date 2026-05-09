package com.generation._taEsencia.controller;

import com.generation._taEsencia.model.DetallePedido;
import com.generation._taEsencia.service.DetallePedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/detalles-pedidos")
@CrossOrigin(origins = "*")
public class DetallePedidoController {

    @Autowired
    private DetallePedidoService detallePedidoService;

    @GetMapping
    public List<DetallePedido> getAll() {
        return detallePedidoService.leerDetalles();
    }

    @PostMapping
    public DetallePedido save(@RequestBody DetallePedido detallePedido) {
        return detallePedidoService.crearDetalle(detallePedido);
    }

    @GetMapping("/{id_detalle_pedido}")
    public DetallePedido getById(@PathVariable Long id_detalle_pedido) {
        return detallePedidoService.buscarPorId(id_detalle_pedido)
                .orElseThrow(() -> new RuntimeException("Detalle no encontrado con id: " + id_detalle_pedido));
    }

    @DeleteMapping("/{id_detalle_pedido}")
    public void delete(@PathVariable Long id_detalle_pedido) {
        detallePedidoService.borrarDetalle(id_detalle_pedido);
    }

    @PutMapping("/{id_detalle_pedido}")
    public DetallePedido update(@PathVariable Long id_detalle_pedido, @RequestBody DetallePedido detallesNuevos) {
        return detallePedidoService.buscarPorId(id_detalle_pedido).map(detalle -> {

            detalle.setId_detalle_pedido(id_detalle_pedido);
            detalle.setPedido(detallesNuevos.getPedido());
            detalle.setProducto(detallesNuevos.getProducto());
            detalle.setCantidad(detallesNuevos.getCantidad());
            detalle.setPrecioUnitario(detallesNuevos.getPrecioUnitario());
            detalle.setSubtotal(detallesNuevos.getSubtotal());

            return detallePedidoService.crearDetalle(detalle);
        }).orElseThrow(() -> new RuntimeException("Detalle no encontrado con id: " + id_detalle_pedido));
    }
}