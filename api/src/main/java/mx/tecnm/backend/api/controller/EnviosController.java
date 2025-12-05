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

import mx.tecnm.backend.api.models.Envios;
import mx.tecnm.backend.api.repository.EnviosDAO;

@RestController
@RequestMapping("/envios")
public class EnviosController {

    @Autowired
    EnviosDAO enviosDAO;

    // 1. READ ALL
    // GET /envios
    @GetMapping()
    public ResponseEntity<List<Envios>> obtenerEnvios() {
        List<Envios> lista = enviosDAO.consultarEnvios();
        return ResponseEntity.ok(lista);
    }

    // 2. READ BY ID
    // GET /envios/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Envios> obtenerEnvioPorId(@PathVariable int id) {
        Envios envio = enviosDAO.buscarPorId(id);
        if (envio != null) {
            return ResponseEntity.ok(envio);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 3. CREATE
    // POST /envios
    @PostMapping
    public ResponseEntity<String> crearEnvio(@RequestBody Envios nuevoEnvio) {
        int resultado = enviosDAO.guardar(nuevoEnvio);
        if (resultado > 0) {
            return ResponseEntity.status(201).body("Envío registrado con éxito.");
        } else {
            return ResponseEntity.internalServerError().body("Error al registrar el envío.");
        }
    }

    // 4. UPDATE
    // PUT /envios/{id}
    @PutMapping("/{id}")
    public ResponseEntity<String> actualizarEnvio(@PathVariable int id, @RequestBody Envios envioActualizado) {
        envioActualizado.setId(id);
        int resultado = enviosDAO.actualizar(envioActualizado);
        
        if (resultado > 0) {
            return ResponseEntity.ok("Envío actualizado con éxito.");
        } else if (resultado == 0) {
            // No se encontró el ID para actualizar
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.internalServerError().body("Error al actualizar el envío.");
        }
    }

    // 5. DELETE
    // DELETE /envios/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarEnvio(@PathVariable int id) {
        int resultado = enviosDAO.eliminar(id);
        
        if (resultado > 0) {
            return ResponseEntity.ok("Envío eliminado correctamente.");
        } else if (resultado == 0) {
            // No se encontró el ID para eliminar
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.internalServerError().body("Error al eliminar el envío.");
        }
    }
}