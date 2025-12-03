package mx.tecnm.backend.api.models;

public class DetallesPedidos {
    private int id;
    private int cantidad;
    private double precio_unitario; // Mantiene el nombre de variable descriptivo
    private int productos_id;
    private int pedidos_id;

    public DetallesPedidos() {
        // Constructor vacío
    }

    // Constructor con todos los campos
    public DetallesPedidos(int id, int cantidad, double precio_unitario, int productos_id, int pedidos_id) {
        this.id = id;
        this.cantidad = cantidad;
        this.precio_unitario = precio_unitario;
        this.productos_id = productos_id;
        this.pedidos_id = pedidos_id;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio_unitario() {
        return precio_unitario;
    }

    public void setPrecio_unitario(double precio_unitario) {
        this.precio_unitario = precio_unitario;
    }

    public int getProductos_id() {
        return productos_id;
    }

    public void setProductos_id(int productos_id) {
        this.productos_id = productos_id;
    }

    public int getPedidos_id() {
        return pedidos_id;
    }

    public void setPedidos_id(int pedidos_id) {
        this.pedidos_id = pedidos_id;
    }
}