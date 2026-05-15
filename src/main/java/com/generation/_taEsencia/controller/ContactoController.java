package com.generation._taEsencia.controller;
import com.generation._taEsencia.model.Contacto;
import com.generation._taEsencia.service.ContactoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contactos") // El endpoint base para esta entidad
@CrossOrigin(origins = "*")
public class ContactoController {

    @Autowired
    private ContactoService contactoService;

    // 1. Obtener todos los mensajes de contacto
    @GetMapping
    public List<Contacto> getAll() {
        return contactoService.leerContactos();
    }

    // 2. Crear un nuevo contacto (Este es el que usaría tu formulario de la web)
    @PostMapping
    public Contacto save(@RequestBody Contacto contacto) {
        return contactoService.crearContacto(contacto);
    }

    // 3. Obtener un mensaje específico por su ID
    @GetMapping("/{id}")
    public Contacto getById(@PathVariable Long id) {
        return contactoService.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Mensaje de contacto no encontrado con id: " + id));
    }

    // 4. Eliminar un mensaje de contacto
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        contactoService.borrarContacto(id);
    }

    // Nota: Normalmente los mensajes de contacto no se "actualizan" (Put),
    // se leen o se borran, pero si lo necesitas, se hace igual que en categorías.
}


