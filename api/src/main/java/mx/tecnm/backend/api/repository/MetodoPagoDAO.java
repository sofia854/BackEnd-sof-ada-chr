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


       
   
}
