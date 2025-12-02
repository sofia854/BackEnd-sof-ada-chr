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
    
//obtener todos 
public List<Usuario> consultarUsuarios() {
        String sql = "SELECT id, nombre, email, telefono,  sexo, fecha_nacimiento, contrasena, fecha_registro  FROM usuarios";
        return conexion.sql(sql)
    .query((rs, rowNum) -> new Usuario(
        rs.getInt("id"),
        rs.getString("nombre"),
        rs.getString("email"),
        rs.getString("telefono"),
        rs.getString("sexo"),
        rs.getString("fecha_nacimiento"),
        rs.getString("contrasena"),
        rs.getString("fecha_registro")
    ))
    .list();
    }

    //Obtener por id
    public Usuario consultarUsuarioPorId(int id) {
    String sql = "SELECT id, nombre, email, telefono, sexo, fecha_nacimiento, contrasena, fecha_registro FROM usuarios WHERE id = ?";

    return conexion.sql(sql)
            .params(id)
            .query((rs, rowNum) -> new Usuario(
                rs.getInt("id"),
                rs.getString("nombre"),
                rs.getString("email"),
                rs.getString("telefono"),
                rs.getString("sexo"),
                rs.getString("fecha_nacimiento"),
                rs.getString("contrasena"),
                rs.getString("fecha_registro")
            ))
            .optional()
            .orElse(null);
}


//para que funcionarsa tuve que modificar el tipo de dato del sexo 
//Insertar usuario
public int insertarUsuario(Usuario usuario) {
    String sql = """
        INSERT INTO usuarios(nombre, email, telefono, sexo, fecha_nacimiento, contrasena, fecha_registro)
        VALUES (?, ?, ?, ?, ?, ?, ?)
    """;

    return conexion.sql(sql)
        .params(
            usuario.nombre(),
            usuario.email(),
            usuario.telefono(),
            usuario.sexo(),   // se modifico este tipo de dato a VARCHAR en nuestro DataGrip
            java.sql.Date.valueOf(usuario.fecha_nacimiento()),
            usuario.contrasena(),
            java.sql.Date.valueOf(usuario.fecha_registro())
        )
        .update();
}

//Modificar usuario
public int actualizarUsuario(Usuario usuario, int id) {
    String sql = """
        UPDATE usuarios
        SET nombre = ?, email = ?, telefono = ?, sexo = ?, 
            fecha_nacimiento = ?, contrasena = ?, fecha_registro = ?
        WHERE id = ?
    """;

    // Parseo seguro de fecha_nacimiento (DATE)
    java.sql.Date fechaNacimiento = java.sql.Date.valueOf(usuario.fecha_nacimiento());

    // Parseo seguro de fecha_registro (TIMESTAMP)
    // Si viene con "T" (ISO), lo cambiamos a espacio
    String fechaReg = usuario.fecha_registro().replace("T", " ");

    // Si no trae segundos, los agregamos
    if (fechaReg.length() == 16) {  // yyyy-MM-dd HH:mm
        fechaReg += ":00";
    }

    java.sql.Timestamp fechaRegistro = java.sql.Timestamp.valueOf(fechaReg);

    return conexion.sql(sql)
            .params(
                usuario.nombre(),
                usuario.email(),
                usuario.telefono(),
                usuario.sexo(),
                fechaNacimiento,
                usuario.contrasena(),
                fechaRegistro,
                id
            )
            .update();
}





//Eliminar usuario
//Eliminar usuario por ID
public int eliminarUsuario(int id) {
    String sql = "DELETE FROM usuarios WHERE id = ?";

    return conexion.sql(sql)
            .params(id)
            .update();
}




}
