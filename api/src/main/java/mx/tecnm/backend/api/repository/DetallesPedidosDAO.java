package mx.tecnm.backend.api.repository;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import mx.tecnm.backend.api.models.DetallesPedidos;

@Repository
public class DetallesPedidosDAO {

    @Autowired
    private JdbcTemplate conexion;

    private final RowMapper<DetallesPedidos> DetallesPedidosMapper = (rs, rowNum) -> {
        DetallesPedidos dp = new DetallesPedidos();
        dp.setId(rs.getInt("id"));
        dp.setCantidad(rs.getInt("cantidad"));
        // *** CORRECCIÓN: Usar "precio" del ResultSet ***
        dp.setPrecio_unitario(rs.getDouble("precio")); 
        dp.setProductos_id(rs.getInt("productos_id"));
        dp.setPedidos_id(rs.getInt("pedidos_id"));
        return dp;
    };

    // Consultar todos los detalles de pedidos
    public List<DetallesPedidos> consultarDetallesPedidos() {
        // *** CORRECCIÓN: Usar "precio" en el SELECT ***
        String sql = "SELECT id, cantidad, precio, productos_id, pedidos_id FROM detalles_pedido";
        return conexion.query(sql, DetallesPedidosMapper);
    }

    // Consultar un detalle de pedido por ID
    public DetallesPedidos consultarDetallePedidoPorId(int id) {
        // *** CORRECCIÓN: Usar "precio" en el SELECT ***
        String sql = "SELECT id, cantidad, precio, productos_id, pedidos_id FROM detalles_pedido WHERE id = ?";
        return conexion.queryForObject(sql, DetallesPedidosMapper, id);
    }

    // Registrar un nuevo detalle de pedido (INSERT)
    public int registrarDetallePedido(DetallesPedidos detalle) {
        // *** CORRECCIÓN: Usar "precio" en la columna de INSERT ***
        String sql = "INSERT INTO detalles_pedido (cantidad, precio, productos_id, pedidos_id) VALUES (?, ?, ?, ?)";
        return conexion.update(sql, detalle.getCantidad(), detalle.getPrecio_unitario(), detalle.getProductos_id(), detalle.getPedidos_id());
    }

    // Actualizar un detalle de pedido (UPDATE)
    public int actualizarDetallePedido(DetallesPedidos detalle) {
        // *** CORRECCIÓN: Usar "precio" en el SET ***
        String sql = "UPDATE detalles_pedido SET cantidad = ?, precio = ?, productos_id = ?, pedidos_id = ? WHERE id = ?";
        return conexion.update(sql, detalle.getCantidad(), detalle.getPrecio_unitario(), detalle.getProductos_id(), detalle.getPedidos_id(), detalle.getId());
    }

    // Eliminar un detalle de pedido por ID (DELETE)
    public int eliminarDetallePedido(int id) {
        String sql = "DELETE FROM detalles_pedido WHERE id = ?";
        return conexion.update(sql, id);
    }
}