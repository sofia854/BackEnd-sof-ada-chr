package mx.tecnm.backend.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.tecnm.backend.api.repository.MetodoPagoDAO;
import mx.tecnm.backend.api.models.MetodoPago;
@RestController
@RequestMapping("/metodos_pago")
public class MetodoPagoController {

    @Autowired
   MetodoPagoDAO metodo;
/* 
    @GetMapping()   
    public ResponseEntity<List<MetodoPago>> obtenerMetodoPago() {
     List<MetodoPago> metodopago = metodo.consultarMetodoPago();
     return ResponseEntity.ok(metodopago);

    }*/

@GetMapping
    public List<MetodoPago> consultar() {
        return dao.consultar();
    }

    @GetMapping("/{id}")
    public MetodoPago buscarPorId(@PathVariable int id) {
        return dao.buscarPorId(id);
    }

    @PostMapping
    public String crear(@RequestBody MetodoPago metodo) {
        return dao.crear(metodo) == 1 ? "Método creado" : "Error";
    }

    @PutMapping("/{id}")
    public String actualizar(@PathVariable int id, @RequestBody MetodoPago metodo) {
        metodo.setId(id);
        return dao.actualizar(metodo) == 1 ? "Actualizado" : "Error";
    }

    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable int id) {
        return dao.eliminar(id) == 1 ? "Eliminado" : "Error";
    }
}

