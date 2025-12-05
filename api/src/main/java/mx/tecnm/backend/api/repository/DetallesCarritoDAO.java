package mx.tecnm.backend.api.repository;

import mx.tecnm.backend.api.models.DetallesCarrito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DetallesCarritoDAO {

    @Autowired
    private JdbcTemplate conexion;

    private final RowMapper<DetallesCarrito> mapper = (rs, rowNum) -> {
        DetallesCarrito d = new DetallesCarrito();
        d.setId(rs.getInt("id"));
        d.setCantidad(rs.getInt("cantidad"));
        d.setProductosId(rs.getInt("productos_id"));
        d.setUsuariosId(rs.getInt("usuarios_id"));
        return d;
    };

    // GET ALL
    public List<DetallesCarrito> consultarTodos() {
        String sql = "SELECT * FROM detalles_carrito ORDER BY id DESC";
        return conexion.query(sql, mapper);
    }

    // GET BY ID
    public DetallesCarrito consultarPorId(int id) {
        String sql = "SELECT * FROM detalles_carrito WHERE id = ?";
        return conexion.queryForObject(sql, mapper, id);
    }

    // POST
    public int registrar(DetallesCarrito d) {
        String sql = """
            INSERT INTO detalles_carrito (cantidad, productos_id, usuarios_id)
            VALUES (?, ?, ?)
        """;
        return conexion.update(sql,
                d.getCantidad(),
                d.getProductosId(),
                d.getUsuariosId()
        );
    }

    // PUT
    public int actualizar(DetallesCarrito d) {
        String sql = """
            UPDATE detalles_carrito 
            SET cantidad = ?, productos_id = ?, usuarios_id = ?
            WHERE id = ?
        """;
        return conexion.update(sql,
                d.getCantidad(),
                d.getProductosId(),
                d.getUsuariosId(),
                d.getId()
        );
    }

    // DELETE
    public int eliminar(int id) {
        String sql = "DELETE FROM detalles_carrito WHERE id = ?";
        return conexion.update(sql, id);
    }
}
