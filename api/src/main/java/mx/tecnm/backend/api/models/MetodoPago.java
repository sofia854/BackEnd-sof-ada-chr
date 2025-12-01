package mx.tecnm.backend.api.models;

public class MetodoPago {
    public int id;
    public String nombre;
    public Double comision;

    public MetodoPago(int id, String nombre, Double comision) {
        this.id = id;
        this.nombre = nombre;
        this.comision = comision;
    }

 
 
}
