    package mx.tecnm.backend.api.controller;
    import java.util.List;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.GetMapping;
    import org.springframework.web.bind.annotation.RequestMapping;
    import org.springframework.web.bind.annotation.RestController;

    import mx.tecnm.backend.api.models.Categoria;
    import mx.tecnm.backend.api.repository.CategoriaDAO;

    @RestController
    @RequestMapping("/categorias")
    public class CategoriaController {

        @Autowired
        CategoriaDAO repo;
        @GetMapping()
        public ResponseEntity<List<Categoria>> obtenerCategorias() {
        // Lógica para obtener la lista de categorías
        List<Categoria> categorias = repo.consultarCategorias();
        return ResponseEntity.ok(categorias);
        
        }

        
        }
