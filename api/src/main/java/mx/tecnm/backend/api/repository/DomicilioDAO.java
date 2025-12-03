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
    
//Obtener por id 
public Domicilio consultarDomicilioPorId(int id) {
    String sql = "SELECT id, calle, numero, colonia, cp, estado, ciudad, usuarios_id FROM domicilios WHERE id = ?";

    return conexion.sql(sql)
            .params(id)
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
            .optional()
            .orElse(null);

        }

        //Insertar domicilio
        public int insertarDomicilio(Domicilio domicilio) {
            String sql = """
                INSERT INTO domicilios(calle, numero, colonia, cp, estado, ciudad, usuarios_id)
                VALUES (?, ?, ?, ?, ?, ?, ?)
                """;
            return conexion.sql(sql)
                .params(
                    domicilio.calle(),
                    domicilio.numero(),
                    domicilio.colonia(),
                    domicilio.cp(),
                    domicilio.estado(),
                    domicilio.ciudad(),
                    domicilio.usuarios_id()
                )
                .update();  
            }

//Modificar domicilio
public int modificarDomicilio( Domicilio domicilio, int id) {
    String sql = """
        UPDATE domicilios
        SET calle = ?, numero = ?, colonia = ?, cp = ?, estado = ?, ciudad = ?, usuarios_id = ?
        WHERE id = ?
        """;

    return conexion.sql(sql)
        .params(
            domicilio.calle(),
            domicilio.numero(),
            domicilio.colonia(),
            domicilio.cp(),
            domicilio.estado(),
            domicilio.ciudad(),
            domicilio.usuarios_id(),
            id
        )
        .update();
    }

    //Eliminar domicilio
    public int eliminarDomicilio(int id) {
        String sql = "DELETE FROM domicilios WHERE id = ?";
        return conexion.sql(sql)
            .params(id)
            .update();
    }

}
