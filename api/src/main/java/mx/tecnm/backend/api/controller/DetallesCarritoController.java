package mx.tecnm.backend.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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

}
