package com.generation._taEsencia.controller;

import com.generation._taEsencia.dto.AuthResponse;
import com.generation._taEsencia.dto.LoginRequest;
import com.generation._taEsencia.dto.RegisterRequest;
import com.generation._taEsencia.model.Usuario;
import com.generation._taEsencia.security.JwtService;
import com.generation._taEsencia.service.UsuarioService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final UsuarioService usuarioService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthController(UsuarioService usuarioService,
                          AuthenticationManager authenticationManager,
                          JwtService jwtService) {
        this.usuarioService = usuarioService;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @PostMapping("/register")
    public Usuario register(@RequestBody RegisterRequest request) {
        return usuarioService.registrarUsuario(request);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getCorreo(),
                        request.getContrasena()
                )
        );

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        String token = jwtService.generateToken(userDetails);

        Usuario usuario = usuarioService.buscarPorCorreo(request.getCorreo())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        return new AuthResponse(
                token,
                "Bearer",
                jwtService.getExpirationTimeMs(),
                usuario.getCorreo(),
                usuario.getRol().name()
        );
    }
}