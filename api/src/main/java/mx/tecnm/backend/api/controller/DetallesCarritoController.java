package mx.tecnm.backend.api.controller;

import mx.tecnm.backend.api.models.DetallesCarrito;
import mx.tecnm.backend.api.repository.DetallesCarritoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/detalles_carrito")
public class DetallesCarritoController {

    @Autowired
    private DetallesCarritoDAO dao;

    @GetMapping
    public ResponseEntity<List<DetallesCarrito>> getAll() {
        List<DetallesCarrito> lista = dao.consultarTodos();
        return lista.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetallesCarrito> getById(@PathVariable int id) {
        try {
            return ResponseEntity.ok(dao.consultarPorId(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody DetallesCarrito d) {
        int filas = dao.registrar(d);
        return filas > 0 ? ResponseEntity.ok("Detalle insertado correctamente")
                : ResponseEntity.badRequest().body("Error al insertar");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable int id, @RequestBody DetallesCarrito d) {
        d.setId(id);
        int filas = dao.actualizar(d);
        return filas > 0 ? ResponseEntity.ok("Actualizado correctamente")
                : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        int filas = dao.eliminar(id);
        return filas > 0 ? ResponseEntity.ok("Eliminado correctamente")
                : ResponseEntity.notFound().build();
    }
}
