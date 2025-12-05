package mx.tecnm.backend.api.controller;

import mx.tecnm.backend.api.models.Pedidos;
import mx.tecnm.backend.api.repository.PedidosDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
@CrossOrigin("*")
public class PedidosController {

    @Autowired
    private PedidosDAO pedidosDAO;

    @GetMapping
    public List<Pedidos> getAll() {
        return pedidosDAO.consultarPedidos();
    }

    @GetMapping("/{id}")
    public Pedidos getById(@PathVariable int id) {
        return pedidosDAO.consultarPedidoPorId(id);
    }


    @PostMapping
    public String registrar(@RequestBody Pedidos pedido) {
        int result = pedidosDAO.registrarPedido(pedido);
        return result > 0 ? "Pedido registrado correctamente" : "Error al registrar";
    }

    @PutMapping("/{id}")
    public String actualizar(@PathVariable int id, @RequestBody Pedidos pedido) {
        pedido.setId(id);
        int result = pedidosDAO.actualizarPedido(pedido);
        return result > 0 ? "Pedido actualizado correctamente" : "Error al actualizar";
    }

    
    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable int id) {
        int result = pedidosDAO.eliminarPedido(id);
        return result > 0 ? "Pedido eliminado correctamente" : "Error al eliminar";
    }
}
