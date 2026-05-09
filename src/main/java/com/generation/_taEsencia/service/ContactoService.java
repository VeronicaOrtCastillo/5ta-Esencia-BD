package com.generation._taEsencia.service;


import com.generation._taEsencia.model.Contacto;
import com.generation._taEsencia.repository.ContactoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactoService {

    @Autowired
    private ContactoRepository contactoRepository;

    // Leer todos los mensajes de contacto (útil para el panel de admin)
    public List<Contacto> leerContactos() {
        return contactoRepository.findAll();
    }

    // Guardar un nuevo mensaje de contacto
    public Contacto crearContacto(Contacto contacto) {
        return contactoRepository.save(contacto);
    }

    // Buscar un mensaje específico por su ID correcto (id_contacto)
    public Optional<Contacto> buscarPorId(Long id_contacto) {
        return contactoRepository.findById(id_contacto);
    }

    // Borrar un mensaje de contacto
    public void borrarContacto(Long id_contacto) {
        contactoRepository.deleteById(id_contacto);
    }
}