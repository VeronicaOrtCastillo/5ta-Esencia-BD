package com.generation._taEsencia.security;

import com.generation._taEsencia.model.Usuario;
import com.generation._taEsencia.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public CustomUserDetailsService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {

        Usuario usuario = usuarioRepository.findByCorreo(correo)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con correo: " + correo));

        System.out.println("===== USUARIO ENCONTRADO =====");
        System.out.println("Correo: " + usuario.getCorreo());
        System.out.println("Rol en BD: " + usuario.getRol().name());
        System.out.println("Autoridad Spring: ROLE_" + usuario.getRol().name());
        System.out.println("==============================");

        return User.builder()
                .username(usuario.getCorreo())
                .password(usuario.getContrasena())
                .roles(usuario.getRol().name())
                .build();
    }
}