// Envío marítimo
class EnvioMaritimo extends Envio {
    public EnvioMaritimo(String origen, String destino, double peso, double distanciaKm) {
        super(origen, destino, peso, distanciaKm);
    }

    @Override
    public double calcularCosto() {
        return peso * distanciaKm * 0.03; // costo intermedio
    }

    @Override
    public int calcularTiempoHoras() {
        return (int)(distanciaKm / 40); // velocidad promedio 40 km/h
    }
}
