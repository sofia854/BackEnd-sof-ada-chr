package mx.tecnm.backend.api.repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;
import java.math.BigDecimal; // Importado para mapeo de 'numeric'

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import mx.tecnm.backend.api.models.Pedidos;

@Repository
public class PedidosDAO {

    @Autowired
    private JdbcTemplate conexion;

    private final RowMapper<Pedidos> PedidosMapper = (rs, rowNum) -> {
        Pedidos p = new Pedidos();
        p.setId(rs.getInt("id"));
        p.setFecha(rs.getTimestamp("fecha"));
        p.setNumero(UUID.fromString(rs.getString("numero")));
        
        // Mapeo a BigDecimal para campos 'numeric'
        p.setImporteProd(rs.getBigDecimal("importe_producto")); // Columna: importe_producto
        p.setImporteServ(rs.getBigDecimal("importe_servicio")); // Columna: importe_servicio
        
        // Mapeo de llaves foráneas
        p.setUsuariosId(rs.getInt("usuario_id")); // Columna: usuario_id
        p.setMetodosPago(rs.getInt("metodos_pago")); // Columna: metodos_pago
        
        p.setFechaHoraPago(rs.getTimestamp("fecha_hora_pago"));
        
        // Mapeo a BigDecimal para campos 'numeric'
        p.setImporteEnv(rs.getBigDecimal("importe_env"));
        p.setTotal(rs.getBigDecimal("total"));
        return p;
    };

    // CRUD: Consultar todos los pedidos (findAll)
    public List<Pedidos> findAll() {
        String sql = "SELECT id, fecha, numero, importe_producto, importe_servicio, usuario_id, metodos_pago, fecha_hora_pago, importe_env, total FROM pedidos";
        return conexion.query(sql, PedidosMapper);
    }

    // CRUD: Consultar un pedido por ID (findById)
    public Pedidos findById(int id) {
        String sql = "SELECT id, fecha, numero, importe_producto, importe_servicio, usuario_id, metodos_pago, fecha_hora_pago, importe_env, total FROM pedidos WHERE id = ?";
        return conexion.queryForObject(sql, PedidosMapper, id);
    }

    // CRUD: Registrar un nuevo pedido (save)
    public int save(Pedidos pedido) {
        String sql = "INSERT INTO pedidos (fecha, numero, importe_producto, importe_servicio, usuario_id, metodos_pago, fecha_hora_pago, importe_env, total) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return conexion.update(sql, 
            pedido.getFecha(), pedido.getNumero().toString(), pedido.getImporteProd(), 
            pedido.getImporteServ(), pedido.getUsuariosId(), pedido.getMetodosPago(), 
            pedido.getFechaHoraPago(), pedido.getImporteEnv(), pedido.getTotal());
    }

    // CRUD: Actualizar un pedido (update)
    public int update(Pedidos pedido) {
        String sql = "UPDATE pedidos SET fecha = ?, numero = ?, importe_producto = ?, importe_servicio = ?, usuario_id = ?, metodos_pago = ?, fecha_hora_pago = ?, importe_env = ?, total = ? WHERE id = ?";
        return conexion.update(sql, 
            pedido.getFecha(), pedido.getNumero().toString(), pedido.getImporteProd(), 
            pedido.getImporteServ(), pedido.getUsuariosId(), pedido.getMetodosPago(), 
            pedido.getFechaHoraPago(), pedido.getImporteEnv(), pedido.getTotal(), pedido.getId());
    }

    // CRUD: Eliminar un pedido (delete)
    public int delete(int id) {
        String sql = "DELETE FROM pedidos WHERE id = ?";
        return conexion.update(sql, id);
    }
}