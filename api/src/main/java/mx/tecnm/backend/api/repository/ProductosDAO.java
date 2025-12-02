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

//oBtener por id
public Productos consultarProductoPorId(int id) {
    String sql = "SELECT id, nombre, precio, sku, color, marca, descripcion, peso, alto, ancho, profundidad, categorias_id FROM productos WHERE id = ?";        
return conexion.sql(sql)
            .params(id)
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
            .optional()
            .orElse(null);
        }

        //Insertar producto
public int insertarProducto(Productos producto) {
    String sql = """
        INSERT INTO productos(nombre, precio, sku, color, marca, descripcion, peso, alto, ancho, profundidad, categorias_id)
        VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
    """;
    return conexion.sql(sql)
        .params(
            producto.nombre(),
            producto.precio(),
            producto.sku(),
            producto.color(),
            producto.marca(),
            producto.descripcion(),
            producto.peso(),
            producto.alto(),
            producto.ancho(),
            producto.profundidad(),
            producto.categorias_id()
        )
        .update();
    }

//Modificar producto
public int modificarProducto(Productos producto) {
    String sql = """
        UPDATE productos 
        SET nombre = ?, precio = ?, sku = ?, color = ?, marca = ?, descripcion = ?, 
            peso = ?, alto = ?, ancho = ?, profundidad = ?, categorias_id = ?
        WHERE id = ?
    """;

    return conexion.sql(sql)
        .params(
            producto.nombre(),
            producto.precio(),
            producto.sku(),
            producto.color(),
            producto.marca(),
            producto.descripcion(),
            producto.peso(),
            producto.alto(),
            producto.ancho(),
            producto.profundidad(),
            producto.categorias_id(),
            producto.id()   // ← MUY IMPORTANTE: ID al final
        )
        .update();
}

//Eliminar producto
public int eliminarProducto(int id) {
    String sql = "DELETE FROM productos WHERE id = ?";
    return conexion.sql(sql)
        .params(id)
        .update();
}

}
