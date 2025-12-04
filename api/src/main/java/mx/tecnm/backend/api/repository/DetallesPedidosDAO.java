package mx.tecnm.backend.api.repository;

import java.util.List;
import java.math.BigDecimal; // Importado para mapeo de 'numeric'
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
        
        // Mapeo de columna 'precio' de DB a BigDecimal
        dp.setPrecio_unitario(rs.getBigDecimal("precio")); 
        
        // Mapeo de llaves foráneas
        dp.setProductosId(rs.getInt("productos_id")); 
        dp.setPedidosId(rs.getInt("pedidos_id"));     
        return dp;
    };

    // CRUD: Consultar todos (findAll)
    public List<DetallesPedidos> findAll() {
        String sql = "SELECT id, cantidad, precio, productos_id, pedidos_id FROM detalles_pedido";
        return conexion.query(sql, DetallesPedidosMapper);
    }

    // CRUD: Consultar por ID (findById)
    public DetallesPedidos findById(int id) {
        String sql = "SELECT id, cantidad, precio, productos_id, pedidos_id FROM detalles_pedido WHERE id = ?";
        return conexion.queryForObject(sql, DetallesPedidosMapper, id);
    }

    // CRUD: Registrar (save)
    public int save(DetallesPedidos detalle) {
        String sql = "INSERT INTO detalles_pedido (cantidad, precio, productos_id, pedidos_id) VALUES (?, ?, ?, ?)";
        return conexion.update(sql, 
            detalle.getCantidad(), detalle.getPrecio_unitario(), detalle.getProductosId(), detalle.getPedidosId());
    }

    // CRUD: Actualizar (update)
    public int update(DetallesPedidos detalle) {
        String sql = "UPDATE detalles_pedido SET cantidad = ?, precio = ?, productos_id = ?, pedidos_id = ? WHERE id = ?";
        return conexion.update(sql, 
            detalle.getCantidad(), detalle.getPrecio_unitario(), detalle.getProductosId(), detalle.getPedidosId(), detalle.getId());
    }

    // CRUD: Eliminar (delete)
    public int delete(int id) {
        String sql = "DELETE FROM detalles_pedido WHERE id = ?";
        return conexion.update(sql, id);
    }
}