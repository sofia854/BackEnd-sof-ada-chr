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
//Obtener por ID 
@GetMapping("/{id}")
public ResponseEntity<Productos> obtenerProductoPorId(@PathVariable int id) {
    Productos producto = repo.consultarProductoPorId(id);
if (producto == null) {
        return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok(producto);

}


//Crear producto
@PostMapping
public ResponseEntity<String> agregarProducto(@RequestBody Productos producto) {    

    int filas = repo.insertarProducto(producto);

    if (filas == 1) {
        return ResponseEntity.ok("Producto agregado correctamente :)");
        
    } else {
        return ResponseEntity.badRequest().body("No se pudo agregar el producto :(");
    }


}
    //Modificar producto
   @PutMapping("/{id}")
public ResponseEntity<String> modificarProducto(@PathVariable int id, @RequestBody Productos producto) {

    Productos productoConId = new Productos(
        id,
        producto.nombre(),
        producto.precio(),
        producto.sku(),
        producto.color(),
        producto.marca(),
        producto.descripcion(),
        producto.peso(),
        producto.alto(),
        producto.ancho(),
        producto.profundidad(),
        producto.categorias_id()
    );

    int filas = repo.modificarProducto(productoConId);

    if (filas == 1) {
        return ResponseEntity.ok("Producto modificado correctamente :)");
    } else {
        return ResponseEntity.badRequest().body("No se pudo modificar el producto :(");
    }
}

//Eliminar producto
@DeleteMapping("/{id}")
public ResponseEntity<String> eliminarProducto(@PathVariable int id) {
    int filas = repo.eliminarProducto(id);

    if (filas == 1) {
        return ResponseEntity.ok("Producto eliminado correctamente :)");
    } else {
        return ResponseEntity.status(404).body("Producto no encontrado :(");
    }


}
}
        