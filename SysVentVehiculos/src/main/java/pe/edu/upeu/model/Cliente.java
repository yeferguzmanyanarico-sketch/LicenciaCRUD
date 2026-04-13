package pe.edu.upeu.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Cliente {
    private String id;
    private  String nombre;
    private  String telefono;
    private  String email;

    public Cliente(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return String.format("%-6s | %-20s  | %-15s", id, nombre,telefono);

    }
}
