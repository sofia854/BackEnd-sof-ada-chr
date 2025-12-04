package mx.tecnm.backend.api.models;

import java.math.BigDecimal; // Importado para tipo numeric

public class DetallesPedidos {
    private int id;
    private int cantidad;
    private BigDecimal precio_unitario; // Usamos BigDecimal para tipo numeric ('precio' en DB)
    private int productosId;
    private int pedidosId;

    public DetallesPedidos() {}

    public DetallesPedidos(int id, int cantidad, BigDecimal precio_unitario, int productosId, int pedidosId) {
        this.id = id;
        this.cantidad = cantidad;
        this.precio_unitario = precio_unitario;
        this.productosId = productosId;
        this.pedidosId = pedidosId;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }
    
    // Getter/Setter para BigDecimal
    public BigDecimal getPrecio_unitario() { return precio_unitario; }
    public void setPrecio_unitario(BigDecimal precio_unitario) { this.precio_unitario = precio_unitario; }
    
    // Getters/Setters para llaves foráneas
    public int getProductosId() { return productosId; }
    public void setProductosId(int productosId) { this.productosId = productosId; }
    public int getPedidosId() { return pedidosId; }
    public void setPedidosId(int pedidosId) { this.pedidosId = pedidosId; }
}