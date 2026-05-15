package com.generation._taEsencia.service;

import com.generation._taEsencia.model.Pedido;
import com.generation._taEsencia.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public List<Pedido> leerPedidos() {
        return pedidoRepository.findAll();
    }

    public Pedido crearPedido(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    public Optional<Pedido> buscarPorId(Long id_pedido) {
        return pedidoRepository.findById(id_pedido);
    }

    public void borrarPedido(Long id_pedido) {
        pedidoRepository.deleteById(id_pedido);
    }
}