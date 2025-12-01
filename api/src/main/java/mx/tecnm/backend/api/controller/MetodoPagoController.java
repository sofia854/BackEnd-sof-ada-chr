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

    @GetMapping()   
    public ResponseEntity<List<MetodoPago>> obtenerMetodoPago() {
     List<MetodoPago> metodopago = metodo.consultarMetodoPago();
     return ResponseEntity.ok(metodopago);

    }


}
