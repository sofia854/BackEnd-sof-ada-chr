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
import mx.tecnm.backend.api.models.DetallesPedidos;
import mx.tecnm.backend.api.repository.DetallesPedidosDAO;

@RestController
@RequestMapping("/detallespedidos")
public class DetallesPedidosController {

    @Autowired
    DetallesPedidosDAO detallesPedidos;

    // Obtener todos los detalles de pedidos
    @GetMapping()
    public ResponseEntity<List<DetallesPedidos>> obtenerDetallesPedidos() {
        List<DetallesPedidos> lista = detallesPedidos.consultarDetallesPedidos();
        return ResponseEntity.ok(lista);
    }

    // Obtener un detalle de pedido por ID
    @GetMapping("/{id}")
    public ResponseEntity<DetallesPedidos> obtenerDetallePedidoPorId(@PathVariable int id) {
        try {
            DetallesPedidos detalle = detallesPedidos.consultarDetallePedidoPorId(id);
            return ResponseEntity.ok(detalle);
        } catch (org.springframework.dao.EmptyResultDataAccessException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Registrar un nuevo detalle de pedido
    @PostMapping()
    public ResponseEntity<String> registrarDetallePedido(@RequestBody DetallesPedidos detalle) {
        int resultado = detallesPedidos.registrarDetallePedido(detalle);
        if (resultado > 0) {
            return ResponseEntity.ok("Detalle de Pedido registrado con éxito.");
        } else {
            return ResponseEntity.internalServerError().body("Error al registrar el Detalle de Pedido.");
        }
    }

    // Actualizar un detalle de pedido (CRUD)
    @PutMapping("/{id}")
    public ResponseEntity<String> actualizarDetallePedido(@PathVariable int id, @RequestBody DetallesPedidos detalle) {
        detalle.setId(id); // Asegurar que el ID del objeto sea el de la URL
        int resultado = detallesPedidos.actualizarDetallePedido(detalle);
        if (resultado > 0) {
            return ResponseEntity.ok("Detalle de Pedido actualizado con éxito.");
        } else if (resultado == 0) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.internalServerError().body("Error al actualizar el Detalle de Pedido.");
        }
    }

    // Eliminar un detalle de pedido por ID (CRUD)
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarDetallePedido(@PathVariable int id) {
        int resultado = detallesPedidos.eliminarDetallePedido(id);
        if (resultado > 0) {
            return ResponseEntity.ok("Detalle de Pedido eliminado con éxito.");
        } else if (resultado == 0) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.internalServerError().body("Error al eliminar el Detalle de Pedido.");
        }
    }
}