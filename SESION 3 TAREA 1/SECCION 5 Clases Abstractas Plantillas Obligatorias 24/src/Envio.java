// Clase abstracta base
abstract class Envio {
    protected String origen;
    protected String destino;
    protected double peso;       // en kg
    protected double distanciaKm; // en km
    protected String estado = "Preparando";

    public Envio(String origen, String destino, double peso, double distanciaKm) {
        this.origen = origen;
        this.destino = destino;
        this.peso = peso;
        this.distanciaKm = distanciaKm;
    }

    // Métodos abstractos: cada tipo de envío define su lógica
    public abstract double calcularCosto();
    public abstract int calcularTiempoHoras();

    // Método concreto que usa los abstractos (Template Method)
    public String generarGuia() {
        return String.format("Envío %s → %s | Peso: %.2f kg | Distancia: %.1f km | Costo: $%.2f | Tiempo: %dh | Estado: %s",
                origen, destino, peso, distanciaKm, calcularCosto(), calcularTiempoHoras(), estado);
    }
}
