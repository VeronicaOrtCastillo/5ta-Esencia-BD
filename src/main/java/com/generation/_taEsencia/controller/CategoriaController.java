package com.generation._taEsencia.controller;

import com.generation._taEsencia.model.Categoria;
import com.generation._taEsencia.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
@CrossOrigin(origins = "*")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public List<Categoria> getAll() {
        return categoriaService.leerCategorias();
    }

    @PostMapping
    public Categoria save(@RequestBody Categoria categoria) {
        return categoriaService.crearCategoria(categoria);
    }

    // Obtener una categoría por su ID específico
    @GetMapping("/{id_categoria}")
    public Categoria getById(@PathVariable Long id_categoria) {
        return categoriaService.buscarPorId(id_categoria)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada con id: " + id_categoria));
    }

    // Eliminar una categoría
    @DeleteMapping("/{id_categoria}")
    public void delete(@PathVariable Long id_categoria) {
        categoriaService.borrarCategoria(id_categoria);
    }

    // Actualizar una categoría existente
    @PutMapping("/{id_categoria}")
    public Categoria update(@PathVariable Long id_categoria, @RequestBody Categoria detalles) {
        return categoriaService.buscarPorId(id_categoria).map(categoria -> {
            categoria.setId_categoria(id_categoria); // Aseguramos el ID correcto
            categoria.setNombre(detalles.getNombre());
            return categoriaService.crearCategoria(categoria);
        }).orElseThrow(() -> new RuntimeException("Categoría no encontrada con id: " + id_categoria));
    }
}