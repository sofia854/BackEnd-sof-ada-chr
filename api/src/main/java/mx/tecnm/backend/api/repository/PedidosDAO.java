package mx.tecnm.backend.api.repository;
import mx.tecnm.backend.api.models.Pedidos;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;  


@Repository
public class PedidosDAO {
    private list<Pedidos> listaPedidos = new ArrayList<>();
    private int contadorId = 1;

    public List<Pedidos> findAll() {
        return listaPedidos;
    }
    public Pedidos findById(int id) {
       return listaPedidos.stream()
                .filter(pedido -> pedido.getId() == id)
                .findFirst()
                .orElse(null);
    }
    public Pedidos save(Pedidos pedido) {
        pedido.setId(contadorId++);
        listaPedidos.add(pedido);
        return pedido;
    }
    public Pedidos update(int id, Pedidos pedidoActualizado) {
        Pedidos pedidoExistente = findById(id);
        if (pedidoExistente != null) {
            pedidoExistente.setFecha(pedidoActualizado.getFecha());
            pedidoExistente.setNumero(pedidoActualizado.getNumero());
            pedidoExistente.setImporteProd(pedidoActualizado.getImporteProd());
            pedidoExistente.setImporteServ(pedidoActualizado.getImporteServ());
            pedidoExistente.setUsuariosId(pedidoActualizado.getUsuariosId());
            pedidoExistente.setMetodosPago(pedidoActualizado.getMetodosPago());
            pedidoExistente.setFechaHoraPago(pedidoActualizado.getFechaHoraPago());
            pedidoExistente.setImporteEnv(pedidoActualizado.getImporteEnv());
            pedidoExistente.setTotal(pedidoActualizado.getTotal());
        }
        return pedidoExistente;
    }
    public boolean delete(int id) {
        return listaPedidos.removeIf(pedido -> pedido.getId() == id);

}
