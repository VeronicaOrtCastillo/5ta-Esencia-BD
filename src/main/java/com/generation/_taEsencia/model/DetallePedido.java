package com.generation._taEsencia.model;
import jakarta.persistence.*;

@Entity
@Table(name = "detalle_pedido")
public class DetallePedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_detalle_pedido;

    @ManyToOne
    @JoinColumn(name = "id_pedido", nullable = false)
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "id_producto", nullable = false)
    private Producto producto;

    @Column(nullable = false)
    private Integer cantidad;

    @Column(name = "precio_unitario", nullable = false)
    private Double precioUnitario;

    @Column(nullable = false)
    private Double subtotal;

    // --- CONSTRUCTORES ---

    public DetallePedido(){}

    public DetallePedido(Long id_detalle_pedido, Pedido pedido, Integer cantidad, Producto producto, Double precioUnitario, Double subtotal) {
        this.id_detalle_pedido = id_detalle_pedido;
        this.pedido = pedido;
        this.cantidad = cantidad;
        this.producto = producto;
        this.precioUnitario = precioUnitario;
        this.subtotal = subtotal;
    }

    // --- GETTERS Y SETTERS ---
    public Long getId_detalle_pedido() { return id_detalle_pedido; }
    public void setId_detalle_pedido(Long id_detalle_pedido) { this.id_detalle_pedido = id_detalle_pedido; }

    public Pedido getPedido() { return pedido; }
    public void setPedido(Pedido pedido) { this.pedido = pedido; }

    public Producto getProducto() { return producto; }
    public void setProducto(Producto producto) { this.producto = producto; }

    public Integer getCantidad() { return cantidad; }
    public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }

    public Double getPrecioUnitario() { return precioUnitario; }
    public void setPrecioUnitario(Double precioUnitario) { this.precioUnitario = precioUnitario; }

    public Double getSubtotal() { return subtotal; }
    public void setSubtotal(Double subtotal) { this.subtotal = subtotal; }
}
