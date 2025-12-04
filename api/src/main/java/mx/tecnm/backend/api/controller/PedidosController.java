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
    public ResponseEntity<List<Pedidos>> getAllPedidos() {
        List<Pedidos> pedidos = pedidosDAO.findAll();
        return pedidos.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(pedidos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedidos> getPedidoById(@PathVariable int id) {
        Pedidos pedido = pedidosDAO.findById(id);
        return pedido != null ? ResponseEntity.ok(pedido) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Pedidos> createPedido(@RequestBody Pedidos pedido) {
        Pedidos creado = pedidosDAO.save(pedido);
        return ResponseEntity.ok(creado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pedidos> updatePedido(@PathVariable int id, @RequestBody Pedidos pedido) {
        Pedidos actualizado = pedidosDAO.update(id, pedido);
        return actualizado != null ? ResponseEntity.ok(actualizado) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePedido(@PathVariable int id) {
        boolean eliminado = pedidosDAO.delete(id);
        return eliminado ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}