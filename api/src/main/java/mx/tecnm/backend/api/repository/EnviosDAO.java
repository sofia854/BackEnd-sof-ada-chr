package mx.tecnm.backend.api.repository;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import mx.tecnm.backend.api.models.Envios;

@Repository
public class EnviosDAO {

    @Autowired
    private JdbcTemplate conexion; // Cambiado a JdbcTemplate para coincidir con tu ejemplo

    // RowMapper reutilizable para Envios, siguiendo el estilo de DetallesPedidosDAO
    private final RowMapper<Envios> enviosRowMapper = (rs, rowNum) -> {
        Envios env = new Envios();
        
        // Mapeo directo de columnas de la BD (asumiendo snake_case) a setters del modelo
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
    public List<Envios> consultarEnvios() {
        String sql = "SELECT id, fecha_entrega, fecha, estado, numero_seguimiento, domicilios_id, pedidos_id FROM envios";
        // Usamos .query() con el RowMapper definido
        return conexion.query(sql, enviosRowMapper);
    }

    // 2. READ BY ID (Buscar por ID)
    public Envios buscarPorId(int id) {
        String sql = "SELECT id, fecha_entrega, fecha, estado, numero_seguimiento, domicilios_id, pedidos_id FROM envios WHERE id = ?";
        // Usamos .queryForObject() para una sola fila
        // Si no se encuentra, JdbcTemplate lanzará una excepción, la cual debe ser manejada en el Controller (o aquí con un try/catch)
        try {
            return conexion.queryForObject(sql, enviosRowMapper, id);
        } catch (Exception e) {
            // Retorna null si no se encuentra (similar a lo que hacía JdbcClient.optional().orElse(null))
            return null; 
        }
    }

    // 3. CREATE (Guardar)
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
    public int actualizar(Envios envio) {
        String sql = "UPDATE envios SET fecha_entrega = ?, fecha = ?, estado = ?, numero_seguimiento = ?, domicilios_id = ?, pedidos_id = ? WHERE id = ?";
        return conexion.update(sql, 
            envio.getFecha_entrega(), 
            envio.getFecha(), 
            envio.getEstado(), 
            envio.getNumero_seguimiento(), 
            envio.getDomicilios_id(), 
            envio.getPedidos_id(),
            envio.getId() // El ID es el último parámetro
        );
    }

    // 5. DELETE (Eliminar)
    public int eliminar(int id) {
        String sql = "DELETE FROM envios WHERE id = ?";
        return conexion.update(sql, id);
    }
}