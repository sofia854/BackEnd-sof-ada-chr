package mx.tecnm.backend.api.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.tecnm.backend.api.models.Pedidos;
import mx.tecnm.backend.api.repository.PedidosDAO;

@RestController
@RequestMapping("/pedidos")
public class PedidosController {
    @Autowired
    private PedidosDAO pedidosDAO;

    // GET /pedidos
    @GetMapping
    public List<Pedidos> getAllPedidos() {
        return pedidosDAO.findAll();
    }

    // GET /pedidos/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Pedidos> getPedidoById(@PathVariable int id) {
        try {
            Pedidos pedido = pedidosDAO.findById(id); 
            return ResponseEntity.ok(pedido);
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // POST /pedidos
    @PostMapping
    public ResponseEntity<String> createPedido(@RequestBody Pedidos pedido) {
        int resultado = pedidosDAO.save(pedido);
        if (resultado > 0) {
            return ResponseEntity.ok("Pedido registrado con éxito.");
        } else {
            return ResponseEntity.internalServerError().body("Error al registrar el pedido.");
        }
    } 
    
    // PUT /pedidos/{id}
    @PutMapping("/{id}")
    public ResponseEntity<String> updatePedido(@PathVariable int id, @RequestBody Pedidos pedido) {
        pedido.setId(id);
        int resultado = pedidosDAO.update(pedido);
        
        if (resultado > 0) {
            return ResponseEntity.ok("Pedido actualizado con éxito.");
        } else if (resultado == 0) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.internalServerError().body("Error al actualizar el pedido.");
        }
    }
    
    // DELETE /pedidos/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePedido(@PathVariable int id) {
        int resultado = pedidosDAO.delete(id);
        
        if (resultado > 0) {
            return ResponseEntity.ok("Pedido eliminado correctamente.");
        } else if (resultado == 0) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.internalServerError().body("Error al eliminar el pedido.");
        }
    }
}