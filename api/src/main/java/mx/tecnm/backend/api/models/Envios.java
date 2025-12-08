package mx.tecnm.backend.api.models;

public class Envios {
    // Corresponden a las columnas de la tabla 'envios'
    private int id;
    private String fecha_entrega; // Asumiendo que es String (TIMESTAMP/DATE)
    private String fecha;         // Asumiendo que es String (TIMESTAMP/DATE)
    private String estado;
    private String numero_seguimiento;
    private int domicilios_id;
    private int pedidos_id;

    public Envios() {}

    public Envios(int id, String fecha_entrega, String fecha, String estado,
                  String numero_seguimiento, int domicilios_id, int pedidos_id) {
        this.id = id;
        this.fecha_entrega = fecha_entrega;
        this.fecha = fecha;
        this.estado = estado;
        this.numero_seguimiento = numero_seguimiento;
        this.domicilios_id = domicilios_id;
        this.pedidos_id = pedidos_id;
    }

    // Getters
    public int getId() { return id; }
    public String getFecha_entrega() { return fecha_entrega; }
    public String getFecha() { return fecha; }
    public String getEstado() { return estado; }
    public String getNumero_seguimiento() { return numero_seguimiento; }
    public int getDomicilios_id() { return domicilios_id; }
    public int getPedidos_id() { return pedidos_id; }

    // Setters
    public void setId(int id) { this.id = id; }
    public void setFecha_entrega(String fecha_entrega) { this.fecha_entrega = fecha_entrega; }
    public void setFecha(String fecha) { this.fecha = fecha; }
    public void setEstado(String estado) { this.estado = estado; }
    public void setNumero_seguimiento(String numero_seguimiento) { this.numero_seguimiento = numero_seguimiento; }
    public void setDomicilios_id(int domicilios_id) { this.domicilios_id = domicilios_id; }
    public void setPedidos_id(int pedidos_id) { this.pedidos_id = pedidos_id; }
}