package com.generation._taEsencia.controller;

import com.generation._taEsencia.model.Usuario;
import com.generation._taEsencia.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    // Obtener todos los usuarios
    @GetMapping
    public List<Usuario> getAll() {
        return usuarioService.leerUsuarios();
    }

    // Registrar un nuevo usuario (Usado para el Registro)
    @PostMapping
    public Usuario save(@RequestBody Usuario usuario) {
        return usuarioService.crearUsuario(usuario);
    }

    // 1. OBTENER POR ID ESPECÍFICO
    @GetMapping("/{id_usuario}")
    public Usuario getById(@PathVariable Long id_usuario) {
        return usuarioService.buscarPorId(id_usuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + id_usuario));
    }

    // 2. ELIMINAR USUARIO
    @DeleteMapping("/{id_usuario}")
    public void delete(@PathVariable Long id_usuario) {
        usuarioService.borrarUsuario(id_usuario);
    }

    // 3. ACTUALIZAR USUARIO (Put)
    @PutMapping("/{id_usuario}")
    public Usuario update(@PathVariable Long id_usuario, @RequestBody Usuario detallesUsuario) {
        return usuarioService.buscarPorId(id_usuario).map(usuario -> {

            usuario.setId_usuario(id_usuario); // Mantenemos el ID correcto

            usuario.setNombre(detallesUsuario.getNombre());
            usuario.setApellido(detallesUsuario.getApellido());
            usuario.setCorreo(detallesUsuario.getCorreo());
            usuario.setTelefono(detallesUsuario.getTelefono());
            usuario.setContrasena(detallesUsuario.getContrasena());
            usuario.setRol(detallesUsuario.getRol());

            return usuarioService.crearUsuario(usuario);
        }).orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + id_usuario));
    }
}