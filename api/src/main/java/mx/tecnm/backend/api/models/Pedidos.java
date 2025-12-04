package mx.tecnm.backend.api.models;

import java.time.LocalDateTime;
import java.util.UUID;

public class Pedidos {

    private int id;
    private LocalDateTime fecha;
    private UUID numero;
    private double importe_productos;
    private double importe_envio;
    private int usuarios_id;
    private int metodos_pago_id;
    private LocalDateTime fecha_hora_pago;
    private double importe_iva;
    private double total;

    public Pedidos() {}

    public Pedidos(int id, LocalDateTime fecha, UUID numero, double importe_productos,
                   double importe_envio, int usuarios_id, int metodos_pago_id,
                   LocalDateTime fecha_hora_pago, double importe_iva, double total) {
        this.id = id;
        this.fecha = fecha;
        this.numero = numero;
        this.importe_productos = importe_productos;
        this.importe_envio = importe_envio;
        this.usuarios_id = usuarios_id;
        this.metodos_pago_id = metodos_pago_id;
        this.fecha_hora_pago = fecha_hora_pago;
        this.importe_iva = importe_iva;
        this.total = total;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public LocalDateTime getFecha() { return fecha; }
    public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }

    public UUID getNumero() { return numero; }
    public void setNumero(UUID numero) { this.numero = numero; }

    public double getImporteProd() { return importe_productos; }
    public void setImporteProd(double importe_productos) { this.importe_productos = importe_productos; }

    public double getImporteEnv() { return importe_envio; }
    public void setImporteEnv(double importe_envio) { this.importe_envio = importe_envio; }

    public int getUsuariosId() { return usuarios_id; }
    public void setUsuariosId(int usuarios_id) { this.usuarios_id = usuarios_id; }

    public int getMetodosPago() { return metodos_pago_id; }
    public void setMetodosPago(int metodos_pago_id) { this.metodos_pago_id = metodos_pago_id; }

    public LocalDateTime getFechaHoraPago() { return fecha_hora_pago; }
    public void setFechaHoraPago(LocalDateTime fecha_hora_pago) { this.fecha_hora_pago = fecha_hora_pago; }

    public double getImporteIva() { return importe_iva; }
    public void setImporteIva(double importe_iva) { this.importe_iva = importe_iva; }

    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }
}