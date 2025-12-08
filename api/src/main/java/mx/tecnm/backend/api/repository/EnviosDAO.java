package mx.tecnm.backend.api.repository;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import mx.tecnm.backend.api.models.Envios;

@Repository
public class EnviosDAO {

    @Autowired
    private JdbcTemplate conexion; // Conexión a la BD

    // RowMapper reutilizable para mapear filas de la BD a objetos Envios
    private final RowMapper<Envios> enviosRowMapper = (rs, rowNum) -> {
        Envios env = new Envios();
        
        // Mapeo directo de columnas (asumiendo snake_case en la BD)
        env.setId(rs.getInt("id"));
        env.setFecha_entrega(rs.getString("fecha_entrega")); 
        env.setFecha(rs.getString("fecha"));
        env.setEstado(rs.getString("estado"));
        env.setNumero_seguimiento(rs.getString("numero_seguimiento"));
        env.setDomicilios_id(rs.getInt("domicilios_id"));
        env.setPedidos_id(rs.getInt("pedidos_id"));
        
        return env;
    };

    // 1. READ ALL (Consultar todos)
    public List<Envios> consultarTodos() {
        String sql = "SELECT id, fecha_entrega, fecha, estado, numero_seguimiento, domicilios_id, pedidos_id FROM envios";
        return conexion.query(sql, enviosRowMapper);
    }

    // 2. READ BY ID (Buscar por ID)
    public Envios buscarPorId(int id) {
        String sql = "SELECT id, fecha_entrega, fecha, estado, numero_seguimiento, domicilios_id, pedidos_id FROM envios WHERE id = ?";
        try {
            return conexion.queryForObject(sql, enviosRowMapper, id);
        } catch (EmptyResultDataAccessException e) {
            // Retorna null si no se encuentra ningún registro con ese ID
            return null; 
        }
    }

    // 3. CREATE (Guardar)
    // Retorna el número de filas afectadas (debería ser 1 si es exitoso)
    public int guardar(Envios envio) {
        String sql = "INSERT INTO envios (fecha_entrega, fecha, estado, numero_seguimiento, domicilios_id, pedidos_id) VALUES (?, ?, ?, ?, ?, ?)";
        return conexion.update(sql, 
            envio.getFecha_entrega(), 
            envio.getFecha(), 
            envio.getEstado(), 
            envio.getNumero_seguimiento(), 
            envio.getDomicilios_id(), 
            envio.getPedidos_id()
        );
    }

    // 4. UPDATE (Actualizar)
    // Retorna el número de filas afectadas (debería ser 1 si es exitoso)
    public int actualizar(Envios envio) {
        // Asegúrate de que el 'id' del objeto Envios esté seteado
        String sql = "UPDATE envios SET fecha_entrega = ?, fecha = ?, estado = ?, numero_seguimiento = ?, domicilios_id = ?, pedidos_id = ? WHERE id = ?";
        return conexion.update(sql, 
            envio.getFecha_entrega(), 
            envio.getFecha(), 
            envio.getEstado(), 
            envio.getNumero_seguimiento(), 
            envio.getDomicilios_id(), 
            envio.getPedidos_id(),
            envio.getId() // Condición WHERE
        );
    }

    // 5. DELETE (Eliminar)
    // Retorna el número de filas afectadas (debería ser 1 si es exitoso)
    public int eliminar(int id) {
        String sql = "DELETE FROM envios WHERE id = ?";
        return conexion.update(sql, id);
    }
}