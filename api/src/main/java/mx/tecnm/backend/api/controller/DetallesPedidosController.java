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

import mx.tecnm.backend.api.models.DetallesPedidos;
import mx.tecnm.backend.api.repository.DetallesPedidosDAO;

@RestController
@RequestMapping("/detallespedidos")
public class DetallesPedidosController {

    @Autowired
    private DetallesPedidosDAO detallesPedidosDAO;

    // GET /detallespedidos
    @GetMapping
    public List<DetallesPedidos> getAllDetallesPedidos() {
        return detallesPedidosDAO.findAll();
    }

    // GET /detallespedidos/{id}
    @GetMapping("/{id}")
    public ResponseEntity<DetallesPedidos> getDetallePedidoById(@PathVariable int id) {
        try {
            DetallesPedidos detalle = detallesPedidosDAO.findById(id); 
            return ResponseEntity.ok(detalle);
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // POST /detallespedidos
    @PostMapping
    public ResponseEntity<String> createDetallePedido(@RequestBody DetallesPedidos detalle) {
        int resultado = detallesPedidosDAO.save(detalle);
        if (resultado > 0) {
            return ResponseEntity.ok("Detalle de Pedido registrado con éxito.");
        } else {
            return ResponseEntity.internalServerError().body("Error al registrar el Detalle de Pedido.");
        }
    } 
    
    // PUT /detallespedidos/{id}
    @PutMapping("/{id}")
    public ResponseEntity<String> updateDetallePedido(@PathVariable int id, @RequestBody DetallesPedidos detalle) {
        detalle.setId(id);
        int resultado = detallesPedidosDAO.update(detalle);
        
        if (resultado > 0) {
            return ResponseEntity.ok("Detalle de Pedido actualizado con éxito.");
        } else if (resultado == 0) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.internalServerError().body("Error al actualizar el Detalle de Pedido.");
        }
    }
    
    // DELETE /detallespedidos/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDetallePedido(@PathVariable int id) {
        int resultado = detallesPedidosDAO.delete(id);
        
        if (resultado > 0) {
            return ResponseEntity.ok("Detalle de Pedido eliminado correctamente.");
        } else if (resultado == 0) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.internalServerError().body("Error al eliminar el Detalle de Pedido.");
        }
    }
}