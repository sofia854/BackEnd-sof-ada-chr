package mx.tecnm.backend.api.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import mx.tecnm.backend.api.models.Usuario;

@Repository
public class UsuarioDAO {

    @Autowired
    JdbcClient conexion;
    

public List<Usuario> consultarUsuarios() {
        String sql = "SELECT id, nombre, email FROM usuarios";
        return conexion.sql(sql)
                .query((rs, rowNum) -> new Usuario(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("email")))
                        .list();
    }

}
