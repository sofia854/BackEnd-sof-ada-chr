package mx.tecnm.backend.api.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import mx.tecnm.backend.api.models.MetodoPago;

@Repository
public class MetodoPagoDAO {

    @Autowired
    JdbcClient conexion;

    public List<MetodoPago> consultarMetodoPago() {
        String sql = "SELECT id, nombre, comision FROM metodos_pago" ;     
           return conexion.sql(sql)
                .query((rs, rowNum) -> new MetodoPago(
        rs.getInt("id"),
        rs.getString("nombre"),
        rs.getDouble("comision")
))
   .list();
    }
   public MetodoPago buscarPorId(int id) {
        return conexion.sql("SELECT * FROM metodos_pago WHERE id = ?")
                .params(id)
                .query((rs, rowNum) -> new MetodoPago(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getDouble("comision")
                )).single();
    }

    public int crear(MetodoPago metodo) {
        return conexion.sql("INSERT INTO metodos_pago (nombre, comision) VALUES (?, ?)")
                .params(metodo.getNombre(), metodo.getComision())
                .update();
    }

    public int actualizar(MetodoPago metodo) {
        return conexion.sql("UPDATE metodos_pago SET nombre = ?, comision = ? WHERE id = ?")
                .params(metodo.getNombre(), metodo.getComision(), metodo.getId())
                .update();
    }

    public int eliminar(int id) {
        return conexion.sql("DELETE FROM metodos_pago WHERE id = ?")
                .params(id)
                .update();
    }
}

       
   

