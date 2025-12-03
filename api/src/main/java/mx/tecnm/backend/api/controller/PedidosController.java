package mx.tecnm.backend.api.controller;

@RestController
@RequestMapping("/pedidos")
public class PedidosController {
    @Autowired
    private PedidosDAO pedidosDAO;

    @GetMapping
    public List<Pedidos> getAllPedidos() {
        return pedidosDAO.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedidos> getPedidoById(@PathVariable int id) {
        retun pedidosDAO.findById(id);

    @PostMapping
    public Pedidos createPedido(@RequestBody Pedidos pedido) {
        return pedidosDAO.save(pedido);
    }   
    @PutMapping("/{id}")
    public Pedidos updatePedido(@PathVariable int id, @RequestBody Pedidos pedido) {
        return pedidosDAO.update(id, pedido);
    }
    @DeleteMapping("/{id}")
    public String deletePedido(@PathVariable int id) {
        boolean deleted = pedidosDAO.delete(id);
        if (deleted) {
            return "Pedido eliminado correctamente.";
        } else {
            return "Pedido no encontrado.";
        }
    }