package mx.tecnm.backend.api.models;

public class DetallesCarrito {
    private int id;
    private int cantidad;
    private int productos_id;
    private int usuarios_id;

    public DetallesCarrito() {
    }

    public DetallesCarrito(int id, int cantidad, int productos_id, int usuarios_id) {
        this.id = id;
        this.cantidad = cantidad;
        this.productos_id = productos_id;
        this.usuarios_id = usuarios_id;
    }

    // GETTERS
    public int getId() {
        return id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public int getProductos_id() {
        return productos_id;
    }

    public int getUsuarios_id() {
        return usuarios_id;
    }

    // SETTERS
    public void setId(int id) {
        this.id = id;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setProductos_id(int productos_id) {
        this.productos_id = productos_id;
    }

    public void setUsuarios_id(int usuarios_id) {
        this.usuarios_id = usuarios_id;
    }
}    
