
// Envío terrestre
class EnvioTerrestre extends Envio {
    public EnvioTerrestre(String origen, String destino, double peso, double distanciaKm) {
        super(origen, destino, peso, distanciaKm);
    }

    @Override
    public double calcularCosto() {
        return peso * distanciaKm * 0.02; // costo más bajo
    }

    @Override
    public int calcularTiempoHoras() {
        return (int)(distanciaKm / 60); // velocidad promedio 60 km/h
    }
}