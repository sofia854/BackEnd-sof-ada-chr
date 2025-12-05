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

import mx.tecnm.backend.api.models.DetallesCarrito;
import mx.tecnm.backend.api.repository.DetallesCarritoDAO;

@RestController
@RequestMapping("/detalles_carrito")
public class DetallesCarritoController {
    @Autowired
    DetallesCarritoDAO detalles;

    @GetMapping()   
    public ResponseEntity<List<DetallesCarrito>> obtenerDetallesCarrito() {
     List<DetallesCarrito> detallescarrito = detalles.consultarDetallesCarrito();
     return ResponseEntity.ok(detallescarrito); 
    }

    @GetMapping("/{id}") 
    public ResponseEntity<DetallesCarrito> obtenerDetalleCarritoPorId( @PathVariable int id) {
    DetallesCarrito detallecarrito = detalles.consultarDetalleCarritoPorId(id);
    if (detallecarrito == null) {
        return ResponseEntity.notFound().build();   }
    return ResponseEntity.ok(detallecarrito);
        }       
    @PostMapping
    public ResponseEntity<String> agregarDetalleCarrito(@RequestBody DetallesCarrito detallecarrito) {

        // Lógica para agregar el detalle al carrito (a implementar)
        return ResponseEntity.ok("Detalle de carrito agregado correctamente :)");
    }   
    @PutMapping("/{id}")
    public ResponseEntity<String> modificarDetalleCarrito(@PathVariable int id, @RequestBody DetallesCarrito detallecarrito) {
        detallecarrito.setId(id); // Asegurar que el ID del objeto sea el de la URL
        int filas = detalles.actualizarDetalleCarrito(detallecarrito);  
        if (filas == 1) {
            return ResponseEntity.ok("Detalle de carrito modificado correctamente :)");
        } else {
            return ResponseEntity.badRequest().body("No se pudo modificar el detalle de carrito :(");

        }  

    }
@DeleteMapping("/{id}")
public ResponseEntity<String> eliminarDetalleCarrito(@PathVariable int id) {  
    int filas = detalles.eliminarDetalleCarrito(id);  
    if (filas == 1) {
        return ResponseEntity.ok("Detalle de carrito eliminado correctamente :)");
    } else {
        return ResponseEntity.badRequest().body("No se pudo eliminar el detalle de carrito :(");
    }                  
}
    }
