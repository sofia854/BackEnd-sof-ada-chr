package mx.tecnm.backend.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.tecnm.backend.api.models.Usuario;
import mx.tecnm.backend.api.repository.UsuarioDAO;

@RestController
@RequestMapping("/usuarios")    
public class UsuarioController {

    @Autowired
    UsuarioDAO repo;

    //Obtener TODOS los usuarios
    @GetMapping()
    public ResponseEntity<List<Usuario>> obtenerUsuarios() {
    List<Usuario> usuarios = repo.consultarUsuarios();
    return ResponseEntity.ok(usuarios);
    
}

//Obtener usuario por ID
@GetMapping("/{id}")
public ResponseEntity<Usuario> obtenerUsuarioPorId(@PathVariable int id) {
    Usuario usuario = repo.consultarUsuarioPorId(id);

    if (usuario == null) {
        return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok(usuario);
}

//Crear usuario
@PostMapping
public ResponseEntity<String> agregarUsuario(@RequestBody Usuario usuario) {

    int filas = repo.insertarUsuario(usuario);

    if (filas == 1) {
        return ResponseEntity.ok("Usuario agregado correctamente :)");
        
    } else {
        return ResponseEntity.badRequest().body("No se pudo agregar el usuario :(");
    }
}

 //Modificar
    @PutMapping("/{id}")
    public ResponseEntity<String> actualizarUsuario(@PathVariable int id, @RequestBody Usuario usuario) {

        int filas = repo.actualizarUsuario(usuario, id);

        if (filas == 1) {
            return ResponseEntity.ok("Usuario actualizado correctamente");
        } else {
            return ResponseEntity.status(404).body("Usuario no encontrado");
        }
    }

    //Eliminar
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarUsuario(@PathVariable int id) {

        int filas = repo.eliminarUsuario(id);

        if (filas == 1) {
            return ResponseEntity.ok("Usuario eliminado correctamente :)");
        } else {
            return ResponseEntity.status(404).body("Usuario no encontrado :(");
        


        }
    }


}

