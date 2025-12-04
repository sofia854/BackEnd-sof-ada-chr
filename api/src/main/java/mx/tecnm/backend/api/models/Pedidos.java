package mx.tecnm.backend.api.models;

import java.sql.Timestamp;
import java.util.UUID;
import java.math.BigDecimal;

public class Pedidos {
    private int id;
    private Timestamp fecha;
    private UUID numero;
    private BigDecimal importeProd; // Usamos BigDecimal para tipo numeric
    private BigDecimal importeServ; // Usamos BigDecimal para tipo numeric
    private int usuariosId;
    private int metodosPago;
    private Timestamp fechaHoraPago;
    private BigDecimal importeEnv;  // Usamos BigDecimal para tipo numeric
    private BigDecimal total;       // Usamos BigDecimal para tipo numeric

    public Pedidos(){}

    public Pedidos(int id, Timestamp fecha, UUID numero, BigDecimal importeProd, BigDecimal importeServ, int usuariosId, int metodosPago, Timestamp fechaHoraPago, BigDecimal importeEnv, BigDecimal total) {
        this.id = id;
        this.fecha = fecha;
        this.numero = numero;
        this.importeProd = importeProd;
        this.importeServ = importeServ;
        this.usuariosId = usuariosId;
        this.metodosPago = metodosPago;
        this.fechaHoraPago = fechaHoraPago;
        this.importeEnv = importeEnv;
        this.total = total;
    }
    
    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public Timestamp getFecha() { return fecha; }
    public void setFecha(Timestamp fecha) { this.fecha = fecha; }
    public UUID getNumero() { return numero; }
    public void setNumero(UUID numero) { this.numero = numero; }
    
    // Getters/Setters para BigDecimal
    public BigDecimal getImporteProd() { return importeProd; }
    public void setImporteProd(BigDecimal importeProd) { this.importeProd = importeProd; } 
    public BigDecimal getImporteServ() { return importeServ; }
    public void setImporteServ(BigDecimal importeServ) { this.importeServ = importeServ; }
    
    public int getUsuariosId() { return usuariosId; }
    public void setUsuariosId(int usuariosId) { this.usuariosId = usuariosId; }
    public int getMetodosPago() { return metodosPago; }
    public void setMetodosPago(int metodosPago) { this.metodosPago = metodosPago; } 
    
    public Timestamp getFechaHoraPago() { return fechaHoraPago; }
    public void setFechaHoraPago(Timestamp fechaHoraPago) { this.fechaHoraPago = fechaHoraPago; }
    
    // Getters/Setters para BigDecimal
    public BigDecimal getImporteEnv() { return importeEnv; }
    public void setImporteEnv(BigDecimal importeEnv) { this.importeEnv = importeEnv; }
    public BigDecimal getTotal() { return total; }
    public void setTotal(BigDecimal total) { this.total = total; }
}