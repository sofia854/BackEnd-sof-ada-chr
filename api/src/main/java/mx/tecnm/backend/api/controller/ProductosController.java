package mx.tecnm.backend.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.tecnm.backend.api.models.Productos;
import mx.tecnm.backend.api.repository.ProductosDAO;

@RestController
@RequestMapping("/productos")
public class ProductosController {
    
    @Autowired
    ProductosDAO repo;
@GetMapping()
public ResponseEntity<List<Productos>> obtenerProductos() {
    // Lógica para obtener la lista de productos
 List<Productos> productos = repo.consultarProductos();
 return ResponseEntity.ok(productos);

}
}

        