package mx.tecnm.backend.api.repository;

import mx.tecnm.backend.api.models.DetallesPedidos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class DetallesPedidosDAO {

    @Autowired
    private JdbcTemplate jdbc;

    // Mapeo de resultados
    private final RowMapper<DetallesPedidos> mapper = new RowMapper<DetallesPedidos>() {
        @Override
        public DetallesPedidos mapRow(ResultSet rs, int rowNum) throws SQLException {
            DetallesPedidos d = new DetallesPedidos();
            d.setId(rs.getInt("id"));
            d.setCantidad(rs.getInt("cantidad"));
            d.setPrecio(rs.getDouble("precio"));
            d.setProductoId(rs.getInt("productos_id"));
            d.setPedidoId(rs.getInt("pedidos_id"));
            return d;
        }
    };

    // Obtener todos
    public List<DetallesPedidos> findAll() {
        return jdbc.query(
                "SELECT id, cantidad, precio, productos_id, pedidos_id FROM detalles_pedido",
                mapper
        );
    }

    // Obtener por ID
    public DetallesPedidos findById(int id) {
        List<DetallesPedidos> result = jdbc.query(
                "SELECT id, cantidad, precio, productos_id, pedidos_id FROM detalles_pedido WHERE id = ?",
                mapper,
                id
        );
        return result.isEmpty() ? null : result.get(0);
    }

    // Crear
    public int save(DetallesPedidos d) {
        int result = jdbc.update(
                "INSERT INTO detalles_pedido (cantidad, precio, productos_id, pedidos_id) VALUES (?, ?, ?, ?)",
                d.getCantidad(), d.getPrecio(), d.getProductoId(), d.getPedidoId()
        );

        // 🔥 Actualiza importe_productos del pedido
        recalcularImporteProductos(d.getPedidoId());

        return result;
    }

    // Actualizar
    public int update(DetallesPedidos d) {
        int result = jdbc.update(
                "UPDATE detalles_pedido SET cantidad = ?, precio = ?, productos_id = ?, pedidos_id = ? WHERE id = ?",
                d.getCantidad(), d.getPrecio(), d.getProductoId(), d.getPedidoId(), d.getId()
        );

        // 🔥 Recalcular después de actualizar
        recalcularImporteProductos(d.getPedidoId());

        return result;
    }

    // Eliminar
    public int delete(int id) {
        // Primero obtener pedidoId para poder recalcular
        Integer pedidoId = jdbc.queryForObject(
                "SELECT pedidos_id FROM detalles_pedido WHERE id = ?",
                Integer.class,
                id
        );

        int result = jdbc.update("DELETE FROM detalles_pedido WHERE id = ?", id);

        // 🔥 Recalcular después de eliminar
        if (pedidoId != null) {
            recalcularImporteProductos(pedidoId);
        }

        return result;
    }

    // 🔥 Método para actualizar el importe de productos de un pedido
    private void recalcularImporteProductos(int pedidoId) {
        String sql = """
            UPDATE pedidos
            SET importe_productos = (
                SELECT COALESCE(SUM(cantidad * precio), 0)
                FROM detalles_pedido
                WHERE pedidos_id = ?
            )
            WHERE id = ?
        """;

        jdbc.update(sql, pedidoId, pedidoId);
    }
}
