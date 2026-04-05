// Envío aéreo
class EnvioAereo extends Envio {
    public EnvioAereo(String origen, String destino, double peso, double distanciaKm) {
        super(origen, destino, peso, distanciaKm);
    }

    @Override
    public double calcularCosto() {
        return peso * distanciaKm * 0.05; // costo más alto
    }

    @Override
    public int calcularTiempoHoras() {
        return (int)(distanciaKm / 800); // velocidad promedio 800 km/h
    }
}