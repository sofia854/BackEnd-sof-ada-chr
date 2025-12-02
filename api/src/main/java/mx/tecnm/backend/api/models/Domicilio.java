package mx.tecnm.backend.api.models;

public record Domicilio(int id, String calle, int numero, String colonia, String cp, String estado, String ciudad, int usuarios_id)   {
}
