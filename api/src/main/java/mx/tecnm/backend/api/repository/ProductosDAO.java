package mx.tecnm.backend.api.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import mx.tecnm.backend.api.models.Productos;

@Repository
public class ProductosDAO {
    
    @Autowired
    JdbcClient conexion;

public List<Productos> consultarProductos() {
String sql = "SELECT id, nombre, precio, sku, color, marca, descripcion, peso, alto, ancho, profundidad, categorias_id FROM productos";
        return conexion.sql(sql)
        .query((rs, rowNum) -> new Productos(
                rs.getInt("id"),
                rs.getString("nombre"),
                rs.getInt("precio"),
                rs.getString("sku"),      // ← String
                rs.getString("color"),
                rs.getString("marca"),
                rs.getString("descripcion"),
                rs.getDouble("peso"),
                rs.getDouble("alto"),
                rs.getDouble("ancho"),
                rs.getDouble("profundidad"),
                rs.getInt("categorias_id") // ← Nuevo campo
        ))
        .list();
    }   


}
