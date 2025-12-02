package mx.tecnm.backend.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.tecnm.backend.api.models.Domicilio;
import mx.tecnm.backend.api.repository.DomicilioDAO;

@RestController
@RequestMapping("/domicilios")
public class DomiciliosController {
    
@Autowired
DomicilioDAO repo;
@GetMapping()
public ResponseEntity<List<Domicilio>> obtenerProductos() {
    // Lógica para obtener la lista de domicilios
 List<Domicilio> domicilios = repo.consultarDomicilios();
 return ResponseEntity.ok(domicilios);
} 
    }
