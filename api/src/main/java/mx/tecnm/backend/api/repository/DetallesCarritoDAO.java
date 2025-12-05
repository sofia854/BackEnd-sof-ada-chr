package mx.tecnm.backend.api.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import mx.tecnm.backend.api.models.DetallesCarrito;

@Repository
public class DetallesCarritoDAO {
    @Autowired
    JdbcClient conexion;

    public List<DetallesCarrito> consultarDetallesCarrito() {

        String sql = "SELECT id, cantidad, productos_id, usuarios_id FROM detalles_carrito";

        return conexion.sql(sql)
                .query((rs, rowNum) -> new DetallesCarrito(
                        rs.getInt("id"),
                        rs.getInt("cantidad"),
                        rs.getInt("productos_id"),
                        rs.getInt("usuarios_id")
                ))
                .list();
    }

    public DetallesCarrito consultarDetalleCarritoPorId(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'consultarDetalleCarritoPorId'");
    }

    public int actualizarDetalleCarrito(DetallesCarrito detallecarrito) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actualizarDetalleCarrito'");
    }

    public int eliminarDetalleCarrito(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'eliminarDetalleCarrito'");
    }
}
