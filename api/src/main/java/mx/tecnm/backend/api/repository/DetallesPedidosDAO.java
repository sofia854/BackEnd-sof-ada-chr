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
        dp.setPrecio_unitario(rs.getBigDecimal("precio")); 
        dp.setProductosId(rs.getInt("productos_id")); 
        dp.setPedidosId(rs.getInt("pedidos_id"));     
        return dp;
    };

    // Consultar todos 
    public List<DetallesPedidos> findAll() {
        String sql = "SELECT id, cantidad, precio, productos_id, pedidos_id FROM detalles_pedido";
        return conexion.query(sql, DetallesPedidosMapper);
    }

    public DetallesPedidos findById(int id) {
        String sql = "SELECT id, cantidad, precio, productos_id, pedidos_id FROM detalles_pedido WHERE id = ?";
        return conexion.queryForObject(sql, DetallesPedidosMapper, id);
    }

    public int save(DetallesPedidos detalle) {
        String sql = "INSERT INTO detalles_pedido (cantidad, precio, productos_id, pedidos_id) VALUES (?, ?, ?, ?)";
        return conexion.update(sql, 
            detalle.getCantidad(), detalle.getPrecio_unitario(), detalle.getProductosId(), detalle.getPedidosId());
    }

    public int update(DetallesPedidos detalle) {
        String sql = "UPDATE detalles_pedido SET cantidad = ?, precio = ?, productos_id = ?, pedidos_id = ? WHERE id = ?";
        return conexion.update(sql, 
            detalle.getCantidad(), detalle.getPrecio_unitario(), detalle.getProductosId(), detalle.getPedidosId(), detalle.getId());
    }


    public int delete(int id) {
        String sql = "DELETE FROM detalles_pedido WHERE id = ?";
        return conexion.update(sql, id);
    }
}