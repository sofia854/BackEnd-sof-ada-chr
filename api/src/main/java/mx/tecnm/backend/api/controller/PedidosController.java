package mx.tecnm.backend.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import mx.tecnm.backend.api.repository.PedidosDAO;
import mx.tecnm.backend.api.models.Pedidos;

@RestController
@RequestMapping("/pedidos")
public class PedidosController {

    @Autowired
    private PedidosDAO pedidosDAO;

    @GetMapping
    public List<Pedidos> getAllPedidos() {
        return pedidosDAO.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedidos> getPedidoById(@PathVariable int id) {
        Pedidos pedido = pedidosDAO.findById(id);
        return pedido != null ? ResponseEntity.ok(pedido) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public Pedidos createPedido(@RequestBody Pedidos pedido) {
        return pedidosDAO.save(pedido);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pedidos> updatePedido(@PathVariable int id, @RequestBody Pedidos pedido) {
        Pedidos actualizado = pedidosDAO.update(id, pedido);
        return actualizado != null ? ResponseEntity.ok(actualizado) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePedido(@PathVariable int id) {
        boolean deleted = pedidosDAO.delete(id);

        if (deleted) {
            return ResponseEntity.ok("Pedido eliminado correctamente.");
        } else {
            return ResponseEntity.status(404).body("Pedido no encontrado.");
        }
    }
}
