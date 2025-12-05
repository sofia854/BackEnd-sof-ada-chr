package mx.tecnm.backend.api.models;

public class DetallesCarrito {

    private int id;
    private int cantidad;
    private int productosId;
    private int usuariosId;

    public DetallesCarrito() {
    }

    public DetallesCarrito(int id, int cantidad, int productosId, int usuariosId) {
        this.id = id;
        this.cantidad = cantidad;
        this.productosId = productosId;
        this.usuariosId = usuariosId;
    }

    // Getters y setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    public int getProductosId() { return productosId; }
    public void setProductosId(int productosId) { this.productosId = productosId; }

    public int getUsuariosId() { return usuariosId; }
    public void setUsuariosId(int usuariosId) { this.usuariosId = usuariosId; }
}
