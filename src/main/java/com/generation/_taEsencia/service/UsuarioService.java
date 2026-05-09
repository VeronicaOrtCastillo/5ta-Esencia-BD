package com.generation._taEsencia.service;

import com.generation._taEsencia.model.Usuario;
import com.generation._taEsencia.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Leer todos los usuarios
    public List<Usuario> leerUsuarios() {
        return usuarioRepository.findAll();
    }

    // Guardar o actualizar un usuario
    public Usuario crearUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    // 1. Buscar por el ID específico  (id_usuario)
    public Optional<Usuario> buscarPorId(Long id_usuario) {
        return usuarioRepository.findById(id_usuario);
    }

    // 2. Borrar usando el ID específico
    public void borrarUsuario(Long id_usuario) {
        usuarioRepository.deleteById(id_usuario);
    }

    // 3. Buscar por correo
    public Optional<Usuario> buscarPorCorreo(String correo) {
        return usuarioRepository.findByCorreo(correo);
    }
}