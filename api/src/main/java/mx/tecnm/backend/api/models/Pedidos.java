package mx.tecnm.backend.api.models;

import java.sql.Timestamp;
import java.util.UUID;

public class Pedidos {

    private int id;
    private Timestamp fecha;
    private UUID numero;
    private double importe_productos;
    private double importe_envio;
    private int usuarios_id;
    private int metodos_pago_id;
    private Timestamp fecha_hora_pago;
    private double importe_iva;
    private double total;

    public Pedidos() {}

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Timestamp getFecha() { return fecha; }
    public void setFecha(Timestamp fecha) { this.fecha = fecha; }

    public UUID getNumero() { return numero; }
    public void setNumero(UUID numero) { this.numero = numero; }

    public double getImporte_productos() { return importe_productos; }
    public void setImporte_productos(double importe_productos) { this.importe_productos = importe_productos; }

    public double getImporte_envio() { return importe_envio; }
    public void setImporte_envio(double importe_envio) { this.importe_envio = importe_envio; }

    public int getUsuarios_id() { return usuarios_id; }
    public void setUsuarios_id(int usuarios_id) { this.usuarios_id = usuarios_id; }

    public int getMetodos_pago_id() { return metodos_pago_id; }
    public void setMetodos_pago_id(int metodos_pago_id) { this.metodos_pago_id = metodos_pago_id; }

    public Timestamp getFecha_hora_pago() { return fecha_hora_pago; }
    public void setFecha_hora_pago(Timestamp fecha_hora_pago) { this.fecha_hora_pago = fecha_hora_pago; }

    public double getImporte_iva() { return importe_iva; }
    public void setImporte_iva(double importe_iva) { this.importe_iva = importe_iva; }

    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }
}
