package com.generation._taEsencia.model;

import jakarta.persistence.*;
    @Entity
    @Table(name = "productos")
    public class Producto {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id_producto;

        @Column(nullable = false, length = 50)
        private String nombre;

        @Column(nullable = false, length = 500)
        private String descripcion;

        @Column(nullable = false)
        private Double precio;

        @Column(nullable = false)
        private Integer stock;

        @Column(length = 255)
        private String imagen;

        // Relación: Muchos productos pertenecen a una sola categoría
        @ManyToOne
        @JoinColumn(name = "id_categoria", nullable = false)
        private Categoria categoria;

        // --- CONSTRUCTORES ---
        public Producto() {}

        public Producto(Long id_producto, String nombre, String descripcion, Double precio, Categoria categoria, Integer stock, String imagen) {
            this.id_producto = id_producto;
            this.nombre = nombre;
            this.descripcion = descripcion;
            this.precio = precio;
            this.categoria = categoria;
            this.stock = stock;
            this.imagen = imagen;
        }

        // --- GETTERS Y SETTERS ---
        public Long getId_producto() { return id_producto; }
        public void setId_producto(Long id_producto) { this.id_producto = id_producto; }

        public String getNombre() { return nombre; }
        public void setNombre(String nombre) { this.nombre = nombre; }

        public String getDescripcion() { return descripcion; }
        public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

        public Double getPrecio() { return precio; }
        public void setPrecio(Double precio) { this.precio = precio; }

        public Integer getStock() { return stock; }
        public void setStock(Integer stock) { this.stock = stock; }

        public Categoria getCategoria() { return categoria; }
        public void setCategoria(Categoria categoria) { this.categoria = categoria; }

        public String getImagen() { return imagen; }
        public void setImagen(String imagen) { this.imagen = imagen; }
    }

