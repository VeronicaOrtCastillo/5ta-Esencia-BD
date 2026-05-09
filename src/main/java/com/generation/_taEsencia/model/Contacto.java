package com.generation._taEsencia.model;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "contacto")
public class Contacto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_contacto;

    // Relación opcional: un usuario logueado puede escribir, o un visitante anónimo
    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = true)
    private Usuario usuario;

    @Column(nullable = false, length = 50)
    private String nombre;

    @Column(nullable = false, length = 100)
    private String correo;

    @Column(nullable = false, length = 300)
    private String mensaje;

    private LocalDateTime fecha;

    // --- CONSTRUCTORES ---

    public Contacto(){}

    public Contacto(Long id_contacto, Usuario usuario, String nombre, String correo, String mensaje, LocalDateTime fecha) {
        this.id_contacto = id_contacto;
        this.usuario = usuario;
        this.nombre = nombre;
        this.correo = correo;
        this.mensaje = mensaje;
        this.fecha = fecha;
    }

    // --- GETTERS Y SETTERS ---
    public Long getId_contacto() { return id_contacto; }
    public void setId_contacto(Long id_contacto) { this.id_contacto = id_contacto; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getMensaje() { return mensaje; }
    public void setMensaje(String mensaje) { this.mensaje = mensaje; }

    public LocalDateTime getFecha() { return fecha; }
    public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }
}
