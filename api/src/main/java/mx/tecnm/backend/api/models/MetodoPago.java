package mx.tecnm.backend.api.models;

public class MetodoPago {
    public int id;
    public String nombre;
    public double comision;

    public MetodoPago() {}

    public MetodoPago(int id, String nombre, double comision) {
        this.id = id;
        this.nombre = nombre;
        this.comision = comision;
    }
    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public double getComision() { return comision; }

    public void setId(int id) { this.id = id; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setComision(double comision) { this.comision = comision; }
}

 
 

