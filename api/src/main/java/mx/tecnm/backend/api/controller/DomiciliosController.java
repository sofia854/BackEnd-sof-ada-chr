package mx.tecnm.backend.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.convert.Delimiter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.tecnm.backend.api.models.Domicilio;
import mx.tecnm.backend.api.repository.DomicilioDAO;

@RestController
@RequestMapping("/domicilios")
public class DomiciliosController {
    
@Autowired
DomicilioDAO repo;

//Obtener TODOS los domicilios
@GetMapping()
public ResponseEntity<List<Domicilio>> obtenerProductos() {
    // Lógica para obtener la lista de domicilios
 List<Domicilio> domicilios = repo.consultarDomicilios();
 return ResponseEntity.ok(domicilios);
} 
//Obtener domicilio por ID
@GetMapping("/{id}")
public ResponseEntity<Domicilio> obtenerDomicilioPorId(@PathVariable int id) {
Domicilio domicilio = repo.consultarDomicilioPorId(id);
if (domicilio == null) {
    return ResponseEntity.notFound().build();

}
return ResponseEntity.ok(domicilio);
    }

    //Crear Domicilio
    @PostMapping
    public ResponseEntity<String> agregarDomicilio(@RequestBody Domicilio domicilio) {

        int filas = repo.insertarDomicilio(domicilio);

        if (filas == 1) {
            return ResponseEntity.ok("Domicilio agregado correctamente :)");
            
        } else {
            return ResponseEntity.badRequest().body("No se pudo agregar el domicilio :(");
        }
    }

//Modificar Domicilio
@PutMapping("/{id}")
public ResponseEntity<String> modificarDomicilio(@PathVariable int id, @RequestBody Domicilio domicilio) {
    int filas = repo.modificarDomicilio(domicilio, id);
    if (filas == 1) {
        return ResponseEntity.ok("Domicilio modificado correctamente :)");
    } else {
        return ResponseEntity.badRequest().body("No se pudo modificar el domicilio :(");
    }
}


//Eliminar Domicilio

@DeleteMapping("/{id}")
public ResponseEntity<String> eliminarDomicilio(@PathVariable int id) {
    int filas = repo.eliminarDomicilio(id);
    if (filas == 1) {
        return ResponseEntity.ok("Domicilio eliminado correctamente :)");
    } else {
        return ResponseEntity.badRequest().body("No se pudo eliminar el domicilio :(");
    }

}
}