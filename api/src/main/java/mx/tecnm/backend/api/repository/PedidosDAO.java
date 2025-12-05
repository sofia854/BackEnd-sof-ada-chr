package mx.tecnm.backend.api.repository;

import mx.tecnm.backend.api.models.Pedidos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public class PedidosDAO {

    @Autowired
    private JdbcTemplate conexion;

    private final RowMapper<Pedidos> pedidosMapper = (rs, rowNum) -> {
        Pedidos p = new Pedidos();
        p.setId(rs.getInt("id"));
        p.setFecha(rs.getObject("fecha", LocalDateTime.class));
        p.setNumero((UUID) rs.getObject("numero"));
        p.setImporteProd(rs.getDouble("importe_productos"));
        p.setImporteEnv(rs.getDouble("importe_envio"));
        p.setUsuariosId(rs.getInt("usuarios_id"));
        p.setMetodosPago(rs.getInt("metodos_pago_id"));
        p.setFechaHoraPago(rs.getObject("fecha_hora_pago", LocalDateTime.class));
        p.setImporteIva(rs.getDouble("importe_iva"));
        p.setTotal(rs.getDouble("total"));
        return p;
    };

    // Consultar todos
    public List<Pedidos> consultarPedidos() {
        String sql = "SELECT * FROM pedidos ORDER BY fecha DESC";
        return conexion.query(sql, pedidosMapper);
    }

    // Consultar por ID
    public Pedidos consultarPedidoPorId(int id) {
        String sql = "SELECT * FROM pedidos WHERE id = ?";
        return conexion.queryForObject(sql, pedidosMapper, id);
    }

    // Registrar pedido
    public int registrarPedido(Pedidos pedido) {
        String sql = """
            INSERT INTO pedidos 
            (fecha, numero, importe_productos, importe_envio, usuarios_id, metodos_pago_id, fecha_hora_pago) 
            VALUES (?, ?, ?, ?, ?, ?, ?)
        """;

        UUID numero = pedido.getNumero() != null ? pedido.getNumero() : UUID.randomUUID();
        pedido.setNumero(numero);

        return conexion.update(sql,
                pedido.getFecha(),
                numero,
                pedido.getImporteProd(),
                pedido.getImporteEnv(),
                pedido.getUsuariosId(),
                pedido.getMetodosPago(),
                pedido.getFechaHoraPago()
        );
    }

    // Actualizar pedido
    public int actualizarPedido(Pedidos pedido) {
        String sql = """
            UPDATE pedidos SET 
                fecha = ?, 
                numero = ?, 
                importe_productos = ?, 
                importe_envio = ?, 
                usuarios_id = ?, 
                metodos_pago_id = ?, 
                fecha_hora_pago = ?
            WHERE id = ?
        """;

        return conexion.update(sql,
                pedido.getFecha(),
                pedido.getNumero(),
                pedido.getImporteProd(),
                pedido.getImporteEnv(),
                pedido.getUsuariosId(),
                pedido.getMetodosPago(),
                pedido.getFechaHoraPago(),
                pedido.getId()
        );
    }

    // Eliminar pedido
    public int eliminarPedido(int id) {
        String sql = "DELETE FROM pedidos WHERE id = ?";
        return conexion.update(sql, id);
    }
}
