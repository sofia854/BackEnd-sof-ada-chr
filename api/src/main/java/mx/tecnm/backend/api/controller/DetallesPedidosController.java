package mx.tecnm.backend.api.controller;

import mx.tecnm.backend.api.models.DetallesPedidos;
import mx.tecnm.backend.api.repository.DetallesPedidosDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/detallespedido")
@CrossOrigin(origins = "*")
public class DetallesPedidosController {

    @Autowired
    private DetallesPedidosDAO dao;

    @GetMapping
    public List<DetallesPedidos> getAll() {
        return dao.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetallesPedidos> getById(@PathVariable int id) {
        DetallesPedidos d = dao.findById(id);
        return d != null ? ResponseEntity.ok(d) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody DetallesPedidos d) {
        dao.save(d);
        return ResponseEntity.ok("Detalle creado.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable int id, @RequestBody DetallesPedidos d) {
        d.setId(id);
        dao.update(d);
        return ResponseEntity.ok("Detalle actualizado.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        dao.delete(id);
        return ResponseEntity.ok("Detalle eliminado.");
    }
}
