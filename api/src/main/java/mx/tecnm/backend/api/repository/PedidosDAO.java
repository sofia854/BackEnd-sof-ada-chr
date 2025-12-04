package mx.tecnm.backend.api.repository;

import mx.tecnm.backend.api.models.Pedidos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public class PedidosDAO {

    @Autowired
    private JdbcClient conexion;

    // Obtener todos los pedidos
    public List<Pedidos> findAll() {
        return conexion.sql("SELECT * FROM pedidos")
                .query((ResultSet rs) -> {
                    List<Pedidos> lista = new java.util.ArrayList<>();
                    while (rs.next()) {
                        lista.add(mapRow(rs));
                    }
                    return lista;
                });
    }

    // Obtener pedido por ID
    public Pedidos findById(int id) {
        return conexion.sql("SELECT * FROM pedidos WHERE id = :id")
                .param("id", id)
                .query(rs -> rs.next() ? mapRow(rs) : null);
    }

    // Guardar un pedido
    public Pedidos save(Pedidos pedido) {
        UUID numero = pedido.getNumero() != null ? pedido.getNumero() : UUID.randomUUID();
        conexion.sql("""
                INSERT INTO pedidos (fecha, numero, importe_productos, importe_envio, usuarios_id,
                metodos_pago_id, fecha_hora_pago, importe_iva, total)
                VALUES (:fecha, :numero, :importe_productos, :importe_envio, :usuarios_id,
                :metodos_pago_id, :fecha_hora_pago, :importe_iva, :total)
                """)
                .param("fecha", pedido.getFecha())
                .param("numero", numero.toString())
                .param("importe_productos", pedido.getImporteProd())
                .param("importe_envio", pedido.getImporteEnv())
                .param("usuarios_id", pedido.getUsuariosId())
                .param("metodos_pago_id", pedido.getMetodosPago())
                .param("fecha_hora_pago", pedido.getFechaHoraPago())
                .param("importe_iva", pedido.getImporteIva())
                .param("total", pedido.getTotal())
                .update();
        pedido.setNumero(numero);
        return pedido;
    }

    // Actualizar un pedido
    public Pedidos update(int id, Pedidos pedido) {
        int rowsAffected = conexion.sql("""
                UPDATE pedidos SET fecha = :fecha, numero = :numero, importe_productos = :importe_productos,
                importe_envio = :importe_envio, usuarios_id = :usuarios_id, metodos_pago_id = :metodos_pago_id,
                fecha_hora_pago = :fecha_hora_pago, importe_iva = :importe_iva, total = :total
                WHERE id = :id
                """)
                .param("fecha", pedido.getFecha())
                .param("numero", pedido.getNumero().toString())
                .param("importe_productos", pedido.getImporteProd())
                .param("importe_envio", pedido.getImporteEnv())
                .param("usuarios_id", pedido.getUsuariosId())
                .param("metodos_pago_id", pedido.getMetodosPago())
                .param("fecha_hora_pago", pedido.getFechaHoraPago())
                .param("importe_iva", pedido.getImporteIva())
                .param("total", pedido.getTotal())
                .param("id", id)
                .update();
        return rowsAffected > 0 ? findById(id) : null;
    }

    // Eliminar un pedido
    public boolean delete(int id) {
        int rowsAffected = conexion.sql("DELETE FROM pedidos WHERE id = :id")
                .param("id", id)
                .update();
        return rowsAffected > 0;
    }

    // Mapear ResultSet a objeto Pedidos
    private Pedidos mapRow(ResultSet rs) throws SQLException {
        return new Pedidos(
                rs.getInt("id"),
                rs.getObject("fecha", LocalDateTime.class),
                UUID.fromString(rs.getString("numero")),
                rs.getDouble("importe_productos"),
                rs.getDouble("importe_envio"),
                rs.getInt("usuarios_id"),
                rs.getInt("metodos_pago_id"),
                rs.getObject("fecha_hora_pago", LocalDateTime.class),
                rs.getDouble("importe_iva"),
                rs.getDouble("total")
        );
    }
}
