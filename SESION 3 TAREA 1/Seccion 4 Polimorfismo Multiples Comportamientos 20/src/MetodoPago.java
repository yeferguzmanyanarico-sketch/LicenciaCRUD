// clase base
public class MetodoPago {
    protected double monto;
    protected boolean procesado = false;

    public MetodoPago(double monto) {
        this.monto = monto;
    }

    public boolean validar() {
        return monto > 0;
    }

    public boolean procesarPago() {
        if (validar()) {
            procesado = true;
            System.out.println("Pago procesado: $" + monto);
            return true;
        }
        System.out.println("Validación fallida.");
        return false;
    }
}
