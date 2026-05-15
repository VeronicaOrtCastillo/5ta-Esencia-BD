package com.generation._taEsencia.model;

import jakarta.persistence.*;

@Entity
@Table(name = "detalle_carrito")
public class DetalleCarrito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_detalle_carrito;

    @ManyToOne
    @JoinColumn(name = "id_carrito", nullable = false)
    private Carrito carrito;

    @ManyToOne
    @JoinColumn(name = "id_producto", nullable = false)
    private Producto producto;

    private Integer cantidad;
    private Double precio_unitario;

    public DetalleCarrito(){}

    public DetalleCarrito(Long id_detalle_carrito, Carrito carrito, Producto producto, Integer cantidad, Double precio_unitario) {
        this.id_detalle_carrito = id_detalle_carrito;
        this.carrito = carrito;
        this.producto = producto;
        this.cantidad = cantidad;
        this.precio_unitario = precio_unitario;
    }

    public Long getId_detalle_carrito() {
        return id_detalle_carrito;
    }

    public void setId_detalle_carrito(Long id_detalle_carrito) {
        this.id_detalle_carrito = id_detalle_carrito;
    }

    public Carrito getCarrito() {
        return carrito;
    }

    public void setCarrito(Carrito carrito) {
        this.carrito = carrito;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getPrecio_unitario() {
        return precio_unitario;
    }

    public void setPrecio_unitario(Double precio_unitario) {
        this.precio_unitario = precio_unitario;
    }
}
