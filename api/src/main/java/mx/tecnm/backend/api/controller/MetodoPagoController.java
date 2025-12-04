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
    MetodoPagoDAO metodo; // La instancia inyectada se llama 'metodo'

    // Obtener todos los métodos de pago (READ ALL)
    // Usando el formato sugerido en el código comentado
    @GetMapping()
    public ResponseEntity<List<MetodoPago>> obtenerMetodoPago() {
       // Asumimos que MetodoPagoDAO tiene el método consultarMetodoPago()
       List<MetodoPago> metodopago = metodo.consultarMetodoPago();
       return ResponseEntity.ok(metodopago);
    }

    /*
     * Nota: Los métodos buscarPorId, crear, actualizar y eliminar de abajo 
     * fueron eliminados o comentados ya que hacían referencia a una variable 'dao' 
     * que no existe y a métodos CRUD que no se sabe si existen en MetodoPagoDAO.
     * Si deseas implementarlos, el código base es similar al de PedidosController, 
     * usando la variable 'metodo'.
     */
}