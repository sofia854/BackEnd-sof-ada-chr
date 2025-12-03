package mx.tecnm.backend.api.repository;

import mx.tecnm.backend.api.models.Pedidos;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PedidosDAO {

    private List<Pedidos> listaPedidos = new ArrayList<>();
    private int contadorId = 1;

    public List<Pedidos> findAll() {
        return listaPedidos;
    }

    public Pedidos findById(int id) {
        return listaPedidos.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public Pedidos save(Pedidos pedido) {
        pedido.setId(contadorId++);
        listaPedidos.add(pedido);
        return pedido;
    }

    public Pedidos update(int id, Pedidos pedidoActualizado) {
        Pedidos existente = findById(id);

        if (existente != null) {
            existente.setFecha(pedidoActualizado.getFecha());
            existente.setNumero(pedidoActualizado.getNumero());
            existente.setImporteProd(pedidoActualizado.getImporteProd());
            existente.setImporteServ(pedidoActualizado.getImporteServ());
            existente.setUsuariosId(pedidoActualizado.getUsuariosId());
            existente.setMetodosPago(pedidoActualizado.getMetodosPago());
            existente.setFechaHoraPago(pedidoActualizado.getFechaHoraPago());
            existente.setImporteEnv(pedidoActualizado.getImporteEnv());
            existente.setTotal(pedidoActualizado.getTotal());
        }

        return existente;
    }

    public boolean delete(int id) {
        return listaPedidos.removeIf(p -> p.getId() == id);
    }
}
