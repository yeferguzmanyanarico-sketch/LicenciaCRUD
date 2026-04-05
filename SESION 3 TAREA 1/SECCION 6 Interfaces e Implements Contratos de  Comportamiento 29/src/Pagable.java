// Interfaces
interface Pagable {
    double calcularMonto();
    boolean procesarPago();
}

interface Imprimible {
    void imprimir();
    String formatear();
}

interface Exportable {
    String exportar(String formato);
    String getFormato();
}
