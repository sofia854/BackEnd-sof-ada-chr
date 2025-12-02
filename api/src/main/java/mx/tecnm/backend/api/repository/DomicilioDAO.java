package mx.tecnm.backend.api.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import mx.tecnm.backend.api.models.Domicilio;

@Repository
public class DomicilioDAO {

    @Autowired
    JdbcClient conexion;

    public List<Domicilio> consultarDomicilios() {
        String sql = "SELECT id, calle, numero, colonia, cp, estado, ciudad, usuarios_id FROM domicilios";
        return conexion.sql(sql)
        .query((rs, rowNum) -> new Domicilio(
                rs.getInt("id"),
                rs.getString("calle"),
                rs.getInt("numero"),
                rs.getString("colonia"),
                rs.getString("cp"),
                rs.getString("estado"),
                rs.getString("ciudad"),
                rs.getInt("usuarios_id")
        ))
        .list();
    }
    
}
