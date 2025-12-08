package mx.tecnm.backend.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.tecnm.backend.api.models.Envios;
import mx.tecnm.backend.api.repository.EnviosDAO; // Importar el DAO correcto

@RestController
@RequestMapping("/envios") // Endpoint base para Envios
public class EnviosController {
    
    @Autowired
    EnviosDAO repo; // Inyecta el DAO de Envios

    // 1. GET - Consultar TODOS (READ ALL)
    // URL: GET http://localhost:8080/envios
    @GetMapping()
    public ResponseEntity<List<Envios>> obtenerTodos() {
        List<Envios> envios = repo.consultarTodos();
        if (envios.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204 No Content si está vacío
        }
        return ResponseEntity.ok(envios); // 200 OK con la lista
    }

    // 2. GET - Consultar por ID (READ BY ID)
    // URL: GET http://localhost:8080/envios/1
    @GetMapping("/{id}")
    public ResponseEntity<Envios> obtenerPorId(@PathVariable int id) {
        Envios envio = repo.buscarPorId(id);
        if (envio == null) {
            return ResponseEntity.notFound().build(); // 404 Not Found si no existe

        }
        return ResponseEntity.ok(envio); // 200 OK con el objeto
    }

    // 3. POST - Crear nuevo (CREATE)
    // URL: POST http://localhost:8080/envios (Body JSON con datos del envío, sin 'id')
    @PostMapping
    public ResponseEntity<String> crearEnvio(@RequestBody Envios envio) {
        // El 'id' se genera en la BD, se insertan los demás campos
        int filas = repo.guardar(envio);

        if (filas == 1) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Envío creado correctamente :)"); // 201 Created
        } else {
            return ResponseEntity.badRequest().body("No se pudo crear el envío :("); // 400 Bad Request
        }
    }

    // 4. PUT - Modificar existente (UPDATE)
    // URL: PUT http://localhost:8080/envios/1 (Body JSON con datos del envío, incluyendo 'id' o usando @PathVariable)
    @PutMapping("/{id}")
    public ResponseEntity<String> modificarEnvio(@PathVariable int id, @RequestBody Envios envio) {
        envio.setId(id); // Asegura que el ID del objeto coincida con el path variable
        
        // Primero verifica si existe
        if (repo.buscarPorId(id) == null) {
             return ResponseEntity.notFound().build(); // 404 Not Found si no existe
        }
        
        int filas = repo.actualizar(envio);
        
        if (filas == 1) {
            return ResponseEntity.ok("Envío modificado correctamente :)"); // 200 OK
        } else {
            // Este caso es poco probable si ya verificamos la existencia, pero lo dejamos como fallback
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al modificar el envío :("); // 500
        }
    }


    // 5. DELETE - Eliminar por ID (DELETE)
    // URL: DELETE http://localhost:8080/envios/1
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarEnvio(@PathVariable int id) {
        int filas = repo.eliminar(id);
        
        if (filas == 1) {
            return ResponseEntity.ok("Envío eliminado correctamente :)"); // 200 OK
        } else {
            // Si filas es 0, significa que el registro no existía
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
    }
}