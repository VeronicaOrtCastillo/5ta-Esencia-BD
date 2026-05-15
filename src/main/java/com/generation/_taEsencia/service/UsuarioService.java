package com.generation._taEsencia.service;

import com.generation._taEsencia.dto.RegisterRequest;
import com.generation._taEsencia.model.Rol;
import com.generation._taEsencia.model.Usuario;
import com.generation._taEsencia.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Usuario> leerUsuarios() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> buscarPorId(Long id_usuario) {
        return usuarioRepository.findById(id_usuario);
    }

    public Optional<Usuario> buscarPorCorreo(String correo) {
        return usuarioRepository.findByCorreo(correo);
    }

    public Usuario crearUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Usuario registrarUsuario(RegisterRequest request) {

        if (usuarioRepository.existsByCorreo(request.getCorreo())) {
            throw new RuntimeException("El correo ya está registrado");
        }

        if (usuarioRepository.existsByTelefono(request.getTelefono())) {
            throw new RuntimeException("El teléfono ya está registrado");
        }

        Usuario usuario = new Usuario();

        usuario.setNombre(request.getNombre());
        usuario.setApellido(request.getApellido());
        usuario.setCorreo(request.getCorreo());
        usuario.setTelefono(request.getTelefono());
        usuario.setContrasena(passwordEncoder.encode(request.getContrasena()));
        usuario.setRol(Rol.usuario);
        usuario.setFecha_registro(LocalDateTime.now());

        return usuarioRepository.save(usuario);
    }

    public void borrarUsuario(Long id_usuario) {
        usuarioRepository.deleteById(id_usuario);
    }
}