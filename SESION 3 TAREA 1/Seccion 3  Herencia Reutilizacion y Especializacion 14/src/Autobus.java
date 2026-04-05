//autobus
public class Autobus extends  Vehiculo {
    private int pisos;


    public Autobus(String marca, String modelo, int capacidad, double consumoKmLitro, int pisos) {
        super(marca, modelo, capacidad, consumoKmLitro);
        this.pisos = pisos;
    }

    @Override
    public String descripcion() {
        return super.descripcion() + " | Pisos: " + pisos;
    }
}
