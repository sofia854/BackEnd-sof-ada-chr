package mx.tecnm.backend.api.repository;

import mx.tecnm.backend.api.models.Pedidos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class PedidosDAO {

    @Autowired
    private JdbcTemplate conexion;

    private final RowMapper<Pedidos> mapper = (rs, rowNum) -> {
        Pedidos p = new Pedidos();
        p.setId(rs.getInt("id"));
        p.setFecha(rs.getTimestamp("fecha"));
        p.setNumero((UUID) rs.getObject("numero"));
        p.setImporte_productos(rs.getDouble("importe_productos"));
        p.setImporte_envio(rs.getDouble("importe_envio"));
        p.setUsuarios_id(rs.getInt("usuarios_id"));
        p.setMetodos_pago_id(rs.getInt("metodos_pago_id"));
        p.setFecha_hora_pago(rs.getTimestamp("fecha_hora_pago"));
        p.setImporte_iva(rs.getDouble("importe_iva"));
        p.setTotal(rs.getDouble("total"));
        return p;
    };

    // ================================
    //            CRUD
    // ================================

    public List<Pedidos> findAll() {
        return conexion.query("SELECT * FROM pedidos", mapper);
    }

    public Pedidos findById(int id) {
        return conexion.queryForObject(
                "SELECT * FROM pedidos WHERE id = ?",
                mapper,
                id
        );
    }

    public int save(Pedidos p) {
        return conexion.update("""
            INSERT INTO pedidos
            (importe_productos, importe_envio, usuarios_id, metodos_pago_id, fecha_hora_pago)
            VALUES (?, ?, ?, ?, ?)
        """,
                p.getImporte_productos(),
                p.getImporte_envio(),
                p.getUsuarios_id(),
                p.getMetodos_pago_id(),
                p.getFecha_hora_pago()
        );
    }

    public int update(Pedidos p) {
        return conexion.update("""
            UPDATE pedidos SET 
                importe_productos = ?, 
                importe_envio = ?, 
                usuarios_id = ?, 
                metodos_pago_id = ?, 
                fecha_hora_pago = ?
            WHERE id = ?
        """,
                p.getImporte_productos(),
                p.getImporte_envio(),
                p.getUsuarios_id(),
                p.getMetodos_pago_id(),
                p.getFecha_hora_pago(),
                p.getId()
        );
    }

    public int delete(int id) {
        return conexion.update("DELETE FROM pedidos WHERE id = ?", id);
    }

    // =========================================
    // Actualizar total desde detalles (si usas)
    // =========================================

    public void recalcularTotales(int pedidoId) {
        conexion.update("""
            UPDATE pedidos
            SET importe_productos = (
                SELECT COALESCE(SUM(importe), 0) 
                FROM detalles_pedidos 
                WHERE pedidos_id = ?
            )
            WHERE id = ?
        """, pedidoId, pedidoId);
    }
}
