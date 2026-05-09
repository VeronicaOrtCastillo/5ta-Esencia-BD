package com.generation._taEsencia.service;

import com.generation._taEsencia.model.DetallePedido;
import com.generation._taEsencia.repository.DetallePedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DetallePedidoService {

    @Autowired
    private DetallePedidoRepository detallePedidoRepository;

    public List<DetallePedido> leerDetalles() {
        return detallePedidoRepository.findAll();
    }

    public DetallePedido crearDetalle(DetallePedido detallePedido) {
        return detallePedidoRepository.save(detallePedido);
    }

    public Optional<DetallePedido> buscarPorId(Long id_detalle_pedido) {
        return detallePedidoRepository.findById(id_detalle_pedido);
    }

    public void borrarDetalle(Long id_detalle_pedido) {
        detallePedidoRepository.deleteById(id_detalle_pedido);
    }
}