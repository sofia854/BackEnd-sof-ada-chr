package mx.tecnm.backend.api.controller;

import mx.tecnm.backend.api.models.Pedidos;
import mx.tecnm.backend.api.repository.PedidosDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
@CrossOrigin(origins = "*")
public class PedidosController {

    @Autowired
    private PedidosDAO pedidosDAO;

    // ===========================
    //       OBTENER TODOS
    // ===========================
    @GetMapping
    public ResponseEntity<List<Pedidos>> getAll() {
        return ResponseEntity.ok(pedidosDAO.findAll());
    }

    // ===========================
    //     OBTENER POR ID
    // ===========================
    @GetMapping("/{id}")
    public ResponseEntity<Pedidos> getById(@PathVariable int id) {
        try {
            return ResponseEntity.ok(pedidosDAO.findById(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    // ===========================
    //          CREAR
    // ===========================
    @PostMapping
    public ResponseEntity<String> create(@RequestBody Pedidos pedido) {

        int result = pedidosDAO.save(pedido);

        return (result > 0)
                ? ResponseEntity.ok("Pedido registrado con éxito.")
                : ResponseEntity.internalServerError().body("Error al registrar el pedido.");
    }

    // ===========================
    //        ACTUALIZAR
    // ===========================
    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable int id, @RequestBody Pedidos pedido) {

        pedido.setId(id);

        int result = pedidosDAO.update(pedido);

        return (result > 0)
                ? ResponseEntity.ok("Pedido actualizado correctamente.")
                : ResponseEntity.internalServerError().body("Error al actualizar.");
    }

    // ===========================
    //         ELIMINAR
    // ===========================
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {

        int result = pedidosDAO.delete(id);

        return (result > 0)
                ? ResponseEntity.ok("Pedido eliminado.")
                : ResponseEntity.notFound().build();
    }
}
