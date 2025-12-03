package mx.tecnm.backend.api.models;

public class Pedidos {
    private int id;
    private Timestamp fecha;
    private UUID numero;
    private double importeProd;
    private double importeServ;
    private int usuariosId;
    private int metodosPago;
    private Timestamp fechaHoraPago;
    private double importeEnv;
    private double total;

    public Pedidos(){}

    public Pedidos(int id, Timestamp fecha, UUID numero, double importeProd, double importeServ, int usuariosId, int metodosPago, Timestamp fechaHoraPago, double importeEnv, double total) {
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
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Timestamp getFecha() {
        return fecha;   
    }
    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }
    public UUID getNumero() {
        return numero;  }
    public void setNumero(UUID numero) {
        this.numero = numero;
    }
    public double getImporteProd() {
        return importeProd;
    }
    public void setImporteProd(double importeProd) {
        this.importeProd = importeProd;
    }   
    public double getImporteServ() {
        return importeServ;
    }
    public void setImporteServ(double importeServ) {
        this.importeServ = importeServ;
    }
    public int getUsuariosId() {
        return usuariosId;
    }
    public void setUsuariosId(int usuariosId) {
        this.usuariosId = usuariosId;
    }
    public int getMetodosPago() {
        return metodosPago;
    }
    public void setMetodosPago(int metodosPago) {
        this.metodosPago = metodosPago; 
    }
    public Timestamp getFechaHoraPago() {
        return fechaHoraPago;
    }
    public void setFechaHoraPago(Timestamp fechaHoraPago) {
        this.fechaHoraPago = fechaHoraPago;
    }
    public double getImporteEnv() {
        return importeEnv;
    }
    public void setImporteEnv(double importeEnv) {
        this.importeEnv = importeEnv;
    }
    public double getTotal() {
        return total;
    }
    public void setTotal(double total) {
        this.total = total;
    }


}
