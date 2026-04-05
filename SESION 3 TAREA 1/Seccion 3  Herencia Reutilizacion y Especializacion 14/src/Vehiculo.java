// vehiculo
public class Vehiculo {
    protected String marca ;
    protected String modelo;
    protected  int capacidad; // numero de pasajeros
    protected double consumoKmLitro; // km por litro

    public Vehiculo(String marca, String modelo, int capacidad,double consumoKmLitro) {
        this.marca = marca;
        this.modelo = modelo;
        this.capacidad = capacidad;
        this.consumoKmLitro = consumoKmLitro;

    }

    // Calcular costo de viaje
    public double costoViaje(double km, double precioLitro) {
        return (km / consumoKmLitro) * precioLitro;
    }

    // Descripción general
    public String descripcion() {
        return String.format("%s %s (capacidad: %d, consumo: %.2f km/l)",
                marca, modelo, capacidad, consumoKmLitro);
    }

    // Capacidad total (para flotas)
    public int capacidadTotal(int cantidadVehiculos) {
        return capacidad * cantidadVehiculos;

    }

}
