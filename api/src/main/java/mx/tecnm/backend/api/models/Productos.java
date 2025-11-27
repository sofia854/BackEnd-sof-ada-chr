package mx.tecnm.backend.api.models;

public record Productos(int id, String nombre, int precio, int sku, String color, String marca, String descripcion, double peso, double dimensiones, int categoriaId) {

    
}
