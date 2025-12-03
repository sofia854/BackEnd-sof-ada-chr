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

import mx.tecnm.backend.api.repository.MetodoPagoDAO;
import mx.tecnm.backend.api.models.MetodoPago;
@RestController
@RequestMapping("/metodos_pago")
public class MetodoPagoController {

    @Autowired
   MetodoPagoDAO metodo;

@GetMapping
    public List<MetodoPago> consultar() {
        return metodo.consultarMetodoPago();
    }

    @GetMapping("/{id}")
    public MetodoPago buscarPorId(@PathVariable int id) {
        return metodo.buscarPorId(id);
    }

    @PostMapping
    public String crear(@RequestBody MetodoPago nuevoMetodo) {
        return metodo.crear(nuevoMetodo) == 1 ? "Creado" : "Error";
    }

    @PutMapping("/{id}")
    public String actualizar(@PathVariable int id, @RequestBody MetodoPago metodo) {
        metodo.setId(id);
        return this.metodo.actualizar(metodo) == 1 ? "Actualizado" : "Error";
    }

    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable int id) {
        return metodo.eliminar(id) == 1 ? "Eliminado" : "Error";
    }
}

