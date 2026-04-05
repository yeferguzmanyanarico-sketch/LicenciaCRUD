// Clase Factura que implementa las tres interfaces
class Factura implements Pagable, Imprimible, Exportable {
    private String cliente;
    private double monto;
    private boolean pagada = false;

    public Factura(String cliente, double monto) {
        this.cliente = cliente;
        this.monto = monto;
    }

    // Métodos de Pagable
    @Override
    public double calcularMonto() {
        return monto;
    }

    @Override
    public boolean procesarPago() {
        if (monto > 0) {
            pagada = true;
            System.out.println("Pago procesado para " + cliente + " por $" + monto);
            return true;
        }
        System.out.println("Monto inválido.");
        return false;
    }

    // Métodos de Imprimible
    @Override
    public void imprimir() {
        System.out.println("Factura impresa: " + formatear());
    }

    @Override
    public String formatear() {
        return "Cliente: " + cliente + " | Monto: $" + monto + " | Pagada: " + pagada;
    }

    // Métodos de Exportable
    @Override
    public String exportar(String formato) {
        return "Exportando factura en formato " + formato + ": " + formatear();
    }

    @Override
    public String getFormato() {
        return "PDF";
    }
}
