package mx.tecnm.backend.api.repository;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import mx.tecnm.backend.api.models.Envios;

@Repository
public class EnviosDAO {

    @Autowired
    JdbcClient conexion;

    public List<Envios> consultarEnvios() {
        String sql = "SELECT id, fecha_entrega, fecha, estado, numero_seguimiento, domicilios_id, pedidos_id FROM envios";

        return conexion.sql(sql)
            .query((rs, rowNum) -> new Envios(
                rs.getInt("id"),
                rs.getString("fecha_entrega"),
                rs.getString("fecha"),
                rs.getString("estado"),
                rs.getString("numero_seguimiento"),
                rs.getInt("domicilios_id"),
                rs.getInt("pedidos_id")
            ))
            .list();
    }
}
