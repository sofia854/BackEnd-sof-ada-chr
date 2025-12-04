    //git status
    //git add .
    //git status
    //git commit -m "creacion de algo"
    //git pushhhhhhhhhhhhhhhhh


  package mx.tecnm.backend.api.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import mx.tecnm.backend.api.models.Categoria;
import mx.tecnm.backend.api.repository.CategoriaDAO;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    CategoriaDAO categorias;

    // Obtener todas las categorías (READ ALL)
    @GetMapping()
    public ResponseEntity<List<Categoria>> obtenerCategorias() {
        List<Categoria> lista = categorias.consultarCategorias();
        return ResponseEntity.ok(lista);
    }

    // Obtener una categoría por ID (READ ONE)
    @GetMapping("/{id}")
    public ResponseEntity<Categoria> obtenerCategoriaPorId(@PathVariable int id) {
        try {
            Categoria categoria = categorias.consultarCategoriaPorId(id);
            return ResponseEntity.ok(categoria);
        } catch (org.springframework.dao.EmptyResultDataAccessException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Registrar una nueva categoría (CREATE)
    @PostMapping()
    public ResponseEntity<String> registrarCategoria(@RequestBody Categoria categoria) {
        int resultado = categorias.registrarCategoria(categoria);
        if (resultado > 0) {
            return ResponseEntity.ok("Categoría registrada con éxito.");
        } else {
            return ResponseEntity.internalServerError().body("Error al registrar la Categoría.");
        }
    }

    // Actualizar una categoría (UPDATE)
    @PutMapping("/{id}")
    public ResponseEntity<String> actualizarCategoria(@PathVariable int id, @RequestBody Categoria categoria) {
        categoria.setId(id); // Asegurar que el ID del objeto sea el de la URL
        int resultado = categorias.actualizarCategoria(categoria);
        if (resultado > 0) {
            return ResponseEntity.ok("Categoría actualizada con éxito.");
        } else if (resultado == 0) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.internalServerError().body("Error al actualizar la Categoría.");
        }
    }

    // Eliminar una categoría por ID (DELETE)
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarCategoria(@PathVariable int id) {
        int resultado = categorias.eliminarCategoria(id);
        if (resultado > 0) {
            return ResponseEntity.ok("Categoría eliminada con éxito.");
        } else if (resultado == 0) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.internalServerError().body("Error al eliminar la Categoría.");
        }
    }
}