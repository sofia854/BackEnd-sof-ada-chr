package mx.tecnm.backend.api.repository;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import mx.tecnm.backend.api.models.Categoria;

@Repository
public class CategoriaDAO {

    @Autowired
    private JdbcTemplate conexion; // Usando JdbcTemplate

    private final RowMapper<Categoria> CategoriaMapper = (rs, rowNum) -> {
        Categoria c = new Categoria();
        c.setId(rs.getInt("id"));
        c.setNombre(rs.getString("nombre"));
        return c;
    };

    // CRUD: Consultar todas las categorías
    public List<Categoria> consultarCategorias() {
        String sql = "SELECT id, nombre FROM categorias";
        return conexion.query(sql, CategoriaMapper);
    }

    // CRUD: Consultar una categoría por ID
    public Categoria consultarCategoriaPorId(int id) {
        String sql = "SELECT id, nombre FROM categorias WHERE id = ?";
        return conexion.queryForObject(sql, CategoriaMapper, id);
    }

    // CRUD: Registrar una nueva categoría (CREATE)
    public int registrarCategoria(Categoria categoria) {
        String sql = "INSERT INTO categorias (nombre) VALUES (?)";
        return conexion.update(sql, categoria.getNombre());
    }

    // CRUD: Actualizar una categoría (UPDATE)
    public int actualizarCategoria(Categoria categoria) {
        String sql = "UPDATE categorias SET nombre = ? WHERE id = ?";
        return conexion.update(sql, categoria.getNombre(), categoria.getId());
    }

    // CRUD: Eliminar una categoría por ID (DELETE)
    public int eliminarCategoria(int id) {
        String sql = "DELETE FROM categorias WHERE id = ?";
        return conexion.update(sql, id);
    }
}