package mx.tecnm.backend.api.models;

import java.time.LocalDateTime;
import java.util.UUID;

public class Pedidos {

    private int id;
    private LocalDateTime fecha;
    private UUID numero;
    private double importeProd;
    private double importeEnv;
    private int usuariosId;
    private int metodosPago;
    private LocalDateTime fechaHoraPago;
    private Double importeIva; 
    private Double total;      

    public Pedidos() {}

    public Pedidos(int id, LocalDateTime fecha, UUID numero, double importeProd, double importeEnv,
                   int usuariosId, int metodosPago, LocalDateTime fechaHoraPago,
                   Double importeIva, Double total) {
        this.id = id;
        this.fecha = fecha;
        this.numero = numero;
        this.importeProd = importeProd;
        this.importeEnv = importeEnv;
        this.usuariosId = usuariosId;
        this.metodosPago = metodosPago;
        this.fechaHoraPago = fechaHoraPago;
        this.importeIva = importeIva;
        this.total = total;
    }

    // Getters y Setters  
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public LocalDateTime getFecha() { return fecha; }
    public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }

    public UUID getNumero() { return numero; }
    public void setNumero(UUID numero) { this.numero = numero; }

    public double getImporteProd() { return importeProd; }
    public void setImporteProd(double importeProd) { this.importeProd = importeProd; }

    public double getImporteEnv() { return importeEnv; }
    public void setImporteEnv(double importeEnv) { this.importeEnv = importeEnv; }

    public int getUsuariosId() { return usuariosId; }
    public void setUsuariosId(int usuariosId) { this.usuariosId = usuariosId; }

    public int getMetodosPago() { return metodosPago; }
    public void setMetodosPago(int metodosPago) { this.metodosPago = metodosPago; }

    public LocalDateTime getFechaHoraPago() { return fechaHoraPago; }
    public void setFechaHoraPago(LocalDateTime fechaHoraPago) { this.fechaHoraPago = fechaHoraPago; }

    public Double getImporteIva() { return importeIva; }
    public void setImporteIva(Double importeIva) { this.importeIva = importeIva; }

    public Double getTotal() { return total; }
    public void setTotal(Double total) { this.total = total; }
}
