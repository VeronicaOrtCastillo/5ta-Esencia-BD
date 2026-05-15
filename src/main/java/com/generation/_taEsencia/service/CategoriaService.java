package com.generation._taEsencia.service;

import com.generation._taEsencia.model.Categoria;
import com.generation._taEsencia.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Categoria> leerCategorias() {
        return categoriaRepository.findAll();
    }

    public Categoria crearCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    public Optional<Categoria> buscarPorId(Long id_categoria) {
        return categoriaRepository.findById(id_categoria);
    }

    public void borrarCategoria(Long id_categoria) {
        categoriaRepository.deleteById(id_categoria);
    }
}