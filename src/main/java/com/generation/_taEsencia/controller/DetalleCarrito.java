package com.generation._taEsencia.controller;

import jakarta.persistence.*;

@Entity
@Table(name = "detalle_carrito")
public class DetalleCarrito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_detalle_carrito;

    private Long id_carrito;

    private Long id_producto;

    private Long cantidad;

    private Double precio_unitario;

    // GETTERS Y SETTERS

    public Long getId_detalle_carrito() {
        return id_detalle_carrito;
    }

    public void setId_detalle_carrito(Long id_detalle_carrito) {
        this.id_detalle_carrito = id_detalle_carrito;
    }

    public Long getId_carrito() {
        return id_carrito;
    }

    public void setId_carrito(Long id_carrito) {
        this.id_carrito = id_carrito;
    }

    public Long getId_producto() {
        return id_producto;
    }

    public void setId_producto(Long id_producto) {
        this.id_producto = id_producto;
    }

    public Long getCantidad() {
        return cantidad;
    }

    public void setCantidad(Long cantidad) {
        this.cantidad = cantidad;
    }

    public Double getPrecio_unitario() {
        return precio_unitario;
    }

    public void setPrecio_unitario(Double precio_unitario) {
        this.precio_unitario = precio_unitario;
    }
}