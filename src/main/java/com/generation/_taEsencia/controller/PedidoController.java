package com.generation._taEsencia.controller;

import com.generation._taEsencia.model.Pedido;
import com.generation._taEsencia.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
@CrossOrigin(origins = "*")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    public List<Pedido> getAllPedidos() {
        return pedidoService.leerPedidos();
    }

    @PostMapping
    public Pedido savePedidos(@RequestBody Pedido pedido) {
        return pedidoService.crearPedido(pedido);
    }

    @GetMapping("/{id_pedido}")
    public Pedido getPedidoById(@PathVariable Long id_pedido) {
        return pedidoService.buscarPorId(id_pedido)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado con id: " + id_pedido));
    }

    @DeleteMapping("/{id_pedido}")
    public void deletePedido(@PathVariable Long id_pedido) {
        pedidoService.borrarPedido(id_pedido);
    }

    @PutMapping("/{id_pedido}")
    public Pedido updatePedido(@PathVariable Long id_pedido, @RequestBody Pedido pedidoDetalles) {
        return pedidoService.buscarPorId(id_pedido).map(pedido -> {

            pedido.setId_pedido(id_pedido);
            pedido.setUsuario(pedidoDetalles.getUsuario());
            pedido.setFecha(pedidoDetalles.getFecha());
            pedido.setTotal(pedidoDetalles.getTotal());
            pedido.setEstado(pedidoDetalles.getEstado());

            return pedidoService.crearPedido(pedido);
        }).orElseThrow(() -> new RuntimeException("Pedido no encontrado con id: " + id_pedido));
    }
}