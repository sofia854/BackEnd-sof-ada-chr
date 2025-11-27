package mx.tecnm.backend.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.tecnm.backend.api.models.Usuario;
import mx.tecnm.backend.api.repository.UsuarioDAO;

@RestController
@RequestMapping("/usuarios")    
public class UsuarioController {

    @Autowired
    UsuarioDAO repo;
    @GetMapping()
    public ResponseEntity<List<Usuario>> obtenerUsuarios() {
    // Lógica para obtener la lista de usuarios
    List<Usuario> usuarios = repo.consultarUsuarios();
    return ResponseEntity.ok(usuarios);
    
}
    
}

